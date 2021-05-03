package ahayes5.api.user

import java.security.Principal

class User(val user:String) : Principal{

    override fun getName(): String {
        return user
    }

}