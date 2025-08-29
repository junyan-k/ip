package uxie.commands;

import uxie.exceptions.UxieIOException;
import uxie.exceptions.UxieIllegalOpException;
import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.Ui;

/**
 * Command that marks a task as incomplete.
 *
 * @author junyan-k
 */
public class UnmarkCommand extends Command {

    /** Index of task to mark complete.*/
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks task matching index in TaskList as complete.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String desc = tasks.markIncomplete(taskIndex);
            storage.toggleTaskCompletion(taskIndex);
            ui.uxiePrintln(String.format("Forgot something? Task %s (%s) is now incomplete.", taskIndex + 1, desc));
        } catch (UxieIllegalOpException | UxieIOException e) {
            ui.printException(e);
        }
    }

    /**
     * Returns whether this command is exit. (false)
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
