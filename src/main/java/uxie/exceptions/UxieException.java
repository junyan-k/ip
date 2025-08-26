package uxie.exceptions;

/**
 * This class represents all Exceptions unique to Uxie.
 *
 * @author junyan-k
 */
public class UxieException extends Exception {

    public UxieException(String message) {
        super(message);
    }

    /**
     * Returns UxieException as String.
     * Format: "Uxie: <message>"
     */
    @Override
    public String toString() {
        return String.format("An error in judgement, trainer. %s", this.getMessage());
    }

}
