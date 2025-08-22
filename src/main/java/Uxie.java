/*
Main class of Chatbot Uxie.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Uxie {
    // Uxie's dialogue is indented with 4 whitespace

    // used to space messages
    private final static String LINE_BREAK =
            "    ____________________________________________________________";
    private final static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        // Output: Welcome (could avoid hardcoding these greetings in future)
        System.out.println(LINE_BREAK);
        System.out.println("    Oh. Hello there, Trainer.");
        System.out.println("    Uxie uxie, or whatever.");
        System.out.println("    Dare I ask why you have summoned me?");
        System.out.println(LINE_BREAK);

        // Receive commands
        Scanner s = new Scanner(System.in);
        // empty variables to use during switch (these are NOT reset after loop so take care)
        int taskIndex; Task task; String desc; String time1; String time2;
        boolean running = true;
        while (running) {
            String userCommand = s.nextLine(); // get next command
            List<String> splitCommand = new ArrayList<>(List.of(userCommand.split(" ")));
            System.out.println(LINE_BREAK);
            try {
                switch (splitCommand.get(0)) {
                    case "list": // output contents of list
                        for (int i = 1; i <= taskList.size(); i++) {
                            System.out.printf("    %s. %s\n", i, taskList.get(i - 1));
                        }
                        break;

                    case "mark": // mark task <n> as completed
                        taskIndex = Integer.parseInt(splitCommand.get(1));
                        if (taskIndex < 0 || taskIndex > taskList.size()) {
                            System.out.println(taskList.size());
                            throw new UxieIllegalOpException("That task doesn't exist.");
                        }
                        task = taskList.get(taskIndex - 1);
                        task.markCompleted();
                        System.out.printf("    Task %s (%s) is done. Congratulations.\n",
                                taskIndex, task.getDesc());
                        break;

                    case "unmark": // mark task <n> as incomplete
                        taskIndex = Integer.parseInt(splitCommand.get(1));
                        if (taskIndex < 1 || taskIndex > taskList.size()) {
                            throw new UxieIllegalOpException("That task doesn't exist.");
                        }
                        task = taskList.get(taskIndex - 1);
                        task.markIncomplete();
                        System.out.printf("    Forgot something? Task %s (%s) is now incomplete.\n",
                                taskIndex, task.getDesc());
                        break;

                    case "delete": // delete task from taskList
                        taskIndex = Integer.parseInt(splitCommand.get(1));
                        if (taskIndex < 1 || taskIndex > taskList.size()) {
                            throw new UxieIllegalOpException("That task doesn't exist.");
                        }
                        task = taskList.get(taskIndex - 1);
                        taskList.remove(taskIndex - 1);
                        System.out.printf("    Good to be realistic. Task %s (%s) has been deleted.\n",
                                taskIndex, task.getDesc());
                        break;


                    case "todo": // add task as a todos
                        splitCommand.remove(0); // remove command word
                        desc = String.join(" ", splitCommand);
                        if (desc.matches("\s*")) {
                            // desc is empty
                            throw new UxieSyntaxException("Your task description can't be empty.");
                        }
                        task = new ToDo(desc);
                        taskList.add(task);
                        System.out.printf("    Alright. Task added:\n      %s\n    You have %s total tasks. Best of luck.\n",
                                task, taskList.size());
                        break;

                    case "deadline": // add task as a deadline
                        splitCommand.remove(0); // remove command word
                        if (splitCommand.isEmpty() || splitCommand.get(0).matches("\s*|/.*")) {
                            // desc is empty
                            throw new UxieSyntaxException("Your task description can't be empty.");
                        }
                        int byIndex = -1;
                        for (int i = 1; i < splitCommand.size(); i++) { // skip first as task desc cannot be empty
                            // search for "/by"
                            if (splitCommand.get(i).equals("/by")) {
                                byIndex = i;
                                break;
                            }
                        }
                        if (byIndex == -1) {
                            // "/by" not found
                            throw new UxieSyntaxException("A deadline needs a... deadline. ('/by')");
                        }

                        desc = String.join(" ", splitCommand.subList(0, byIndex));
                        time1 = String.join(" ", splitCommand.subList(byIndex + 1, splitCommand.size()));
                        task = new Deadline(desc, time1);
                        taskList.add(task);
                        System.out.printf("    Alright. Task added:\n      %s\n    You have %s total tasks. But we all know you'll just rush them at the last minute like you always do.\n",
                                task, taskList.size());
                        break;

                    case "event": // add task as an event
                        splitCommand.remove(0); // remove command word
                        if (splitCommand.isEmpty() || splitCommand.get(0).matches("\s*|/.*")) {
                            // desc is empty
                            throw new UxieSyntaxException("Your task description can't be empty.");
                        }
                        int fromIndex = -1;
                        int toIndex = -1;
                        for (int i = 1; i < splitCommand.size(); i++) { // skip first as task desc cannot be empty
                            // search for "/from"
                            if (splitCommand.get(i).equals("/from")) {
                                fromIndex = i;
                            }
                            // search for "/to"
                            if (splitCommand.get(i).equals("/to")) {
                                if (fromIndex == -1) {
                                    // "/to" before "/from"
                                    throw new UxieSyntaxException("/from should be before /to.");
                                }
                                toIndex = i;
                                break;
                            }
                        }

                        if (fromIndex == -1 || toIndex == -1) {
                            // argument missing
                            throw new UxieSyntaxException("You're missing an argument there. ('/from', '/to')");
                        }

                        desc = String.join(" ", splitCommand.subList(0, fromIndex));
                        time1 = String.join(" ", splitCommand.subList(fromIndex + 1, toIndex));
                        time2 = String.join(" ", splitCommand.subList(toIndex + 1, splitCommand.size()));
                        task = new Event(desc, time1, time2);
                        taskList.add(task);
                        System.out.printf("    Alright. Task added:\n      %s\n    You have %s total tasks. Have fun.\n",
                                task, taskList.size());
                        break;

                    case "goodbye":
                    case "bye": // exits program
                        running = false;
                        break;

                    default: // unrecognized command
                        throw new UxieSyntaxException("Consider checking your spelling.");
                }
            } catch (UxieException e) {
                System.out.printf("    %s\n", e);
            }
            if (running) { System.out.println(LINE_BREAK); }
        }

        // Goodbye
        System.out.println("    That is all? Very well. Goodbye.");
        System.out.println(LINE_BREAK);
    }
}