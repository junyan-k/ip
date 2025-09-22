package uxie;

import uxie.commands.Command;
import uxie.exceptions.UxieException;
import uxie.exceptions.UxieIOException;
import uxie.exceptions.UxieSyntaxException;
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
        } catch (UxieException e) {
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
        } catch (UxieException e) {
            ui.printException(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Uxie program.
     * Includes body of program loop.
     */
    public void run() {
        ui.printWelcome(); // print directly
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLineBreak(); // print directly
                Command c = CommandParse.parse(fullCommand);
                c.execute(tasks, ui, storage); // loads ui string buffer
                System.out.print(ui.getBufferString()); // gets and clears ui string buffer
                isExit = c.isExit();
            } catch (UxieException e) {
                ui.printException(e); // print directly
            } finally {
                ui.printLineBreak(); // print directly
            }
        }
    }

    /**
     * Gets output String resulting from evaluating command in input.
     * Used by JavaFX GUI.
     *
     * @param input String inputted by user.
     * @return response for Uxie to print.
     */
    public String getResponse(String input) {
        // check if input is blank
        if (input.isBlank()) {
            return "...you there?";
        }

        try {
            Command c = CommandParse.parse(input);
            c.execute(tasks, ui, storage);
            return ui.getBufferString();
        } catch (UxieException e) {
            ui.appendException(e);
            return ui.getBufferString();
        }
    }

    public static void main(String[] args) {
        new Uxie().run();
    }

}
