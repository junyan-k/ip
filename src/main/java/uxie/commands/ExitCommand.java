package uxie.commands;

import uxie.interfaces.TaskList;
import uxie.interfaces.Ui;

/**
 * Command that exits Uxie program.
 *
 * @author junyan-k
 */
public class ExitCommand extends Command {

    public ExitCommand() {

    }

    /**
     * Executes command. (does nothing)
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printGoodbye();
        ui.closeScanner();
    }

    /**
     * Whether this command is exit. (true)
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
