/**
 * This class represents all Exceptions unique to Uxie.
 */
public class UxieException extends Exception {

    public UxieException(String message) {
        super(message);
    }

    /**
     * Returns UxieException as String in format:
     * "Uxie: <message>"
     *
     * @return formatted string
     */
    @Override
    public String toString() {
        return String.format("An error in judgement, trainer. %s", this.getMessage());
    }

}
