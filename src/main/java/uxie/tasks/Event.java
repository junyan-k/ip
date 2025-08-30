package uxie.tasks;

import uxie.interfaces.DateTimeParse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Events are Tasks that start and end at a specific date/time.
 * (note: date/time is represented by String to allow for more
 *  flexible inputs)
 *
 * @author junyan-k
 */
public class Event extends Task {

    /** Starting date/time. */
    private LocalDateTime startDateTime;

    /** Ending date/time */
    private LocalDateTime endDateTime;


    public Event(String desc, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(desc);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Event(boolean isCompleted, String desc, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(isCompleted, desc);
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
     * Returns time arguments in order.
     *
     * @return list containing from, to in order
     */
    @Override
    public List<LocalDateTime> getTimeArguments() {
        return List.of(startDateTime, endDateTime);
    }

    /**
     * Returns Event as String.
     * Format: "[E][<'X' if completed, ' ' if not>] <desc> (from: <startDT> to: <endDateTime>)"
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)",
                getSymbol(), super.toString(), DateTimeParse.parseOutput(startDateTime),
                DateTimeParse.parseOutput(endDateTime));
    }

    /**
     * Returns true if both Events are equal.
     * Two Todos are equal if they have the same description, to and from LDTs.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Event e) {
            return e.getDesc().equals(this.getDesc()) &&
                    e.startDateTime.equals(this.startDateTime) &&
                    e.endDateTime.equals(this.endDateTime);
        }
        return false;
    }

}
