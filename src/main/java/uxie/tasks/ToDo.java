package uxie.tasks;

/**
 * ToDos are Tasks without any date/time attached.
 */

public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Returns ToDos as String in format:
     * "[T][<'X' if completed, ' ' if not>] <desc>"
     *
     * @return formatted String
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
