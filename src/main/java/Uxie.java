/*
Main class of Chatbot Uxie.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Uxie {
    // Uxie's dialogue is indented with 4 whitespace

    // used to space messages
    private final static String LINE_BREAK =
            "    ____________________________________________________________";
    private final static ArrayList<Task> listContents = new ArrayList<>();

    public static void main(String[] args) {
        // Output: Welcome (could avoid hardcoding these greetings in future)
        System.out.println(LINE_BREAK);
        System.out.println("    Oh. Hello there, Trainer.");
        System.out.println("    Uxie uxie, or whatever.");
        System.out.println("    Dare I ask why you have summoned me?");
        System.out.println(LINE_BREAK);

        // Receive commands
        Scanner s = new Scanner(System.in);
        boolean running = true;
        while (running) {
            String userCommand = s.nextLine(); // get next command
            String[] splitCommand = userCommand.split(" ");
            System.out.println(LINE_BREAK);
            switch (splitCommand[0]) {
                case "list": // output contents of list
                    for (int i = 1; i <= listContents.size(); i++) {
                        System.out.printf("    %s. %s\n", i, listContents.get(i-1));
                    }
                    System.out.println(LINE_BREAK);
                    break;

                case "goodbye":
                case "bye": // exits program
                    running = false;
                    break;

                default: // adds message as Task to list
                    listContents.add(new Task(userCommand));
                    System.out.printf("    added %s\n", userCommand); // echo command
                    System.out.println(LINE_BREAK);
            }
        }

        // Goodbye
        System.out.println("    That is all? Very well. Goodbye.");
        System.out.println(LINE_BREAK);
    }
}