/*
 * JsonParseException
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
public class JsonParseException extends Exception {
    public JsonParseException(Throwable cause) {
        super(cause);
    }

    public JsonParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
