/**
 * Deadlines are Tasks that need to be done by a specific date/time.
 * (note: date/time is represented by String to allow for more
 *  flexible inputs)
 */
public class Deadline extends Task {

    private String deadline; // deadline represented in String

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    /**
     * Returns Deadline as String in format:
     * "[D][<'X' if completed, ' ' if not>] <desc> (by: <deadline>)"
     *
     * @return formatted String
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

}
