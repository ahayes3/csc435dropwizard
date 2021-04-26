package ahayes5

import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import ahayes5.DWcsc435Application
import ahayes5.resources.CharacterResource
import io.dropwizard.hibernate.HibernateBundle

fun main(args:Array<String>) {
    DWcsc435Application.run(*args)
}
object DWcsc435Application : Application<CharacterSheetConfiguration>() {


    override fun initialize(bootstrap: Bootstrap<CharacterSheetConfiguration>) {

    }

    override fun run(configuration: CharacterSheetConfiguration?, environment: Environment?) {
        val cr = CharacterResource()
        environment?.jersey()?.register(cr)


    }
}