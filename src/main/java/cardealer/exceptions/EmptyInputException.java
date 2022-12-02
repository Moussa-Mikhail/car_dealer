package cardealer.exceptions;

/**
 * @author Moussa
 */
public class EmptyInputException extends InvalidInputException {
    public EmptyInputException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public EmptyInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
