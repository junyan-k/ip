package uxie;

import uxie.commands.Command;
import uxie.exceptions.UxieException;
import uxie.exceptions.UxieIOException;
import uxie.interfaces.CommandParse;
import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.ui.Ui;

/**
 * Main class of Chatbot Uxie.
 *
 * @author junyan-k
 */
public class Uxie {

    /** List of tasks. */
    private TaskList tasks;

    /** User interface of Uxie. */
    private Ui ui;

    /** Local storage interface. */
    private Storage storage;

    /**
     * Initializes Uxie.
     * Generates Ui.
     * Reads Tasks from default task filepath to generate TaskList.
     */
    public Uxie() {
        // initialization
        ui = new Ui();
        storage = new Storage(); // see uxie.storage.Storage for default taskFilePath
        try {
            tasks = new TaskList(storage.readTasks());
        } catch (UxieIOException e) {
            ui.printException(e);
            tasks = new TaskList();
        }
    }

    /**
     * Initializes Uxie.
     * Generates Ui.
     * Reads Tasks from specified task filepath to generate TaskList.
     */
    public Uxie(String taskFilePath) {
        // initialization
        ui = new Ui();
        storage = new Storage(taskFilePath);
        try {
            tasks = new TaskList(storage.readTasks());
        } catch (UxieIOException e) {
            ui.printException(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Uxie program.
     * Includes body of program loop.
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLineBreak();
                Command c = CommandParse.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (UxieException e) {
                ui.printException(e);
            } finally {
                ui.printLineBreak();
            }
        }
    }

    public static void main(String[] args) {
        new Uxie().run();
    }

}
