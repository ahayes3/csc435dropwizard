package ahayes5.resources.character

import ahayes5.api.character.Character
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import ahayes5.api.character.CharacterDAO
import ahayes5.resources.JSONView
import ahayes5.resources.error.ResourceError
import java.lang.IllegalArgumentException
import javax.ws.rs.core.Response

@Path("/characters")
@Produces(MediaType.APPLICATION_JSON)
class CharacterResource(private val dao: CharacterDAO) {
    @GET
    @Path("/{id}")
    fun getId(@PathParam("id") id:Long):Response {
        return try {
            val c = getChar(id)
            return Response.ok(JSONView(c)).build()
        } catch (e:IllegalArgumentException) {
            Response.status(400).entity(ResourceError(400,"Invalid id: $id").toJson()).build()
        }
    }

    @GET
    fun get():String {
        TODO()

        //returns all owned characters
    }
    @POST
    fun create(c:Character): Response {
        println("READ JSON")
        val res = try {
            createChar(c)
        }catch (e:Exception) {
            e.printStackTrace()
        }

        return Response.ok(JSONView(res)).build()

    }

    @DELETE
    @Path("/{id}")
    fun delete(@PathParam("id")id:Long):Response {
        TODO()
        //must be logged in (401)
        //must exists (404)
        //must be owned (403)
        val c = getId(id)
        dao.deleteChar(id)
        dao.deleteSkills(id)
        dao.deleteFeatures(id)
        dao.deleteTools(id)
        dao.deleteLanguages(id)
        dao.deleteItems(id)
        dao.deleteClazzes(id)
        return Response.ok(JSONView(c)).build()
    }

    private fun createChar(c:Character):Character {

        val id = dao.create(c.name,c.background,c.race,c.str,c.dex,c.con,c.intel,c.wis,c.cha,c.ac,c.init,c.speed,c.maxHp)
        c.id = id
        dao.insertSkills(id,c.skills)
        dao.insertFeatures(id,c.features)
        dao.insertTools(id,c.tools)
        dao.insertLanguages(id,c.languages)
        dao.insertItems(id,c.items.keys,c.items.values)
        dao.insertClazzes(id,c.clazzes.map { it.level },c.clazzes.map {it.name} )
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