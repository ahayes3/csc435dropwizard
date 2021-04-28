package ahayes5.api.character

import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory
import java.util.*

class CharacterDAO(factory: SessionFactory): AbstractDAO<Character>(factory) {
    fun findById(id: UUID): Character {
        return get(id)
    }
    fun create(c: Character): Character {
        //give id
//        val used = usedIds()
//        var uuid:UUID
//        do {
//            uuid = UUID.randomUUID()
//        }while(used.contains(uuid))
//        c.setId(uuid)
        c.setId(UUID.randomUUID())
        return persist(c)
    }
    private fun usedIds():List<UUID> {
        TODO()
    }
}