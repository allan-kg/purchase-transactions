/*
 * FetchFailedException
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
