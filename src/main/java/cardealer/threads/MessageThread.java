package cardealer.threads;

import java.util.List;

/**
 * @author Moussa
 */
public class MessageThread extends Thread {
    private Runnable runnable;

    MessageThread(List<String> list, int numMessagesToSend, Connection connection) {
        this.runnable = new MessageRunnable(list, numMessagesToSend, connection);
    }

    @Override
    public void run() {
        runnable.run();
    }
}
