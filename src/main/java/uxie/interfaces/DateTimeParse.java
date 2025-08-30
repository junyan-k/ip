package uxie.interfaces;

import uxie.exceptions.UxieSyntaxException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses Strings to DateTime.
 */
public class DateTimeParse {

    private final static DateTimeFormatter INPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    private final static DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm");

    private final static DateTimeFormatter STORAGE_DATETIME_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    /**
     * Parses input String to LocalDateTime object.
     *
     * @throws UxieSyntaxException incorrect format of input string
     */
    public static LocalDateTime parseInput(String dateTimeString) throws UxieSyntaxException {
        try {
            return LocalDateTime.parse(dateTimeString, INPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new UxieSyntaxException(
                    String.format("Your date/time format is incorrect. (%s)", INPUT_DATETIME_FORMAT));
        }
    }

    /**
     * Returns DateTime object as String in output format.
     */
    public static String parseOutput(LocalDateTime dateTime) {
        return OUTPUT_DATETIME_FORMAT.format(dateTime);
    }

    /**
     * Parses input String to LocalDateTime object for local storage.
     *
     * @throws UxieSyntaxException incorrect format of input string
     */
    public static LocalDateTime parseStorageRead(String dateTimeString) throws UxieSyntaxException {
        try {
            return LocalDateTime.parse(dateTimeString, STORAGE_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new UxieSyntaxException(
                    String.format("Your date/time format is incorrect. (%s)", STORAGE_DATETIME_FORMAT));
        }
    }

    /**
     * Returns DateTime object as String in output format for local storage.
     */
    public static String parseStorageWrite(LocalDateTime dateTime) {
        return STORAGE_DATETIME_FORMAT.format(dateTime);
    }

}
