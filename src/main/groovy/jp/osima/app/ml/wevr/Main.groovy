package jp.osima.app.ml.wevr

class Main {
    private static def CACHE_FILE = new File('cache.mdb')

    static void main(String[] args){
        if( args.length>1 ){
            def gzippedVectorsTxtFile = new File(args[0])
            def word = args[1]

            def invalid = ( !CACHE_FILE.exists() && !gzippedVectorsTxtFile.exists() )
            if( invalid ){
                println 'vector file not found.'
                System.exit(0)
            }

            def wordDictionary = WordDictionaryFactory.createWordDictionary(gzippedVectorsTxtFile, CACHE_FILE)
            if( wordDictionary==null ){
                System.exit(0)
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
