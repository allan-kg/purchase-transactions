/*
 * ElementNotFoundException
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
