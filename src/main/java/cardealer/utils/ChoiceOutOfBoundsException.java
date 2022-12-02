package cardealer.utils;

/**
 * @author Moussa
 */
public class ChoiceOutOfBoundsException extends InvalidInputException {
    public ChoiceOutOfBoundsException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public ChoiceOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
