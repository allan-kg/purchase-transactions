package kg.allan.purchasetransactions.exception;

/**
 *
 * @author allan
 */
public class JsonParseException extends Exception {
    public JsonParseException(Throwable cause) {
        super(cause);
    }

    public JsonParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
