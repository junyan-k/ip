package uxie.commands;

import uxie.exceptions.UxieIllegalOpException;
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
    public void execute(TaskList tasks, Ui ui) {
        try {
            tasks.markCompleted(taskIndex);
        } catch (UxieIllegalOpException e) {
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
