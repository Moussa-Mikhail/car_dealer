package library.sql.jdbc;

import library.models.Book;
import library.sql.ConnectionPool;
import library.sql.IBookDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class BookDAO extends AbstractDAO<Book> implements IBookDAO {
    public static final String TABLE_NAME = "book";
    public static final List<String> COLUMN_NAMES = List.of("title", "author_id", "genre_id", "isbn", "quantity");
    private static final Logger LOGGER = LogManager.getLogger(BookDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, Book book) throws SQLException {
        ps.setString(1, book.getTitle());
        ps.setLong(2, book.getAuthorId());
        ps.setLong(3, book.getGenreId());
        ps.setString(4, book.getIsbn());
        ps.setInt(5, book.getQuantity());
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    public Book getBookByISBN(String isbn) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM book WHERE isbn = (?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, isbn);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("No book found with isbn " + isbn);
                }
                return createEntityFromRow(rs);
            }
        } finally {
            try {
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    protected Book createEntityFromRow(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthorId(rs.getLong("author_id"));
        book.setGenreId(rs.getLong("genre_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setQuantity(rs.getInt("quantity"));
        return book;
    }
}
