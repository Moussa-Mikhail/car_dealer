package cardealer.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Moussa
 */
public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    Queue<IConnection> pool = new ArrayBlockingQueue<>(10);
    private int numConnections = 0;
    private int maxConnections;

    public ConnectionPool(int maxConnections) {
        if (maxConnections <= 0) {
            throw new IllegalArgumentException("maxConnections must be greater than 0.");
        }

        this.maxConnections = maxConnections;
    }

    public ConnectionWrapper getConnection() {
        if (!pool.isEmpty()) {
            IConnection connection = pool.remove();
            return new ConnectionWrapper(this, connection);
        }

        if (numConnections < maxConnections) {
            numConnections++;
            return new ConnectionWrapper(this, new Connection());
        }

        LOGGER.info("No connections available.");
        throw new IllegalStateException("No more connections available.");
    }

    /* default */ void releaseConnection(IConnection connection) {
        pool.add(connection);
        LOGGER.info("Connection released.");
    }

    public boolean isConnectionAvailable() {
        return !pool.isEmpty() || numConnections < maxConnections;
    }

    public void closeAll() {
        for (IConnection connection : pool) {
            connection.close();
        }

        pool.clear();
        numConnections = 0;
        LOGGER.info("All connections closed.");
    }
}
