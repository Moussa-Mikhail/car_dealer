package cardealer.library.sql.jdbc;

import cardealer.library.models.Ban;
import cardealer.library.sql.IBanDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class BanDAO extends AbstractDAO<Ban> implements IBanDAO {
    private static final String TABLE_NAME = "ban";
    private static final List<String> COLUMN_NAMES = new ArrayList<>(List.of("user_id", "reason", "date"));

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, Ban ban) throws SQLException {
        ps.setLong(1, ban.getMemberId());
        ps.setDate(2, ban.getBannedDate());
        ps.setDate(3, ban.getBannedUntilDate());
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Ban createEntityFromRow(ResultSet rs) throws SQLException {
        Ban ban = new Ban();
        ban.setId(rs.getLong("ban_id"));
        ban.setMemberId(rs.getLong("member_id"));
        ban.setBannedDate(rs.getDate("start_date"));
        ban.setBannedUntilDate(rs.getDate("banned_until_date"));
        return ban;
    }
}
