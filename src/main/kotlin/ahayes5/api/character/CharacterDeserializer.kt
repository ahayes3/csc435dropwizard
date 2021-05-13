package ahayes5.api.character

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer


class CharacterDeserializer constructor(vc: Class<*>? = null) : StdDeserializer<Character>(vc) {
    override fun deserialize(jsonparser: JsonParser, context: DeserializationContext?): Character {
        val mapper = ObjectMapper()
        val head: JsonNode = jsonparser.codec.readTree(jsonparser)
        val clazzes: List<Clazz>

        if(!head.has("skills") || !head.has("languages") || !head.has("tools") || !head.has("features") ||!head.has("items"))
            throw JsonParseException(jsonparser,"Missing fields.")

        val skills: List<String> = mapper.convertValue(head.get("skills"), object : TypeReference<List<String>>() {})
        val languages: List<String> = mapper.convertValue(head.get("languages"), object : TypeReference<List<String>>() {})
        val tools: List<String> = mapper.convertValue(head.get("tools"), object : TypeReference<List<String>>() {})
        val features: List<String> = mapper.convertValue(head.get("features"), object : TypeReference<List<String>>() {})
        val items: Map<String, Int> = mapper.convertValue(head.get("items"), object : TypeReference<Map<String, Int>>() {})
        val n = head.get("classes")
        clazzes = mapper.convertValue(n, object : TypeReference<List<Clazz>>() {})

        if(!head.has("name") || !head.has("background") || !head.has("race") || !head.has("str")
            || !head.has("dex")|| !head.has("con")|| !head.has("intel")|| !head.has("wis") || !head.has("cha")
            || !head.has("ac")|| !head.has("init")|| !head.has("speed")|| !head.has("maxHp"))
                throw JsonParseException(jsonparser,"Missing field")

        if(!head.get("name").isTextual || !head.get("background").isTextual || !head.get("race").isTextual || !head.get("str").isInt
            || !head.get("dex").isInt|| !head.get("con").isInt|| !head.get("intel").isInt|| !head.get("wis").isInt || !head.get("cha").isInt
            || !head.get("ac").isInt|| !head.get("init").isInt|| !head.get("speed").isInt|| !head.get("maxHp").isInt)
                throw JsonParseException(jsonparser,"Field is wrong type.")

        return Character(
            head.get("name").textValue(),
            head.get("background").textValue(),
            head.get("race").textValue(),
            head.get("str").intValue(),
            head.get("dex").intValue(),
            head.get("con").intValue(),
            head.get("intel").intValue(),
            head.get("wis").intValue(),
            head.get("cha").intValue(),
            head.get("ac").intValue(),
            head.get("init").intValue(),
            head.get("speed").intValue(),
            head.get("maxHp").intValue(),
            skills,
            languages,
            tools,
            items,
            features,
            clazzes
        )

    }

    constructor() : this(null) {}
}

class ClazzDeserializer constructor(vc: Class<*>? = null) : StdDeserializer<Clazz>(vc) {
    override fun deserialize(p0: JsonParser, p1: DeserializationContext?): Clazz {
        val mapper = ObjectMapper()
        val head: JsonNode = p0.codec.readTree(p0)
        val level = mapper.convertValue(head.get("level"), object : TypeReference<Int>() {})
        val name = head.get("name").textValue()
        return Clazz(level, name)
    }
}