package ahayes5.api.character

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.io.IOException
import java.text.SimpleDateFormat


class CharacterDeserializer constructor(vc: Class<*>? = null) : StdDeserializer<Character>(vc) {
    override fun deserialize(jsonparser: JsonParser, context: DeserializationContext?): Character {
        val mapper = ObjectMapper()
        val head: JsonNode = jsonparser.codec.readTree(jsonparser)
        val skills = mapper.convertValue(head.get("skills"),object: TypeReference<List<String>>(){})
        val languages = mapper.convertValue(head.get("languages"),object: TypeReference<List<String>>(){})
        val tools = mapper.convertValue(head.get("tools"),object: TypeReference<List<String>>(){})
        val features = mapper.convertValue(head.get("features"),object: TypeReference<List<String>>(){})
        val items= mapper.convertValue(head.get("items"),object: TypeReference<Map<String,Int>>(){})
        val n = head.get("clazzes")
        val clazzes = mapper.convertValue(n,object:TypeReference<List<Clazz>>(){})

        return Character(head.get("name").textValue(), head.get("background").textValue(),head.get("race").textValue(),
        head.get("str").intValue(),head.get("dex").intValue(),head.get("con").intValue(),head.get("intel").intValue(),
        head.get("wis").intValue(),head.get("cha").intValue(), head.get("ac").intValue(),head.get("init").intValue(),
        head.get("speed").intValue(),head.get("maxHp").intValue(),skills,languages,tools,items,features,clazzes)

    }

    constructor() :this(null){}
}