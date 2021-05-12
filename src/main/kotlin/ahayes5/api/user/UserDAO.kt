package ahayes5.api.user

import org.jdbi.v3.sqlobject.config.RegisterRowMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface UserDAO {

    @SqlQuery("select u.name from users u")
    fun getUsernames():List<String>

    @SqlUpdate("insert into users(name,hash) values(:name, :hash)")
    fun createUser(@Bind("name") name:String,@Bind("hash") hash:String)


    @RegisterRowMapper(HashMapper::class)
    @SqlQuery("select u.hash from users u where u.name=:name")
    fun getHash(@Bind("name") name:String):String
}