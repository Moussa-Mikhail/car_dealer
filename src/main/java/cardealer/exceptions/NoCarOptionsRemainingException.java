package cardealer.exceptions;

/**
 * @author Moussa
 */
public class NoCarOptionsRemainingException extends CarOptionsException {
    public NoCarOptionsRemainingException(String message) {
        super(message);
    }

    public NoCarOptionsRemainingException(String message, Throwable cause) {
        super(message, cause);
    }
}
