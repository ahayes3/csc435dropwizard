package ahayes5.api.character

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="classes")
class Clazz() {
    @Id
    var id: Long = TODO("initialize me")
    var level:Int?
    var name:String

    constructor(@JsonProperty("level") level:Int, @JsonProperty("name") name:String): this() {
        this.level = level
        this.name = name
    }
}