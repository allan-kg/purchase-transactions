package kg.allan.purchasetransactions.exception;

/**
 *
 * @author allan
 */
public class GetRequestException extends Exception {

    public GetRequestException(Throwable cause) {
        super(cause);
    }
    
    public GetRequestException(String message) {
        super(message);
    }

    public GetRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
