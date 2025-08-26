package uxie.tasks;

/**
 * Events are Tasks that start and end at a specific date/time.
 * (note: date/time is represented by String to allow for more
 *  flexible inputs)
 */
public class Event extends Task {

    private String startDT; // startDT represented in String
    private String endDT;

    public Event(String desc, String startDT, String endDT) {
        super(desc);
        this.startDT = startDT;
        this.endDT = endDT;
    }

    /**
     * Returns Event as String in format:
     * "[E][<'X' if completed, ' ' if not>] <desc> (from: <startDT> to: <endDT>)"
     *
     * @return formatted String
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), startDT, endDT);
    }

}
