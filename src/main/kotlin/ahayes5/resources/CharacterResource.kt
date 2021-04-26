package ahayes5.resources

import java.util.*
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/characters")
@Produces(MediaType.APPLICATION_JSON)
class CharacterResource {
    @GET
    fun test(@QueryParam("id") id:Optional<String>):String {
        return id.get()
    }
}