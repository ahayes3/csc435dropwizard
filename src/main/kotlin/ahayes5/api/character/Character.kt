package ahayes5.api.character

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

@Entity
@Table(name="characters")
class Character @JsonCreator constructor() {
    @Id
    lateinit var id:UUID
    lateinit var name:String
    lateinit var background: String
    lateinit var race: String
    @ElementCollection
    lateinit var languages: List<String>
    var str:Int? = null
    var dex: Int? = null
    var con: Int? = null
    var intel: Int? = null
    var wis: Int? = null
    var cha: Int? = null
    var ac: Int? = null
    var init: Int? = null
    var speed: Int? = null
    var maxHp: Int? = null
    @ElementCollection
    lateinit var skills: List<String>
    @ElementCollection
    lateinit var tools: List<String>
    @ElementCollection
    lateinit var items: List<String>
    @ElementCollection
    lateinit var features: List<String>
    @OneToMany
    lateinit var classes: List<Clazz>


    @JsonCreator
    constructor(@JsonProperty id:UUID,
                @JsonProperty name: String,
                @JsonProperty background: String,
                @JsonProperty race: String,
                @JsonProperty languages: List<String>,
                @JsonProperty str: Int,
                @JsonProperty dex: Int,
                @JsonProperty con: Int,
                @JsonProperty intel: Int,
                @JsonProperty wis: Int,
                @JsonProperty cha: Int,
                @JsonProperty ac: Int,
                @JsonProperty init: Int,
                @JsonProperty speed: Int,
                @JsonProperty maxHp: Int,
                @JsonProperty skills: List<String>,
                @JsonProperty tools: List<String>,
                @JsonProperty items: List<String>,
                @JsonProperty features: List<String>,
                @JsonProperty classes: List<Clazz>):this() {//:this(name,background,race, languages, str, dex, con, intel, wis, cha, ac, init, speed, maxHp, skillProfs, toolProfs, items, features, classes) {
                    this.id=id
        this.name= name
    }

    constructor(
    @JsonProperty("name") val name: String,
    @JsonProperty("background") val background: String,
    @JsonProperty("race") val race: String,
    @JsonProperty("languages") val languages: List<String>,
    @JsonProperty("str") val str: Int,
    @JsonProperty("dex") val dex: Int,
    @JsonProperty("con") val con: Int,
    @JsonProperty("intel") val intel: Int,
    @JsonProperty("wis") val wis: Int,
    @JsonProperty("cha") val cha: Int,
    @JsonProperty("ac") val ac: Int,
    @JsonProperty("init") val init: Int,
    @JsonProperty("speed") val speed: Int,
    @JsonProperty("maxHp") val maxHp: Int,
    @JsonProperty("skills") val skillProfs: List<String>,
    @JsonProperty("tools") val toolProfs: List<String>,
    @JsonProperty("items") val items: List<String>,
    @JsonProperty("features") val features: List<String>,
    @JsonProperty("classes") val classes: List<Clazz>
    ) : this()

//    @Id
//    var id: UUID? = null

}