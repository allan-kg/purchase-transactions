/*
 * InvalidPurchaseTransactionException
 *
 * v1.0
 *
 * 2023
 *
 * Author: Allan Krama Guimarães
 */

package kg.allan.purchasetransactions.exception;

/**
 *
 * @author Allan Krama Guimarães
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
