package kg.allan.purchasetransactions.exception;

/**
 *
 * @author allan
 */
public class FetchFailedException extends Exception {

    public FetchFailedException(Throwable cause) {
        super(cause);
    }
    
    public FetchFailedException(String message) {
        super(message);
    }

    public FetchFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
