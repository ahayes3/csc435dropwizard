package ahayes5.api.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.dropwizard.jersey.sessions.Session
import java.security.Principal
@JsonDeserialize(using = UserDeserializer::class)
@JsonIgnoreProperties(ignoreUnknown = true)
class User(@JsonIgnore val user:String) : Principal{
    @JsonIgnore
    lateinit var hash:String
    override fun getName(): String {
        return user
    }
}