package uxie.interfaces;

import uxie.exceptions.UxieException;

import java.util.Scanner;

/**
 * Represents UI of Uxie.
 *
 * @author junyan-k
 */
public class Ui {

    /** Used to space messages (60 characters). */
    private final static String LINE_BREAK =
            "____________________________________________________________";

    /** Number of whitespace characters to indent Uxie's messages with. */
    private final static int INDENTATION = 4;

    /** Scanner to obtain user input. */
    private final Scanner input;

    /**
     * Generates a Ui. Initializes input Scanner with {@link java.lang.System#in}.
     */
    public Ui() {
        input = new Scanner(System.in);
    }

    /**
     * Prints string with Uxie's indentation, and a new line.
     * @see #INDENTATION
     */
    public void uxiePrintln(String str) {
        System.out.print(str.indent(INDENTATION));
    }

    /**
     * Prints line break.
     * @see #LINE_BREAK
     */
    public void printLineBreak() {
        uxiePrintln(LINE_BREAK);
    }

    /**
     * Prints welcome message.
     */
    public void printWelcome() {
        printLineBreak();
        uxiePrintln("Oh. Hello there, Trainer.\n" +
                "Uxie uxie, or whatever.\n" +
                "Dare I ask why you have summoned me?");
        printLineBreak();
    }

    /**
     * Prints goodbye message.
     */
    public void printGoodbye() {
        uxiePrintln("That is all? Very well. Goodbye.");
    }

    /**
     * Prints UxieException.
     * Uses the toString function of UxieException or its subclasses.
     */
    public void printException(UxieException e) {
        uxiePrintln(e.toString());
    }

    /**
     * Reads and returns next command.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Closes input Scanner.
     */
    public void closeScanner() {
        input.close();
    }

}
