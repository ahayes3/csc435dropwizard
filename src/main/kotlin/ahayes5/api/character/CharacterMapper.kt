package ahayes5.api.character

import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import java.sql.ResultSet
import ahayes5.api.character.Character

class CharacterMapper : RowMapper<Character> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): Character {
        return Character(rs?.getLong("id"), rs?.getString("name"),rs?.getString("background"),rs?.getString("race"),rs?.getInt("str"),
            rs?.getInt("dex"),rs?.getInt("con"),rs?.getInt("intel"),rs?.getInt("wis"),rs?.getInt("cha"),rs?.getInt("ac"),
            rs?.getInt("init"),rs?.getInt("speed"),rs?.getInt("maxHp"),listOf(),listOf(),listOf(),mapOf(),listOf(),listOf())
    }
}