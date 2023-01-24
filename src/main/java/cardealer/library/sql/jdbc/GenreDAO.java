package cardealer.library.sql.jdbc;

import cardealer.library.models.Genre;
import cardealer.library.sql.IGenreDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class GenreDAO extends AbstractDAO<Genre> implements IGenreDAO {
    private static final String TABLE_NAME = "genre";
    private static final List<String> COLUMN_NAMES = List.of("name");

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Genre createEntityFromRow(ResultSet rs) throws SQLException {
        Genre genre = new Genre();
        genre.setId(rs.getLong("genre_id"));
        genre.setName(rs.getString("name"));
        return genre;
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, Genre genre) throws SQLException {
        ps.setString(1, genre.getName());
    }
}
