package uxie.tasks;

/**
 * Deadlines are Tasks that need to be done by a specific date/time.
 * (note: date/time is represented by String to allow for more
 *  flexible inputs)
 *
 * @author junyan-k
 */
public class Deadline extends Task {

    /** Deadline of task. */
    private String deadline;


    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    /**
     * Returns Deadline as String.
     * Format: "[D][<'X' if completed, ' ' if not>] <desc> (by: <deadline>)"
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

}
