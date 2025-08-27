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
     * Returns task type symbol. ("T")
     */
    @Override
    public String getSymbol() {
        return "T";
    }

    /**
     * Returns ToDos as String.
     * Format: "[T][<'X' if completed, ' ' if not>] <desc>"
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", getSymbol(), super.toString());
    }

}
