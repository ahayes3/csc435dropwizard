package ahayes5.api.character

import org.jdbi.v3.sqlobject.config.RegisterRowMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.jdbi.v3.sqlobject.customizer.BindList
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlBatch
import java.util.*

interface CharacterDAO {
    @SqlUpdate("insert into user_characters(name,id) values(:name,:id)")
    fun link(@Bind("id") id:Long,@Bind("name") name:String)

    @SqlQuery("select c.id from user_characters c where c.name=:name")
    fun getCharacters(@Bind("name") name:String):List<Long>

    @SqlQuery("select c.id from user_characters c")
    fun getAllCid():List<Long>

    @SqlBatch("insert into features(c_id,name) values(:id,:name)")
    fun insertFeatures(@Bind("id")id:Long,@Bind("name") name:List<String>)

    @SqlBatch("insert into languages(c_id,name) values(:id,:name)")
    fun insertLanguages(@Bind("id")id:Long,@Bind("name") name:List<String>)

    @SqlBatch("insert into tools(c_id,name) values(:id,:name)")
    fun insertTools(@Bind("id")id:Long,@Bind("name") name:List<String>)

    @SqlBatch("insert into skills(c_id,name) values(:id,:name)")
    fun insertSkills(@Bind("id")id:Long,@Bind("name") name:List<String>)

    @SqlBatch("insert into items (c_id,name,qty) values(:id, :name,:qty)")
    fun insertItems(@Bind("id") id:Long,@Bind("name") names:Set<String>,@Bind("qty") qty:Collection<Int>)

    @SqlBatch("insert into clazzes(c_id,level,name) values(:id, :level, :name)")
    fun insertClazzes(@Bind("id") id:Long, @Bind("level") level:List<Int>, @Bind("name") name:List<String>)

    @SqlUpdate("insert into characters(id,name,background,race,str,dex,con,intel,wis,cha,ac,init,speed,maxHp)" +
            " values (0,:name, :background, :race,:str,:dex,:con,:intel,:wis,:cha,:ac,:init,:speed,:maxHp)")
    @GetGeneratedKeys("id")
    fun create(@Bind("name")name:String?,@Bind("background") background:String?,
               @Bind("race")race:String?, @Bind("str") str:Int?,@Bind("dex") dex:Int?,@Bind("con")con:Int?,
               @Bind("intel")intel:Int?,@Bind("wis")wis:Int?,@Bind("cha")cha:Int?,@Bind("ac")ac:Int?,
               @Bind("init")init:Int?,@Bind("speed")speed:Int?,@Bind("maxHp")maxHp:Int?):Long

    @SqlQuery("select s.name from skills s where c_id = :id")
    fun getSkills(@Bind("id") id:Long):List<String>

    @SqlQuery("select i.name,i.qty from items i where c_id = :id")
    fun getItems(@Bind("id")id:Long):HashMap<String,Int>

    @SqlQuery("select * from languages where c_id = :id")
    fun getLanguages(@Bind("id") id:Long):List<String>

    @SqlQuery("select * from tools where c_id = :id")
    fun getTools(@Bind("id") id:Long):List<String>

    @SqlQuery("select * from features where c_id = :id")
    fun getFeatures(@Bind("id") id:Long):List<String>

    @SqlQuery("select * from clazzes where c_id = :id")
    fun getClazzes(@Bind("id")id:Long):List<Clazz>

    @SqlUpdate("DELETE FROM characters c WHERE c.id = :id")
    fun deleteChar(@Bind("id") id:Long)

    @SqlUpdate("DELETE FROM user_characters c WHERE c.id=:id")
    fun deleteLink(@Bind("id")id:Long)

    @SqlUpdate("DELETE FROM skills c WHERE c.c_id = :id")
    fun deleteSkills(@Bind("id") id:Long)

    @SqlUpdate("DELETE FROM features c WHERE c.c_id = :id")
    fun deleteFeatures(@Bind("id") id:Long)

    @SqlUpdate("DELETE FROM tools c WHERE c.c_id = :id")
    fun deleteTools(@Bind("id") id:Long)

    @SqlUpdate("DELETE FROM languages c WHERE c.c_id = :id")
    fun deleteLanguages(@Bind("id") id:Long)

    @SqlUpdate("DELETE FROM items c WHERE c.c_id = :id")
    fun deleteItems(@Bind("id") id:Long)

    @SqlUpdate("DELETE FROM clazzes c WHERE c.c_id = :id")
    fun deleteClazzes(@Bind("id") id:Long)

    @SqlQuery("select * from characters where id = :id")
    @RegisterRowMapper(CharacterMapper::class)
    fun findById(@Bind("id") id:Long):Character

    @SqlUpdate("update characters set name=:name, background=:background, race=:race, str=:str, dex=:dex," +
            "con=:con,intel=:intel,wis=:wis,cha=:cha,ac=:ac,init=:init,speed=:speed,maxHp=:maxHp where id=:id")
    fun updateChar(@Bind("id")id:Long,@Bind("name")name:String?,@Bind("background") background:String?,
                   @Bind("race")race:String?, @Bind("str") str:Int?,@Bind("dex") dex:Int?,@Bind("con")con:Int?,
                   @Bind("intel")intel:Int?,@Bind("wis")wis:Int?,@Bind("cha")cha:Int?,@Bind("ac")ac:Int?,
                   @Bind("init")init:Int?,@Bind("speed")speed:Int?,@Bind("maxHp")maxHp:Int?)
}