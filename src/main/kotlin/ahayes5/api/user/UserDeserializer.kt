package ahayes5.api.user

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.lambdaworks.crypto.SCryptUtil
import kotlin.random.Random

class UserDeserializer(vc: Class<*>? = null) : StdDeserializer<User>(vc) {
    override fun deserialize(jsonparser: JsonParser, context: DeserializationContext?): User {
        val mapper = ObjectMapper()
        val head: JsonNode = jsonparser.codec.readTree(jsonparser)
        val name = mapper.convertValue(head.get("name"),object: TypeReference<String>(){})
        val pass = mapper.convertValue(head.get("pass"),object: TypeReference<String>(){})
        val hash = SCryptUtil.scrypt(pass,16384,8,1)
        val out = User(name)
        out.hash=hash
        return out
    }

    fun randomChars(size:Int): String {
        val allowed = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..size).map { allowed.random() }.joinToString("")
    }
}