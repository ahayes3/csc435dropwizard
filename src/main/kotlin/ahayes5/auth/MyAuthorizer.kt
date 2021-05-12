package ahayes5.auth

import ahayes5.api.user.User
import io.dropwizard.auth.Authorizer

class MyAuthorizer : Authorizer<User> {
    override fun authorize(p0: User, p1: String): Boolean {
        TODO("AUTHORIZER")
    }
}