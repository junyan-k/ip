package uxie.commands;

import uxie.exceptions.UxieIOException;
import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.Ui;
import uxie.tasks.Deadline;

import java.time.LocalDateTime;

/**
 * Command for creating Deadline tasks.
 *
 * @author junyan-k
 */
public class DeadlineCommand extends Command {

    private Deadline task;

    public DeadlineCommand(String desc, LocalDateTime deadline) {
        this.task = new Deadline(desc, deadline);
    }

    public DeadlineCommand(boolean isCompleted, String desc, LocalDateTime deadline) {
        this.task = new Deadline(isCompleted, desc, deadline);
    }

    /**
     * Adds the Deadline to the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        try {
            storage.storeTask(task);
        } catch (UxieIOException e) {
            ui.printException(e);
        }
        ui.uxiePrintln(String.format("Alright. Task added:\n  %s\nYou have %s total tasks. "
                        + "But we all know you'll just rush them at the last minute like you always do.",
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
