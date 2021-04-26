package ahayes5.resources

import java.util.*
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/characters")
@Produces(MediaType.APPLICATION_JSON)
class CharacterResource {
    @GET
    @Path("/{id}")
    fun getId(@PathParam("id") id:Int):String {
        return "IDK"
    }

    @GET
    fun get():String {
        return "IDK2"

        //returns all owned characters
    }
}