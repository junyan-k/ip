package uxie.interfaces.ui;

import java.util.Scanner;

import uxie.exceptions.UxieException;

/**
 * Represents UI of Uxie.
 *
 * @author junyan-k
 */
public class Ui {

    /** Used to space messages (60 characters). */
    private static final String LINE_BREAK =
            "____________________________________________________________";

    /** Number of whitespace characters to indent Uxie's messages with. */
    private static final int INDENTATION = 4;

    /** Scanner to obtain user input. */
    private final Scanner input;

    /** Stored buffer of Strings. */
    private StringBuilder stringBuffer;

    /**
     * Generates a Ui. Initializes input Scanner with {@link java.lang.System#in}.
     */
    public Ui() {
        input = new Scanner(System.in);
        stringBuffer = new StringBuilder();
    }

    /**
     * Immediately prints string with Uxie's indentation, and a new line.
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
        uxiePrintln("Oh. Hello there, Trainer.\n"
                + "Uxie uxie, or whatever.\n"
                + "Dare I ask why you have summoned me?");
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

    /**
     * Appends given String to Ui's buffer.
     */
    public void uxieAppendln(String str) {
        stringBuffer.append(str.indent(INDENTATION));
    }

    /**
     * Returns and clears String buffer.
     *
     * @return String stored in Ui's buffer.
     */
    public String getBufferString() {
        String outputString = stringBuffer.toString();
        stringBuffer.setLength(0); // clears stringBuffer for reuse
        return outputString;
    }

}
