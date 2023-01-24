package library.sql.jdbc;

import library.models.Fee;
import library.sql.IFeeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class FeeDAO extends AbstractDAO<Fee> implements IFeeDAO {
    private static final String TABLE_NAME = "fee";
    private static final List<String> COLUMN_NAMES = List.of("member_id", "fee_type_id", "amount", "is_paid");

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Fee createEntityFromRow(ResultSet rs) throws SQLException {
        Fee fee = new Fee();
        fee.setId(rs.getLong("fee_id"));
        fee.setMemberId(rs.getLong("member_id"));
        fee.setFeeTypeId(rs.getLong("fee_type_id"));
        fee.setAmount(rs.getBigDecimal("amount"));
        fee.setPaid(rs.getBoolean("is_paid"));
        return fee;
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, Fee fee) throws SQLException {
        ps.setLong(1, fee.getMemberId());
        ps.setLong(2, fee.getFeeTypeId());
        ps.setBigDecimal(3, fee.getAmount());
        ps.setBoolean(4, fee.isPaid());
    }
}
