package uxie.interfaces;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import uxie.commands.Command;
import uxie.commands.DeadlineCommand;
import uxie.commands.DeleteCommand;
import uxie.commands.EventCommand;
import uxie.commands.ExitCommand;
import uxie.commands.FindCommand;
import uxie.commands.ListCommand;
import uxie.commands.MarkCommand;
import uxie.commands.TodoCommand;
import uxie.commands.UnmarkCommand;
import uxie.exceptions.UxieSyntaxException;

/**
 * Parses commands from Ui.
 *
 * @author junyan-k
 */
public class CommandParse {

    /**
     * Parses input String and returns corresponding Command.
     *
     * @param userCommand input String from user
     * @return resulting Command object of specific type
     * @throws UxieSyntaxException when userCommand is in incorrect format (e.g. missing arguments, unknown command)
     * @see uxie.commands
     */
    public static Command parse(String userCommand) throws UxieSyntaxException {
        List<String> splitCommand = new ArrayList<>(List.of(userCommand.split(" ")));

        switch (splitCommand.get(0)) {
        case "list": // output contents of list
            return new ListCommand();

        case "mark": // mark task <n> as completed
            try {
                return new MarkCommand(Integer.parseInt(splitCommand.get(1)) - 1);
            } catch (NumberFormatException e) {
                throw new UxieSyntaxException("That index doesn't seem right.");
            }

        case "unmark": // mark task <n> as incomplete
            try {
                return new UnmarkCommand(Integer.parseInt(splitCommand.get(1)) - 1);
            } catch (NumberFormatException e) {
                throw new UxieSyntaxException("That index doesn't seem right.");
            }

        case "delete": // delete task from tasks
            try {
                return new DeleteCommand(Integer.parseInt(splitCommand.get(1)) - 1);
            } catch (NumberFormatException e) {
                throw new UxieSyntaxException("That index doesn't seem right.");
            }

        case "find":
            splitCommand.remove(0);
            String searchString = String.join(" ", splitCommand);
            if (searchString.isBlank()) {
                // search is empty
                throw new UxieSyntaxException("Your search string can't be empty.");
            }
            return new FindCommand(searchString);

        case "todo": // add task as a todos
            splitCommand.remove(0); // remove command word
            String todoDesc = String.join(" ", splitCommand);
            if (todoDesc.isBlank()) {
                // desc is empty
                throw new UxieSyntaxException("Your task description can't be empty.");
            }
            return new TodoCommand(todoDesc);

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

            String dlDesc = String.join(" ", splitCommand.subList(0, byIndex));
            LocalDateTime dlBy = DateTimeParse.parseInput(
                    String.join(" ", splitCommand.subList(byIndex + 1, splitCommand.size())));
            return new DeadlineCommand(dlDesc, dlBy);

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

            String eDesc = String.join(" ", splitCommand.subList(0, fromIndex));
            LocalDateTime eFrom = DateTimeParse.parseInput(
                    String.join(" ", splitCommand.subList(fromIndex + 1, toIndex)));
            LocalDateTime eTo = DateTimeParse.parseInput(
                    String.join(" ", splitCommand.subList(toIndex + 1, splitCommand.size())));
            return new EventCommand(eDesc, eFrom, eTo);

        case "goodbye":
        case "bye": // exits program
            return new ExitCommand();

        default: // unrecognized command
            throw new UxieSyntaxException("Consider checking your spelling.");
        }
    }

}
