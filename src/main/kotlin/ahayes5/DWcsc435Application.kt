package ahayes5

import ahayes5.api.character.*
import ahayes5.api.user.User
import ahayes5.auth.MyAuthorizer
import ahayes5.auth.MyOauthAuthenticator
import ahayes5.resources.character.CharacterResource
import io.dropwizard.Application
import io.dropwizard.auth.AuthDynamicFeature
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter
import io.dropwizard.jdbi3.JdbiFactory
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import org.jdbi.v3.core.statement.SqlLogger
import org.jdbi.v3.core.statement.StatementContext

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
        jdbi.setSqlLogger(myLogger)
        val mydao = jdbi.onDemand(CharacterDAO::class.java)
        environment?.jersey()?.register(CharacterResource(mydao))
        jdbi.registerRowMapper(CharacterMapper())
        jdbi.registerRowMapper(ItemsMapper())
        jdbi.registerRowMapper(ItemNameMapper())
        jdbi.registerRowMapper(ItemQtyMapper())
        jdbi.registerRowMapper(ClazzMapper())

        environment?.jersey()?.register(JsonProcessingExceptionMapper(true))


        environment?.jersey()?.register(AuthDynamicFeature(
            OAuthCredentialAuthFilter.Builder<User>()
                .setAuthenticator(MyOauthAuthenticator())
                .setAuthorizer(MyAuthorizer())
                .setPrefix("Bearer")
                .buildAuthFilter()
        ))
    }
}