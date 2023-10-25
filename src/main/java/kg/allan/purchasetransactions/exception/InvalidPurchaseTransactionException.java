package kg.allan.purchasetransactions.exception;

/**
 *
 * @author allan
 */
public class InvalidPurchaseTransactionException extends Exception{

    public InvalidPurchaseTransactionException(Throwable cause) {
        super(cause);
    }

    public InvalidPurchaseTransactionException(String message) {
        super(message);
    }
    
    public InvalidPurchaseTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
