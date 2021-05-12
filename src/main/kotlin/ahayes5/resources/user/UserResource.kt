package ahayes5.resources.user

import ahayes5.api.user.User
import ahayes5.api.user.UserDAO
import ahayes5.resources.JSONView
import ahayes5.resources.error.ResourceError
import com.lambdaworks.crypto.SCryptUtil
import io.dropwizard.auth.Auth
import io.dropwizard.jersey.sessions.Session
import org.eclipse.jetty.server.session.SessionHandler

import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import kotlin.random.Random

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
class UserResource(val dao: UserDAO) {

    @POST
    fun createUser(u:User):Response {
        val used = dao.getUsernames()
        if(used.contains(u.user))
            return Response.status(409).entity(ResourceError(409,"Username "+ u.user + " taken.")).build()
        dao.createUser(u.user,u.hash)
        return Response.ok(JSONView(u)).build()
    }

    @PUT
    fun changePassAndName(@Auth u:User,newUser:User):Response {
        val used = dao.getUsernames()
        if(used.contains(newUser.name))
            return Response.status(409).entity(ResourceError(409,"Username "+ u.user + " taken.")).build()

        dao.updateUser(u.name,newUser.name,newUser.hash)
        dao.changeOwner(u.name,newUser.name)
        return Response.ok(JSONView(newUser)).build()
    }
}