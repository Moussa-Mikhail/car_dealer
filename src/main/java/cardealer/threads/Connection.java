package cardealer.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Moussa
 */
public class Connection implements IConnection {
    private static final Logger LOGGER = LogManager.getLogger(Connection.class);
    // Mock connection class.

    Connection() {
        // Do nothing.
        LOGGER.info("Connection created.");
    }

    @Override
    public void close() {
        // Do nothing.
        LOGGER.info("Connection closed.");
    }
}
