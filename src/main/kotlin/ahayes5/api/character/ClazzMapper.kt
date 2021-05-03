package ahayes5.api.character

import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import java.lang.NullPointerException
import java.sql.ResultSet

class ClazzMapper : RowMapper<Clazz> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): Clazz {
        if(rs == null)
            throw NullPointerException("Result set null in clazz mapper")
        return Clazz(rs.getInt("level"), rs.getString("name"))
    }
}