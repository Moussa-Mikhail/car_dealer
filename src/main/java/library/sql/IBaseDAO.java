package library.sql;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public interface IBaseDAO<T> {
    /**
     * @param id the id of the object to be retrieved
     * @return The object if found, throws otherwise
     */
    T getEntityById(long id) throws SQLException;

    /**
     * @param entity Entity to be updated
     */
    void updateEntity(T entity) throws SQLException;

    /**
     * @param entity Entity to be created
     */
    void createEntity(T entity) throws SQLException;

    /**
     * @param id the id of the object to be deleted
     */
    void deleteEntity(long id) throws SQLException;

    /**
     * @return List of all entities
     */
    List<T> getAllEntities() throws SQLException;
}

