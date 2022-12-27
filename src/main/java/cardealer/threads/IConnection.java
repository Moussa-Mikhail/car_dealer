package cardealer.threads;

import java.io.Closeable;

/**
 * @author Moussa
 */
public interface IConnection extends Closeable {
    @Override
    void close();
}
