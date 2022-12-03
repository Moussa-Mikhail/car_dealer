package cardealer.exceptions;

/**
 * @author Moussa
 */
public class CarOptionsException extends RuntimeException {
    public CarOptionsException(String message) {
        super(message);
    }

    public CarOptionsException(String message, Throwable cause) {
        super(message, cause);
    }
}
