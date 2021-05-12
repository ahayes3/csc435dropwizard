package ahayes5.auth

import ahayes5.api.user.User
import ahayes5.api.user.UserDAO
import com.lambdaworks.crypto.SCrypt
import com.lambdaworks.crypto.SCryptUtil
import io.dropwizard.auth.Authenticator
import io.dropwizard.auth.basic.BasicCredentials
import javassist.NotFoundException
import java.util.*
import kotlin.random.Random

class MyAuthenticator(val dao: UserDAO) : Authenticator<BasicCredentials, User> {
    override fun authenticate(p0: BasicCredentials): Optional<User> {
        val hash = try {
            dao.getHash(p0.username)
        } catch(e:NotFoundException) {
            return Optional.empty()
        } ?: return Optional.empty()
        if(SCryptUtil.check(p0.password,hash)) {
            println("AUTHENTICATED: ${p0.username}")
            return Optional.of(User(p0.username))
        }
        return Optional.empty()
    }

}