package ahayes5.resources.character

import ahayes5.api.character.Character
import java.util.*
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import ahayes5.api.character.CharacterDAO
import ahayes5.resources.JSONView
import ahayes5.resources.error.ResourceError
import io.dropwizard.hibernate.UnitOfWork
import java.lang.IllegalArgumentException

@Path("/characters")
@Produces(MediaType.APPLICATION_JSON)
class CharacterResource(private val dao: CharacterDAO) {
    @GET
    @Path("/{id}")
    @UnitOfWork
    fun getId(@PathParam("id") id:String):String {
        return try {
            val a = UUID.fromString(id)
            JSONView(dao.findById(a))
        } catch (e:IllegalArgumentException) {
            ResourceError(400,"Invalid id: $id").toJson()
        }
    }

    @GET
    fun get():String {
        TODO()

        //returns all owned characters
    }
    @POST
    @UnitOfWork
    fun create(c:Character):String {
        val res = dao.create(c)
        return JSONView(res)
    }

}