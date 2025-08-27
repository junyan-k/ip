package uxie.tasks;

import java.util.List;

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
     * Returns task type symbol. ("D")
     */
    @Override
    public String getSymbol() {
        return "D";
    }

    /**
     * Returns time arguments in order.
     *
     * @return list containing deadline
     */
    @Override
    public List<String> getTimeArguments() {
        return List.of(deadline);
    }

    /**
     * Returns Deadline as String.
     * Format: "[D][<'X' if completed, ' ' if not>] <desc> (by: <deadline>)"
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", getSymbol(), super.toString(), deadline);
    }

}
