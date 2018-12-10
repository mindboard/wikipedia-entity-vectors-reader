package jp.osima.app.wevr

import java.util.zip.GZIPInputStream

class Main {
    private static def CACHE_FILE = new File('cache.mdb')

    private static void createCacheFile(def gzippedVectorsTxtFile, WordDictionary wordDictionary){
        def br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(gzippedVectorsTxtFile)), 'UTF-8'))

        boolean firstLine = true
        String line = null
        Integer layerSize = null

        while( (line=br.readLine())!=null ){
            def split = line.split(/ /)

            if( firstLine ){
                firstLine = false
                layerSize = split[1] as int
            }
            else {
                def word = split[0]
                List<Float> vector = []
                (0..<layerSize).each { index->
                    vector << (split[(index+1)] as float)
                }
                
                def vecWord = new VecWord(word, vector)
                wordDictionary.put( word, vecWord )
            }
        }

        br.close()
    }

    static void main(String[] args){
        if( args.length>1 ){
            def gzippedVectorsTxtFile = new File(args[0])
            def word = args[1]

            def invalid = ( !CACHE_FILE.exists() && !gzippedVectorsTxtFile.exists() )
            if( invalid ){
                println 'vector file not found.'
                System.exit()
            }

            def wordDictionary = new WordDictionary(CACHE_FILE)

            if( !CACHE_FILE.exists() && gzippedVectorsTxtFile.exists() ){
                assert gzippedVectorsTxtFile.name.endsWith('gz')
                createCacheFile(gzippedVectorsTxtFile, wordDictionary)
            }

            def vecWord = wordDictionary.get(word)

            println vecWord.word
            //println vecWord.vector

            // List up top 10 similarity words.
            Map<String, Double> map = [:]
            wordDictionary.forEach( new VecWordResult(){
                void call(VecWord myVecWord){
                    if( vecWord.word!=myVecWord.word ){
                        def v = SimilarityUtils.similarity( vecWord.vector, myVecWord.vector )
                        map.put( myVecWord.word, v )
                    }
                }
            })

            def mapList = new ArrayList( map.entrySet() )
            Collections.sort(mapList,{a,b->
                a.value.compareTo(b.value) * -1
            } as Comparator)

            mapList.take(Math.min(10, mapList.size())).each {
                println "- ${it.key} (score: ${it.value})"
            }
        }
    }
}
