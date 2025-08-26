package uxie.tasks;

/**
 * ToDos are Tasks without any date/time attached.
 *
 * @author junyan-k
 */

public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Returns ToDos as String.
     * Format: "[T][<'X' if completed, ' ' if not>] <desc>"
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
