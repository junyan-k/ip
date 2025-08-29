package uxie.commands;

import uxie.exceptions.UxieIOException;
import uxie.exceptions.UxieIllegalOpException;
import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.Ui;

/**
 * Command that marks a task as complete.
 *
 * @author junyan-k
 */
public class MarkCommand extends Command {

    /** Index of task to mark complete.*/
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks task matching index in TaskList as complete.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String desc = tasks.markCompleted(taskIndex);
            storage.toggleTaskCompletion(taskIndex);
            ui.uxiePrintln(String.format("Task %s (%s) is done. Congratulations.", taskIndex + 1, desc));
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

    /**
     * Returns true if both MarkCommands are equal.
     * Two MarkCommands are equal if they have the same index.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof MarkCommand) {
            return ((MarkCommand) o).taskIndex == this.taskIndex;
        }
        return false;
    }
}
