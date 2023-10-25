package kg.allan.purchasetransactions.exception;

/**
 *
 * @author allan
 */
public class ElementNotFoundException extends Exception {

    public ElementNotFoundException(Throwable cause) {
        super(cause);
    }

    public ElementNotFoundException(String message) {
        super(message);
    }

    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
