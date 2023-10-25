package kg.allan.purchasetransactions.exception;

/**
 *
 * @author allan
 */
public class ConversionFailedException extends Exception {

    public ConversionFailedException(Throwable cause) {
        super(cause);
    }

    public ConversionFailedException(String message) {
        super(message);
    }

    public ConversionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
