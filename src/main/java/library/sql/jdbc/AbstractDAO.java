package library.sql.jdbc;

import library.models.IdGettable;
import library.sql.ConnectionPool;
import library.sql.IBaseDAO;
import library.sql.SetPreparedStatement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public abstract class AbstractDAO<T extends IdGettable> implements IBaseDAO<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public T getEntityById(long id) throws SQLException {
        return getEntityById(id, getTableName());
    }

    /**
     * @return gets Table Name from child class.
     */
    protected abstract String getTableName();

    protected T getEntityById(long id, String tableName) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String query = QueryUtil.entityByIdQuery(tableName);

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("No entity found with id " + id);
                }
                return createEntityFromRow(rs);
            }
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    /**
     * Creates an entity from a row in the ResultSet.
     * Does not check if the ResultSet is empty.
     *
     * @param rs ResultSet from which to create the entity.
     * @return The entity created from the first if any row of the ResultSet.
     * @throws SQLException If a database access error occurs or this method is called on a closed result set.
     */
    protected abstract T createEntityFromRow(ResultSet rs) throws SQLException;

    @Override
    public void updateEntity(T entity) throws SQLException {
        String tableName = getTableName();
        List<String> columnNames = getColumnNames();
        String query = QueryUtil.updateQuery(tableName, columnNames);
        executeCommand(query, entity, this::setUpdatePreparedStatement);
    }

    /**
     * @return gets Column Names from child class.
     */
    protected abstract List<String> getColumnNames();

    /**
     * @param query                The query to execute. Can be an INSERT, UPDATE or DELETE query.
     * @param entity               The entity to use to set the PreparedStatement.
     * @param setPreparedStatement The method to set the PreparedStatement. Accepts a PreparedStatement and an entity.
     */
    private void executeCommand(String query, T entity, SetPreparedStatement<T> setPreparedStatement) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            setPreparedStatement.setValues(ps, entity);
            ps.executeUpdate();
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    protected void setUpdatePreparedStatement(PreparedStatement preparedStatement, T entity) throws SQLException {
        setCreatePreparedStatement(preparedStatement, entity);
        preparedStatement.setLong(getColumnNames().size() + 1, entity.getId());
    }

    /**
     * @param ps     PreparedStatement to set values for.
     * @param entity Entity to set values from.
     * @throws SQLException If a database access error occurs or this method is called on a closed result set.
     */
    protected abstract void setCreatePreparedStatement(PreparedStatement ps, T entity) throws SQLException;

    @Override
    public void createEntity(T entity) throws SQLException {
        String tableName = getTableName();
        List<String> columnNames = getColumnNames();
        String query = QueryUtil.createQuery(tableName, columnNames);
        executeCommand(query, entity, this::setCreatePreparedStatement);
    }

    @Override
    public void deleteEntity(long id) throws SQLException {
        String tableName = getTableName();
        Connection connection = CONNECTION_POOL.getConnection();
        String query = QueryUtil.deleteQuery(tableName);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public List<T> getAllEntities() throws SQLException {
        String tableName = getTableName();
        Connection connection = CONNECTION_POOL.getConnection();
        String query = QueryUtil.selectAllQuery(tableName);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                List<T> entities = new ArrayList<>();
                while (rs.next()) {
                    T entity = createEntityFromRow(rs);
                    entities.add(entity);
                }
                return entities;
            }
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
