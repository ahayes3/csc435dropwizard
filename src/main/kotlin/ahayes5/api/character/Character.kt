package ahayes5.api.character

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.*

@JsonDeserialize(using = CharacterDeserializer::class)
class Character constructor(
    @JsonProperty("name")name: String?, @JsonProperty("background")background: String?, @JsonProperty("race")race: String?, @JsonProperty("str")str: Int?, @JsonProperty("dex")dex: Int?,
    @JsonProperty("con")con: Int?, @JsonProperty("intel")intel: Int?, @JsonProperty("wis")wis: Int?, @JsonProperty("cha")cha: Int?, @JsonProperty("ac")ac: Int?, @JsonProperty("init")init: Int?,
    @JsonProperty("speed")speed: Int?, @JsonProperty("maxHp")maxHp: Int?, @JsonProperty("skills")skills: List<String>, @JsonProperty("languages")languages: List<String>,
    @JsonProperty("tools")tools: List<String>,
    @JsonProperty("items")items: Map<String,Int>,
    @JsonProperty("features")features: List<String>,
    @JsonProperty("classes") clazzes: List<Clazz>
) {


    @JsonProperty("id")
    var id: Long? = null

    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("background")
    var background: String? = null

    @JsonProperty("race")
    var race: String? = null

    @JsonProperty("str")
    var str: Int? = null

    @JsonProperty("dex")
    var dex: Int? = null

    @JsonProperty("con")
    var con: Int? = null

    @JsonProperty("intel")
    var intel: Int? = null

    @JsonProperty("wis")
    var wis: Int? = null

    @JsonProperty("cha")
    var cha: Int? = null

    @JsonProperty("ac")
    var ac: Int? = null

    @JsonProperty("init")
    var init: Int? = null

    @JsonProperty("speed")
    var speed: Int? = null

    @JsonProperty("maxHp")
    var maxHp: Int? = null

    @JsonProperty("skills")
    var skills: List<String>

    @JsonProperty("languages")
    var languages: List<String>

    @JsonProperty("tools")
    var tools: List<String>

    @JsonProperty("items")
    var items: Map<String,Int>

    @JsonProperty("features")
    var features: List<String>

    @JsonProperty("classes")
    var clazzes: List<Clazz>

    init {
        this.name = name
        this.background = background
        this.race = race
        this.str = str
        this.dex = dex
        this.con = con
        this.intel = intel
        this.wis = wis
        this.cha = cha
        this.ac = ac
        this.init = init
        this.speed = speed
        this.maxHp = maxHp
        this.skills=skills
        this.languages=languages
        this.tools=tools
        this.items=items
        this.features=features
        this.clazzes=clazzes
    }

    @JsonCreator
    constructor(
        @JsonProperty("id") id: Long?,
        @JsonProperty("name")name: String?,
        @JsonProperty("background")background: String?,
        @JsonProperty("race")race: String?,
        @JsonProperty("str")str: Int?,
        @JsonProperty("dex")dex: Int?,
        @JsonProperty("con")con: Int?,
        @JsonProperty("intel")intel: Int?,
        @JsonProperty("wis")wis: Int?,
        @JsonProperty("cha")cha: Int?,
        @JsonProperty("ac")ac: Int?,
        @JsonProperty("init")init: Int?,
        @JsonProperty("speed")speed: Int?,
        @JsonProperty("maxHp")maxHp: Int?,
        @JsonProperty("skills")skills: List<String>,
        @JsonProperty("languages")languages: List<String>,
        @JsonProperty("tools")tools: List<String>,
        @JsonProperty("items")items: Map<String,Int>,
        @JsonProperty("features")features: List<String>,
        @JsonProperty("classes") clazzes: List<Clazz>
    ) : this(name, background, race, str, dex, con, intel, wis, cha, ac, init, speed, maxHp,skills, languages, tools,
        items, features,clazzes) {
        this.id = id
    }



}