package uxie.commands;

import uxie.exceptions.UxieIllegalOpException;
import uxie.interfaces.TaskList;
import uxie.interfaces.Ui;

/**
 * Command that lists all Tasks.
 *
 * @author junyan-k
 */
public class ListCommand extends Command {

    public ListCommand() {

    }

    /**
     * Lists all Tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            for (int i = 1; i <= tasks.size(); i++) {
                ui.uxiePrintln(String.format("%s. %s", i, tasks.getTask(i - 1)));
            }
        } catch (UxieIllegalOpException e) {
            ui.printException(e); // should never happen as indices are controlled
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
