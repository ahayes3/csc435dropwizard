package ahayes5

import ahayes5.api.character.CharacterDAO
import ahayes5.resources.character.CharacterResource
import io.dropwizard.Application
import io.dropwizard.db.PooledDataSourceFactory
import io.dropwizard.hibernate.HibernateBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

fun main(args:Array<String>) {
    DWcsc435Application.run(*args)
}
object DWcsc435Application : Application<CharacterSheetConfiguration>() {

    val hibernate = object: HibernateBundle<CharacterSheetConfiguration>(java.lang.Character::class.java) {
        override fun getDataSourceFactory(p0: CharacterSheetConfiguration): PooledDataSourceFactory {
            return p0.getDataSourceFactory()
        }
    }


    override fun initialize(bootstrap: Bootstrap<CharacterSheetConfiguration>) {
        bootstrap.addBundle(hibernate)
    }

    override fun run(configuration: CharacterSheetConfiguration?, environment: Environment?) {
        val cdao = CharacterDAO(hibernate.sessionFactory)
        environment?.jersey()?.register(CharacterResource(cdao))


    }
}