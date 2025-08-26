package uxie.exceptions;

/**
 * Represents UxieExceptions to do with illegal operations.
 */

public class UxieIllegalOpException extends UxieException {

    public UxieIllegalOpException(String message) {
        super(message);
    }

    /**
     * Returns exception as string in format:
     * "Can't do that, trainer. <message>"
     *
     * @return formatted string
     */
    @Override
    public String toString() {
        return String.format("Can't do that, trainer. %s", this.getMessage());
    }
}
