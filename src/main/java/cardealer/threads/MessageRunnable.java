package cardealer.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Moussa
 */
public class MessageRunnable implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(MessageRunnable.class);
    private static final String MESSAGE_FORMAT = "MessageRunnable in %s appends message #%d to list.";
    private final List<String> list;
    private int messageNumber = 0;
    private int numMessagesToSend;
    private IConnection connection;

    public MessageRunnable(List<String> list, int numMessagesToSend, IConnection connection) {
        this.list = list;
        this.numMessagesToSend = numMessagesToSend;
        this.connection = connection;
    }

    @Override
    public void run() {
        while (messageNumber < numMessagesToSend) {
            try {
                // Simulate a long-running operation.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error(e);
                Thread.currentThread().interrupt();
            }

            String threadName = Thread.currentThread().getName();
            String message = String.format(MESSAGE_FORMAT, threadName, messageNumber + 1);
            list.add(message);
            messageNumber++;
            LOGGER.info(message);
        }
        LOGGER.info("MessageRunnable finished.");
        connection.close();
    }
}
