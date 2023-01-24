package library.sql.jdbc;

import library.models.Checkout;
import library.sql.ICheckoutDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class CheckoutDAO extends AbstractDAO<Checkout> implements ICheckoutDAO {
    private static final String TABLE_NAME = "checkout";
    private static final List<String> COLUMN_NAMES = List.of("member_id", "book_id", "employee_id", "return_status_id", "checkout_date", "due_date", "returned_date");

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, Checkout checkout) throws SQLException {
        ps.setLong(1, checkout.getMemberId());
        ps.setLong(2, checkout.getBookId());
        ps.setLong(3, checkout.getEmployeeId());
        ps.setLong(4, checkout.getReturnStatusId());
        ps.setDate(5, checkout.getCheckoutDate());
        ps.setDate(6, checkout.getDueDate());
        ps.setDate(7, checkout.getReturnedDate());
    }

    @Override
    protected Checkout createEntityFromRow(ResultSet rs) throws SQLException {
        Checkout checkout = new Checkout();
        checkout.setId(rs.getLong("checkout_id"));
        checkout.setMemberId(rs.getLong("member_id"));
        checkout.setBookId(rs.getLong("book_id"));
        checkout.setEmployeeId(rs.getLong("employee_id"));
        checkout.setReturnStatusId(rs.getLong("return_status_id"));
        checkout.setCheckoutDate(rs.getDate("checkout_date"));
        checkout.setDueDate(rs.getDate("due_date"));
        checkout.setReturnedDate(rs.getDate("returned_date"));
        return checkout;
    }
}
