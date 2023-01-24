package library.sql.jdbc;

import library.models.Author;
import library.sql.IAuthorDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class AuthorDAO extends AbstractDAO<Author> implements IAuthorDAO {
    private static final String TABLE_NAME = "author";
    private static final List<String> COLUMN_NAMES = new ArrayList<>(List.of("first_name", "last_name"));

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, Author author) throws SQLException {
        ps.setString(1, author.getFirstName());
        ps.setString(2, author.getLastName());
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Author createEntityFromRow(ResultSet rs) throws SQLException {
        Author author = new Author();
        author.setId(rs.getLong("author_id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        return author;
    }
}