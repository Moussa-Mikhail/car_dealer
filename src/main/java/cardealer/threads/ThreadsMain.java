package cardealer.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Moussa
 */
public class ThreadsMain {
    private static final Logger LOGGER = LogManager.getLogger(ThreadsMain.class);

    public static void main(String[] args) {
        List<String> messages = Collections.synchronizedList(new ArrayList<>());
        Thread thread = new ListAppendingThread(messages, 5);
        Thread fromRunnable = new Thread(new ListAppendingRunnable(messages, 5));
        thread.start();
        fromRunnable.start();
        try {
            thread.join();
            fromRunnable.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (String message : messages) {
            LOGGER.info(message);
        }
    }
}
