package library.sql.jdbc;

import library.models.BookDonation;
import library.sql.IBookDonationDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class BookDonationDAO extends AbstractDAO<BookDonation> implements IBookDonationDAO {
    private static final String TABLE_NAME = "book_donation";
    private static final List<String> COLUMN_NAMES = List.of("member_id", "book_id", "donated_date");

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, BookDonation bookDonation) throws SQLException {
        ps.setLong(1, bookDonation.getMemberId());
        ps.setLong(2, bookDonation.getBookId());
        ps.setDate(3, bookDonation.getDonatedDate());
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected BookDonation createEntityFromRow(ResultSet rs) throws SQLException {
        BookDonation bookDonation = new BookDonation();
        bookDonation.setId(rs.getLong("book_donation_id"));
        bookDonation.setMemberId(rs.getLong("member_id"));
        bookDonation.setBookId(rs.getLong("book_id"));
        bookDonation.setDonatedDate(rs.getDate("donated_date"));
        return bookDonation;
    }
}
