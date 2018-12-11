package jp.osima.app.ml.wevr

import java.util.zip.GZIPInputStream

class WordDictionaryFactory {
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

    static WordDictionary createWordDictionary(
        File gzippedVectorsTxtFile,
        File cacheFile){

        def invalid = ( !cacheFile.exists() && !gzippedVectorsTxtFile.exists() )
        if( invalid ){
            return null
        }

        def wordDictionary = new WordDictionary(cacheFile)

        if( !cacheFile.exists() && gzippedVectorsTxtFile.exists() ){
            def valid = gzippedVectorsTxtFile.name.endsWith('gz')
            if( !valid ){
                return null
            }

            createCacheFile(gzippedVectorsTxtFile, wordDictionary)
        }

        return wordDictionary
    }
}
