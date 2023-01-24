package cardealer.library.sql;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Moussa
 */
public interface IBaseDAO<T> {
    /**
     * @param id the id of the object to be retrieved
     * @return Optional containing the object if found, empty Optional otherwise
     */
    Optional<T> getEntityById(long id);

    /**
     * @param entity Entity to be updated
     * @return whether the update was successful or not
     */
    boolean updateEntity(T entity);

    /**
     * @param entity Entity to be created
     * @return whether the creation was successful or not
     */
    boolean createEntity(T entity);

    /**
     * @param id the id of the object to be deleted
     * @return whether the deletion was successful or not
     */
    boolean deleteEntity(long id);

    /**
     * @return List of all entities
     */
    List<T> getAllEntities() throws SQLException;
}

