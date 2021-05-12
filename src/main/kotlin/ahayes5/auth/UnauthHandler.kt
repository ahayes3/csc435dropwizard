package ahayes5.auth

import ahayes5.resources.error.ResourceError
import io.dropwizard.auth.UnauthorizedHandler
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

class UnauthHandler : UnauthorizedHandler{
    override fun buildResponse(p0: String?, p1: String?): Response {
        return Response.status(401).type(MediaType.APPLICATION_JSON_PATCH_JSON_TYPE).entity(ResourceError(401,"Unauthorized").toJson()).build()
    }
}