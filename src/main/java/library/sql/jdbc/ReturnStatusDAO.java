package library.sql.jdbc;

import library.models.ReturnStatus;
import library.sql.IReturnStatusDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class ReturnStatusDAO extends AbstractDAO<ReturnStatus> implements IReturnStatusDAO {
    private static final String TABLE_NAME = "return_status";
    private static final List<String> COLUMN_NAMES = List.of("name", "description");

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected ReturnStatus createEntityFromRow(ResultSet rs) throws SQLException {
        ReturnStatus returnStatus = new ReturnStatus();
        returnStatus.setId(rs.getLong("return_status_id"));
        returnStatus.setDescription(rs.getString("description"));
        return returnStatus;
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, ReturnStatus returnStatus) throws SQLException {
        ps.setString(1, returnStatus.getDescription());
    }
}
