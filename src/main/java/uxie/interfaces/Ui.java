package uxie.interfaces;

import uxie.exceptions.UxieException;

import java.util.Scanner;

/**
 * Manages UI of Uxie.
 */

public class Ui {

    // used to space messages (60 characters)
    private final static String LINE_BREAK =
            "____________________________________________________________";
    // no. of lines to indent
    private final static int INDENTATION = 4;

    private final Scanner input;

    public Ui() {
        input = new Scanner(System.in);
    }

    /**
     * Prints string with Uxie's indentation.
     */
    public void uxiePrintln(String str) {
        System.out.print(str.indent(INDENTATION));
    }

    /**
     * Prints line break.
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
     * Prints goodbye.
     */
    public void printGoodbye() {
        uxiePrintln("That is all? Very well. Goodbye.");
    }

    /**
     * Display UxieException.
     */
    public void printException(UxieException e) {
        uxiePrintln(e.toString());
    }

    /**
     * Read and return next command.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Close scanner.
     */
    public void closeScanner() {
        input.close();
    }

}
