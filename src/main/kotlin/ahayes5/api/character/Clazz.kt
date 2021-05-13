package ahayes5.api.character

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = ClazzDeserializer::class)
class Clazz(@field:JsonProperty val level: Int, @field:JsonProperty val name: String) {}