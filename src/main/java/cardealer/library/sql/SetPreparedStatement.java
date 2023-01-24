package cardealer.library.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Moussa
 */
@FunctionalInterface
public interface SetPreparedStatement<T> {
    /**
     * Set the values of the PreparedStatement using the entity.
     *
     * @param ps     PreparedStatement to set.
     * @param entity Entity to set the PreparedStatement with.
     * @throws SQLException see {@link PreparedStatement#setObject(int, Object)}
     */
    void setValues(PreparedStatement ps, T entity) throws SQLException;
}
