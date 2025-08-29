package uxie.commands;

import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.Ui;

/**
 * Abstract class representing commands for Uxie to execute.
 *
 * @author junyan-k
 */
public abstract class Command {

    /** Executes command. */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /** Only returns true if command is exit, else return false. */
    public abstract boolean isExit();

}
