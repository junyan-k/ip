package uxie.exceptions;

/**
 * Represents UxieExceptions related to file reading and I/O.
 *
 * @author junyan-k
 */
public class UxieIOException extends UxieException {

    public UxieIOException(String msg) {
        super(msg);
    }

    /**
     * Returns exception as String.
     * Format: "This prison angers me, trainer. <msg>"
     */
    @Override
    public String toString() {
        return String.format("This prison angers me, trainer. %s", this.getMessage());
    }
}
