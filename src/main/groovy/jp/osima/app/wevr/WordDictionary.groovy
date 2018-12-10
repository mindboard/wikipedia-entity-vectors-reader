package jp.osima.app.wevr

import org.mapdb.DBMaker

class WordDictionary {
    private final File cacheFile
    WordDictionary(File cacheFile){
        this.cacheFile = cacheFile
    }
    
    private Map<String, String> _map
    private Map<String, String> getMap(){
        if(_map==null){
            def db  = DBMaker.fileDB(cacheFile).closeOnJvmShutdown().make()
            _map = db.hashMap("map").createOrOpen()
        }
        return _map
    }

    void forEach( VecWordResult result ){
        getMap().keySet().each { word->
            def vecWord = get(word)
            result.call(vecWord)
        }
    }

    void put(String word, VecWord vecWord){
        getMap().put(word, vecWord.toJson())
    }

    VecWord get(String word){
        String value = getMap().get(word)
        return VecWord.getInstance(value)
    }
}
