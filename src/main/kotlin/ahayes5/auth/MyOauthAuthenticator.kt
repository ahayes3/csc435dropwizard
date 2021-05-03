package ahayes5.auth

import ahayes5.api.user.User
import io.dropwizard.auth.Authenticator
import java.util.*

class MyOauthAuthenticator : Authenticator<String, User> {
    override fun authenticate(p0: String?): Optional<User> {
        TODO("Not yet implemented")
    }
}