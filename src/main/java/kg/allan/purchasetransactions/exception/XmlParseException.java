/*
 * XmlParseException
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
public class XmlParseException extends Exception {
    public XmlParseException(Throwable cause) {
        super(cause);
    }
    public XmlParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
