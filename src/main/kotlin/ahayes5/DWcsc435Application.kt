package ahayes5

import ahayes5.api.character.*
import ahayes5.api.user.User
import ahayes5.api.user.UserDAO
import ahayes5.auth.MyAuthenticator
import ahayes5.auth.MyAuthorizer
import ahayes5.auth.UnauthHandler
import ahayes5.resources.character.CharacterResource
import ahayes5.resources.user.UserResource
import io.dropwizard.Application
import io.dropwizard.auth.AuthDynamicFeature
import io.dropwizard.auth.AuthValueFactoryProvider
import io.dropwizard.auth.basic.BasicCredentialAuthFilter
import io.dropwizard.jdbi3.JdbiFactory
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import org.eclipse.jetty.server.session.SessionHandler
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature
import org.jdbi.v3.core.statement.SqlLogger
import org.jdbi.v3.core.statement.StatementContext
import javax.servlet.SessionTrackingMode

fun main(args:Array<String>) {
    DWcsc435Application.run(*args)
}
object DWcsc435Application : Application<CharacterSheetConfiguration>() {

    override fun initialize(bootstrap: Bootstrap<CharacterSheetConfiguration>) {
    }

    override fun run(configuration: CharacterSheetConfiguration?, environment: Environment?) {
        val factory = JdbiFactory()
        val jdbi = factory.build(environment,configuration?.getDataSourceFactory(),"mysql")
        val myLogger = object: SqlLogger{
            override fun logBeforeExecution(context: StatementContext?) {
                println("INFO:   ${context?.renderedSql}")
            }
        }

        val cdao = jdbi.onDemand(CharacterDAO::class.java)
        val udao=jdbi.onDemand(UserDAO::class.java)

        environment?.jersey()?.register(AuthDynamicFeature(
            BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(MyAuthenticator(udao))
                .setAuthorizer(MyAuthorizer())
                .setUnauthorizedHandler(UnauthHandler())
                .setRealm("MyRealm")
                .buildAuthFilter()))
        environment?.jersey()?.register(RolesAllowedDynamicFeature::class.java)
        environment?.jersey()?.register(AuthValueFactoryProvider.Binder(User::class.java))

        jdbi.setSqlLogger(myLogger)



        environment?.jersey()?.register(CharacterResource(cdao))
        environment?.jersey()?.register(UserResource(udao))

        jdbi.registerRowMapper(CharacterMapper())
        jdbi.registerRowMapper(ItemsMapper())
        jdbi.registerRowMapper(ItemNameMapper())
        jdbi.registerRowMapper(ItemQtyMapper())
        jdbi.registerRowMapper(ClazzMapper())

        environment?.jersey()?.register(JsonProcessingExceptionMapper(true))



    }
}