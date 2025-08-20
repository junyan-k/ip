import java.util.Scanner;
public class Uxie {
    // Uxie's dialogue is indented with 4 whitespace

    // used to space messages
    private final static String LINE_BREAK =
            "    ____________________________________________________________";

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
            System.out.println(LINE_BREAK);
            switch (userCommand) {
                case "goodbye":
                case "bye":
                    running = false;
                    break;
                default:
                    System.out.println("    " + userCommand); // echo command
                    System.out.println(LINE_BREAK);
            }
        }

        // Goodbye
        System.out.println("    That is all? Very well. Goodbye.");
        System.out.println(LINE_BREAK);
    }
}