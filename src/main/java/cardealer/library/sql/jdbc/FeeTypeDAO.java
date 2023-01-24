package cardealer.library.sql.jdbc;

import cardealer.library.models.FeeType;
import cardealer.library.sql.IFeeTypeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class FeeTypeDAO extends AbstractDAO<FeeType> implements IFeeTypeDAO {
    private static final String TABLE_NAME = "fee_type";
    private static final List<String> COLUMN_NAMES = List.of("name", "description");

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected FeeType createEntityFromRow(ResultSet rs) throws SQLException {
        FeeType feeType = new FeeType();
        feeType.setId(rs.getLong("fee_type_id"));
        feeType.setDescription(rs.getString("description"));
        return feeType;
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, FeeType feeType) throws SQLException {
        ps.setString(1, feeType.getDescription());
    }
}
