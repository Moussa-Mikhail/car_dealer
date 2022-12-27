package cardealer.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Moussa
 */
public class ListAppendingThread extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(ListAppendingThread.class);
    private static final String MESSAGE_FORMAT = "ListAppendingThread appends message #%d to list.";
    private final List<String> list;
    private int messageNumber = 0;
    private int numMessagestoSend = 10;

    public ListAppendingThread(List<String> list, int numMessagestoSend) {
        this.list = list;
        this.numMessagestoSend = numMessagestoSend;
    }

    @Override
    public void run() {
        while (messageNumber < numMessagestoSend) {
            String message = String.format(MESSAGE_FORMAT, messageNumber);
            list.add(message);
            messageNumber++;
            LOGGER.info(message);
            if (Thread.interrupted()) {
                LOGGER.info("ListAppendingRunnable interrupted.");
            }
        }
    }
}
