/**
 * Indicates an error in user input syntax. Details are provided in
 * exception message.
 */

public class UxieSyntaxException extends UxieException {

    public UxieSyntaxException(String message) {
        super(message);
    }

    /**
     * Returns exception as String in format:
     * "Didn't quite catch that, trainer. <message>"
     */
    @Override
    public String toString() {
        return String.format("Didn't quite catch that, trainer. %s", this.getMessage());
    }

}
