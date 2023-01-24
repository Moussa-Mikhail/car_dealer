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
import java.util.Optional;

/**
 * @author Moussa
 */
public abstract class AbstractDAO<T extends IdGettable> implements IBaseDAO<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public Optional<T> getEntityById(long id) {
        return getEntityById(id, getTableName());
    }

    /**
     * @return gets Table Name from child class.
     */
    protected abstract String getTableName();

    protected Optional<T> getEntityById(long id, String tableName) {
        Connection connection = CONNECTION_POOL.getConnection();
        String query = entityByIdQuery(tableName);

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                T entity = createEntityFromRow(rs);
                return Optional.of(entity);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Optional.empty();
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    protected String entityByIdQuery(String tableName) {
        return String.format("SELECT * FROM %s WHERE %s_id = (?)", tableName, tableName);
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
    public boolean updateEntity(T entity) {
        String tableName = getTableName();
        List<String> columnNames = getColumnNames();
        String query = updateQuery(tableName, columnNames);
        return executeUpdate(query, entity, this::setUpdatePreparedStatement);
    }

    /**
     * @return gets Column Names from child class.
     */
    protected abstract List<String> getColumnNames();

    /**
     * @param query                The query to execute. Can be an INSERT, UPDATE or DELETE query.
     * @param entity               The entity to use to set the PreparedStatement.
     * @param setPreparedStatement The method to set the PreparedStatement. Accepts a PreparedStatement and an entity.
     * @return True if the entity was inserted/updated/deleted successfully, false otherwise.
     */
    private boolean executeUpdate(String query, T entity, SetPreparedStatement<T> setPreparedStatement) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            setPreparedStatement.setValues(ps, entity);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    protected String updateQuery(String tableName, List<String> columnNames) {
        StringBuilder query = new StringBuilder(String.format("UPDATE %s SET ", tableName));
        for (int i = 0; i < columnNames.size(); i++) {
            query.append(columnNames.get(i)).append(" = (?)");
            if (i != columnNames.size() - 1) {
                query.append(", ");
            }
        }
        query.append(String.format(" WHERE %s_id = (?)", tableName));
        return query.toString();
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
    public boolean createEntity(T entity) {
        String tableName = getTableName();
        List<String> columnNames = getColumnNames();
        String query = createQuery(tableName, columnNames);
        return executeUpdate(query, entity, this::setUpdatePreparedStatement);
    }

    protected String createQuery(String tableName, List<String> columnNames) {
        StringBuilder query = new StringBuilder(String.format("INSERT INTO %s (", tableName));
        for (int i = 0; i < columnNames.size(); i++) {
            query.append(columnNames.get(i));
            if (i != columnNames.size() - 1) {
                query.append(", ");
            }
        }
        query.append(") VALUES (");
        for (int i = 0; i < columnNames.size(); i++) {
            query.append("?");
            if (i != columnNames.size() - 1) {
                query.append(", ");
            }
        }
        query.append(")");
        return query.toString();
    }

    @Override
    public boolean deleteEntity(long id) {
        String tableName = getTableName();
        Connection connection = CONNECTION_POOL.getConnection();
        String query = deleteQuery(tableName);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    protected static String deleteQuery(String tableName) {
        return String.format("DELETE FROM %s WHERE %s_id = (?)", tableName, tableName);
    }

    @Override
    public List<T> getAllEntities() throws SQLException {
        String tableName = getTableName();
        Connection connection = CONNECTION_POOL.getConnection();
        String query = selectAllQuery(tableName);
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

    protected static String selectAllQuery(String tableName) {
        return String.format("SELECT * FROM %s", tableName);
    }
}
