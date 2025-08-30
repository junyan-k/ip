package uxie.commands;

import uxie.exceptions.UxieIllegalOpException;
import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.Ui;

import java.util.List;

/**
 * Command that finds Tasks matching String.
 *
 * @author junyan-k
 */
public class FindCommand extends Command {

    /** String to search for. */
    private String search;

    /**
     * Generates a FindCommand with search String.
     */
    public FindCommand(String search) {
        this.search = search;
    }

    /**
     * Prints list of Tasks matching search String.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Integer> resultIndices = tasks.findContainingString(search);
        if (resultIndices.isEmpty()) {
            ui.uxiePrintln("I can't find any tasks mentioning that.");
        } else {
            ui.uxiePrintln("Here are the matching tasks:");
            try {
                for (int index: resultIndices) {
                    ui.uxiePrintln(String.format("%s. %s", index + 1, tasks.getTask(index)));
                }
            } catch (UxieIllegalOpException e) {
                ui.printException(e); // this should never happen as indices are produced by TaskList
            }
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

    /**
     * Returns true if provided Object is equal to this FindCommand.
     * Two FindCommands are equal if their search strings are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof FindCommand) {
            return ((FindCommand) o).search.equals(this.search);
        } else {
            return false;
        }
    }

}
