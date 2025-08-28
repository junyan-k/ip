package uxie.commands;

import uxie.interfaces.TaskList;
import uxie.interfaces.Ui;
import uxie.tasks.Event;

import java.time.LocalDateTime;

/**
 * Command for creating Event tasks.
 *
 * @author junyan-k
 */
public class EventCommand extends Command {

    /** Task to be added. */
    private Event task;

    public EventCommand(String desc, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        task = new Event(desc, startDateTime, endDateTime);
    }

    public EventCommand(boolean isCompleted, String desc, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        task = new Event(isCompleted, desc, startDateTime, endDateTime);
    }

    /**
     * Adds the Event to the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(task);
        ui.uxiePrintln(String.format("Alright. Task added:\n  %s\nYou have %s total tasks. Have fun.",
                task, tasks.size()));
    }

    /**
     * Returns whether this command is exit. (false)
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
