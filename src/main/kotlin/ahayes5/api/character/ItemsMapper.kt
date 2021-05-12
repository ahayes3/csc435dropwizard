package ahayes5.api.character

import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import java.sql.ResultSet


class ItemsMapper : RowMapper<HashMap<String,Int>> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): HashMap<String, Int> {
        val map = HashMap<String,Int>()
        while(rs?.isLast != true) {
            TODO()
        }
        return map
    }

}

class ItemNameMapper: RowMapper<String> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): String? {
        return rs?.getString("name")
    }
}

class ItemQtyMapper: RowMapper<Int> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): Int? {
        return rs?.getInt("qty")
    }
}