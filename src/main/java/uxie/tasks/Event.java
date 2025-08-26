package uxie.tasks;

/**
 * Events are Tasks that start and end at a specific date/time.
 * (note: date/time is represented by String to allow for more
 *  flexible inputs)
 *
 * @author junyan-k
 */
public class Event extends Task {

    /** Starting date/time. */
    private String startDateTime;

    /** Ending date/time */
    private String endDateTime;


    public Event(String desc, String startDateTime, String endDateTime) {
        super(desc);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns Event as String.
     * Format: "[E][<'X' if completed, ' ' if not>] <desc> (from: <startDT> to: <endDateTime>)"
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), startDateTime, endDateTime);
    }

}
