package uxie.commands;

import uxie.exceptions.UxieIllegalOpException;
import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.ui.Ui;

/**
 * Command that lists all Tasks.
 *
 * @author junyan-k
 */
public class ListCommand extends Command {

    /**
     * Generates ListCommand.
     */
    public ListCommand() {

    }

    /**
     * {@inheritDoc}
     * Lists all Tasks in TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            ui.uxiePrintln("You don't have any tasks yet.");
        }
        try {
            for (int i = 1; i <= tasks.getSize(); i++) {
                ui.uxiePrintln(String.format("%s. %s", i, tasks.getTask(i - 1)));
            }
        } catch (UxieIllegalOpException e) {
            ui.printException(e); // should never happen as indices are controlled
        }
    }

    /**
     * {@inheritDoc}
     * Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
