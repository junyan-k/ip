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
     * Returns task type symbol. ("E")
     */
    @Override
    public String getSymbol() {
        return "E";
    }

    /**
     * Returns Event as String.
     * Format: "[E][<'X' if completed, ' ' if not>] <desc> (from: <startDT> to: <endDateTime>)"
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)",
                getSymbol(), super.toString(), startDateTime, endDateTime);
    }

}
