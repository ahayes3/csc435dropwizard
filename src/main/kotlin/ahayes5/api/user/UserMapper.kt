package ahayes5.api.user

import javassist.NotFoundException
import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import java.sql.ResultSet

class UserMapper : RowMapper<String> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): String {
        if(rs?.isAfterLast==true)
            throw NotFoundException("User not found")
        return rs!!.getString("name")
    }
}

class HashMapper : RowMapper<String> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): String? {
        if(rs?.isAfterLast == true)
            throw NotFoundException("User not found")
        return rs?.getString("hash")
    }
}