package cardealer.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Moussa
 */
public class ConnectionPoolMain {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolMain.class);

    public static void main(String[] args) {
        ConnectionPool connectionPool = new ConnectionPool(5);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(7, 7, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));

        List<String> messages = Collections.synchronizedList(new LinkedList<>());
        for (int i = 0; i < 7; i++) {
            while (!connectionPool.isConnectionAvailable()) {
                LOGGER.info("No connections available. Waiting for a connection to be released.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOGGER.error(e);
                    Thread.currentThread().interrupt();
                }
            }

            IConnection connection = connectionPool.getConnection();
            threadPoolExecutor.execute(new MessageRunnable(messages, 5, connection));
        }

        threadPoolExecutor.shutdown();
        try {
            while (!threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
                LOGGER.info("Waiting for thread pool to terminate.");
            }
        } catch (InterruptedException e) {
            LOGGER.error(e);
            Thread.currentThread().interrupt();
        }
        connectionPool.closeAll();
    }
}
