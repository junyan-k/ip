package uxie;

import uxie.commands.Command;
import uxie.exceptions.UxieException;
import uxie.exceptions.UxieIOException;
import uxie.interfaces.CommandParse;
import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.Ui;

/**
 * Main class of Chatbot Uxie.
 *
 * @author junyan-k
 */

public class Uxie {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Initializes Uxie.
     * Generates Ui. Reads Tasks from file to generate TaskList.
     */
    public Uxie() {
        // initialization
        ui = new Ui();
        storage = new Storage("./tasks.csv");
        try {
            tasks = new TaskList(storage.readTasks());
        } catch (UxieIOException e) {
            ui.printException(e);
            tasks = new TaskList();
        }
    }

    /**
     * Run the Uxie program.
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