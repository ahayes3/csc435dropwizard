package ahayes5.resources.character

import ahayes5.api.character.Character
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import ahayes5.api.character.CharacterDAO
import ahayes5.api.user.User
import ahayes5.resources.JSONView
import ahayes5.resources.error.ResourceError
import io.dropwizard.auth.Auth
import java.lang.IllegalArgumentException
import javax.ws.rs.core.Response

@Path("/characters")
@Produces(MediaType.APPLICATION_JSON)
class CharacterResource(private val dao: CharacterDAO) {
    @GET
    @Path("/{id}")
    fun getId(@Auth u:User,@PathParam("id") id:Long):Response {
        if(!dao.getAllCid().contains(id))
            return Response.status(404,ResourceError(404,"Character $id not found").toJson()).build()
        else if(!dao.getCharacters(u.name).contains(id))
            return Response.status(403,ResourceError(403,"Not authorized to access character.").toJson()).build()
        val c = getChar(id)
        return Response.ok(JSONView(c)).build()
    }

    @GET
    fun get(@Auth u: User):Response {
        val ids = dao.getCharacters(u.name)
        return Response.ok(JSONView(ids)).build()
    }
    @POST
    fun create(@Auth u:User,c:Character): Response {
        val res = try {
            createChar(c,u)
        }catch (e:Exception) {
            e.printStackTrace()
        }

        return Response.ok(JSONView(res)).build()

    }

    @DELETE
    @Path("/{id}")
    fun delete(@Auth u:User,@PathParam("id")id:Long):Response {
        if(!dao.getAllCid().contains(id))
            return Response.status(404,ResourceError(404,"Character $id not found").toJson()).build()
        else if(!dao.getCharacters(u.name).contains(id))
            return Response.status(403,ResourceError(403,"Not authorized to access character.").toJson()).build()
        val c = getId(u,id)
        dao.deleteChar(id)
        dao.deleteSkills(id)
        dao.deleteFeatures(id)
        dao.deleteTools(id)
        dao.deleteLanguages(id)
        dao.deleteItems(id)
        dao.deleteClazzes(id)
        return Response.ok(JSONView(c)).build()
    }

    @PUT
    @Path("/{id}")
    fun update(@Auth u:User,c:Character,@PathParam("id")id:Long): Response {
        if(!dao.getAllCid().contains(id))
            return Response.status(404,ResourceError(404,"Character $id not found").toJson()).build()
        else if(!dao.getCharacters(u.name).contains(id))
            return Response.status(403,ResourceError(403,"Not authorized to access character.").toJson()).build()

        dao.deleteSkills(id)
        dao.deleteFeatures(id)
        dao.deleteTools(id)
        dao.deleteLanguages(id)
        dao.deleteItems(id)
        dao.deleteClazzes(id)

        dao.updateChar(id,c.name,c.background,c.race,c.str,c.dex,c.con,c.intel,c.wis,c.cha,c.ac,c.init,c.speed,c.maxHp)

        dao.insertSkills(id,c.skills)
        dao.insertFeatures(id,c.features)
        dao.insertTools(id,c.tools)
        dao.insertLanguages(id,c.languages)
        dao.insertItems(id,c.items.keys,c.items.values)
        dao.insertClazzes(id,c.clazzes.map {it.level},c.clazzes.map { it.name })

        return Response.ok(JSONView(c)).build()
    }

    private fun createChar(c:Character,u:User):Character {

        val id = dao.create(c.name,c.background,c.race,c.str,c.dex,c.con,c.intel,c.wis,c.cha,c.ac,c.init,c.speed,c.maxHp)
        c.id = id
        dao.insertSkills(id,c.skills)
        dao.insertFeatures(id,c.features)
        dao.insertTools(id,c.tools)
        dao.insertLanguages(id,c.languages)
        dao.insertItems(id,c.items.keys,c.items.values)
        dao.insertClazzes(id,c.clazzes.map { it.level },c.clazzes.map {it.name} )
        dao.link(id,u.name)
        return c
    }

    private fun getChar(id:Long):Character {
        val c = dao.findById(id)
        c.skills = dao.getSkills(id)
        c.languages = dao.getLanguages(id)
        c.tools = dao.getTools(id)
        c.features = dao.getFeatures(id)
        c.items = dao.getItems(id)
        c.clazzes = dao.getClazzes(id)
        return c
    }
}