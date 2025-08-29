package uxie.tasks;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ToDos are Tasks without any date/time attached.
 *
 * @author junyan-k
 */

public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    public ToDo(boolean isCompleted, String desc) {
        super(isCompleted, desc);
    }

    /**
     * Returns task type symbol. ("T")
     */
    @Override
    public String getSymbol() {
        return "T";
    }

    /**
     * Returns time arguments in order.
     *
     * @return empty list
     */
    @Override
    public List<LocalDateTime> getTimeArguments() {
        return List.of();
    }

    /**
     * Returns ToDos as String.
     * Format: "[T][<'X' if completed, ' ' if not>] <desc>"
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", getSymbol(), super.toString());
    }

    /**
     * Returns true if both Todos are equal.
     * Two Todos are equal if they have the same description.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ToDo) {
            return ((ToDo) o).getDesc().equals(this.getDesc());
        }
        return false;
    }
}
