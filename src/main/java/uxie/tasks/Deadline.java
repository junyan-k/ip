package uxie.tasks;

import uxie.interfaces.DateTimeParse;

import java.time.LocalDateTime;
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
    private LocalDateTime deadline;

    public Deadline(String desc, LocalDateTime deadline) {
        super(desc);
        this.deadline = deadline;
    }

    public Deadline(boolean isCompleted, String desc, LocalDateTime deadline) {
        super(isCompleted, desc);
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
     * Returns time arguments in order as String.
     *
     * @return list containing deadline
     */
    @Override
    public List<LocalDateTime> getTimeArguments() {
        return List.of(deadline);
    }

    /**
     * Returns Deadline as String.
     * Format: "[D][<'X' if completed, ' ' if not>] <desc> (by: <deadline>)"
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)",
                getSymbol(), super.toString(), DateTimeParse.parseOutput(deadline));
    }

    /**
     * Returns true if both Deadlines are equal.
     * Two Todos are equal if they have the same description and by LDTs.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline d) {
            return d.getDesc().equals(this.getDesc()) &&
                    d.deadline.equals(this.deadline);
        }
        return false;
    }

}
