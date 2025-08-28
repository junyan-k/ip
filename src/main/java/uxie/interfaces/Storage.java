package uxie.interfaces;

import uxie.exceptions.UxieIOException;
import uxie.exceptions.UxieSyntaxException;
import uxie.interfaces.DateTimeParse;
import uxie.tasks.Deadline;
import uxie.tasks.Event;
import uxie.tasks.Task;
import uxie.tasks.ToDo;

import com.opencsv.exceptions.CsvException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Stores and reads Uxie's data with local file.
 *
 * @author junyan-k
 */
public class Storage {

    /** File path to task file. */
    private static final String TASK_FILEPATH = "./tasks.csv";

    /**
     * Returns local task CSV file.
     * If not found, creates it and returns.
     */
    private static File getTaskFile() throws UxieIOException {
        try {
            File taskFile = new File(TASK_FILEPATH);
            taskFile.createNewFile(); // creates file if it doesn't exist.
            return taskFile;
        } catch (IOException e) {
            throw new UxieIOException("I can't find your task file.");
        }
    }

    /**
     * Stores Task into CSV file.
     * Format is: "<type>,<completion>,<description>,(time1),(time2)"
     * <p>
     * type: T if Todos, D if Deadline, E if Event
     * completion: 1 if completed, 0 otherwise
     * description: description of Task
     * time1 (if needed): if Deadline, deadline. if Event, from
     * time2 (if needed): if Event, to
     *
     * @throws UxieIOException I/O exception during writing of file
     */
    public static void storeTask(Task task) throws UxieIOException {
        List<String> arguments = new ArrayList<>();
        arguments.add(task.getSymbol());
        arguments.add(task.isCompleted() ? "1" : "0");
        arguments.add(task.getDesc());
        for (LocalDateTime dt: task.getTimeArguments()) {
            arguments.add(DateTimeParse.storageWriteParse(dt));
        }

        try (CSVWriter taskFileWriter = new CSVWriter(new FileWriter(getTaskFile(), true))) {
            taskFileWriter.writeNext(arguments.toArray(new String[0]));
        } catch (IOException e) {
            throw new UxieIOException("I can't seem to write down this task.");
        }
    }

    /**
     * Toggles completion status of task matching index.
     *
     * @throws UxieIOException I/O exception during editing of file.
     */
    public static void toggleTaskCompletion(int index) throws UxieIOException {
        try (CSVReader taskFileReader = new CSVReader(new FileReader(getTaskFile()))) {
            List<String[]> taskRows = taskFileReader.readAll();
            taskFileReader.close();

            String[] taskRow = taskRows.get(index);
            taskRow[1] = taskRow[1].equals("0") ? "1" : "0";

            CSVWriter taskFileWriter = new CSVWriter(new FileWriter(getTaskFile()));
            taskFileWriter.writeAll(taskRows);
            taskFileWriter.close();
        } catch (IOException | CsvException e) {
            throw new UxieIOException("I can't seem to edit this marking.");
        }
    }

    /**
     * Converts array of Strings into a Task.
     *
     * @param arguments args for Task. see storeTask for format
     * @return Optional containing Task if valid, or null if invalid.
     */
    private static Optional<Task> convertTaskRow(String[] arguments) {
        // verify completion (1) and description (2)
        if (!arguments[1].matches("[01]") || arguments[2].isBlank()) {
            return Optional.empty();
        }
        try {
            switch (arguments[0]) {
                case "T":
                    if (arguments.length == 3) { // valid
                        return Optional.of(new ToDo(arguments[1].equals("1"), arguments[2]));
                    }
                    break;

                case "D":
                    if (arguments.length == 4 && !arguments[3].isBlank()) { // valid
                        return Optional.of(new Deadline(arguments[1].equals("1"), arguments[2],
                                DateTimeParse.storageReadParse(arguments[3])));
                    }
                    break;

                case "E":
                    if (arguments.length == 5 && !arguments[3].isBlank() && !arguments[4].isBlank()) { // valid
                        return Optional.of(
                                new Event(arguments[1].equals("1"), arguments[2],
                                        DateTimeParse.storageReadParse(arguments[3]),
                                        DateTimeParse.storageReadParse(arguments[4]))
                        );
                    }
                    break;

                default:
                    // task symbol not recognized
                    return Optional.empty();
            }
        } catch (UxieSyntaxException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    /**
     * Reads Tasks from task file.
     *
     * @throws UxieIOException I/O exception during reading of file.
     */
    public static List<Task> readTasks() throws UxieIOException {
        try (CSVReader taskFileReader = new CSVReader(new FileReader(getTaskFile()))) {
            List<String[]> taskRows = taskFileReader.readAll();
            taskFileReader.close();

            List<Task> tasks = new ArrayList<>();
            for (String[] taskRow: taskRows) {
                Optional<Task> maybeTask = convertTaskRow(taskRow);
                maybeTask.ifPresent(tasks::add); // TODO: Add exceptions+messages for different mis-formatted data
            }
            return tasks;
        } catch (IOException | CsvException e) {
            throw new UxieIOException("I can't read your file.");
        }
    }

    /**
     * Deletes task matching index.
     *
     * @throws UxieIOException I/O Exception during deleting of line
     */
    public static void deleteTask(int index) throws UxieIOException {
        try (CSVReader taskFileReader = new CSVReader(new FileReader(getTaskFile()))) {
            List<String[]> taskRows = taskFileReader.readAll();
            taskRows.remove(index);
            taskFileReader.close();

            CSVWriter taskFileWriter = new CSVWriter(new FileWriter(getTaskFile()));
            taskFileWriter.writeAll(taskRows);
            taskFileWriter.close();
        } catch (IOException | CsvException e) {
            throw new UxieIOException("I can't seem to delete this task.");
        }
    }

}
