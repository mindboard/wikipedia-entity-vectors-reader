package jp.osima.app.ml.wevr

import groovy.json.*

class VecWord {
    final String word
    final List<Float> vector
    VecWord(String word, List<Float> vector){
        this.word = word
        this.vector = vector
    }
    
    static VecWord getInstance(String json){
        def obj = new JsonSlurper().parseText( json )
        return new VecWord(obj.word, obj.vector)
    }

    String toJson(){
        def obj = [:]
        obj.word = word
        obj.vector = vector
        return new JsonBuilder( obj ).toString()
    }
}
