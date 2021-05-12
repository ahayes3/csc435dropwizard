package ahayes5.resources.error

import ahayes5.resources.JSONView
import com.fasterxml.jackson.annotation.JsonProperty

class ResourceError(@JsonProperty val code:Int,@JsonProperty val message:String) {
    fun toJson(): String {
        return JSONView(this)
    }
}