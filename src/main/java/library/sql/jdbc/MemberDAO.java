package library.sql.jdbc;

import library.models.Member;
import library.sql.IMemberDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class MemberDAO extends AbstractDAO<Member> implements IMemberDAO {
    private static final String TABLE_NAME = "member";
    private static final List<String> COLUMN_NAMES = List.of("first_name", "last_name", "email", "phone_number");

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Member createEntityFromRow(ResultSet rs) throws SQLException {
        Member member = new Member();
        member.setId(rs.getLong("member_id"));
        member.setFirstName(rs.getString("first_name"));
        member.setLastName(rs.getString("last_name"));
        member.setJoinedDate(rs.getDate("joined_date"));
        return member;
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, Member member) throws SQLException {
        ps.setString(1, member.getFirstName());
        ps.setString(2, member.getLastName());
        ps.setDate(3, member.getJoinedDate());
    }
}
