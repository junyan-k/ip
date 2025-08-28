package uxie.commands;

import uxie.interfaces.TaskList;
import uxie.interfaces.Ui;
import uxie.tasks.ToDo;

/**
 * Command for creating Todo tasks.
 *
 * @author junyan-k
 */
public class TodoCommand extends Command {

    /** Task to be added. */
    private ToDo task;

    public TodoCommand(String desc) {
        task = new ToDo(desc);
    }

    public TodoCommand(boolean isCompleted, String desc) {
        task = new ToDo(isCompleted, desc);
    }

    /**
     * Adds the Todo to the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(task);
    }

    /**
     * Returns whether this command is exit. (false)
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
