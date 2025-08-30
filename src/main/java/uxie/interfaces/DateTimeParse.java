package uxie.interfaces;

import uxie.exceptions.UxieSyntaxException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses Strings to DateTime.
 *
 * @author junyan-k
 */
public class DateTimeParse {

    /** DateTimeFormatter used to format user-inputted date/times. */
    private final static DateTimeFormatter INPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    /** DateTimeFormatter used to format date/times printed by UI. */
    private final static DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm");

    /** DateTimeFormatter used to format date/times in stored locally. */
    private final static DateTimeFormatter STORAGE_DATETIME_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    /**
     * Parses input String to LocalDateTime object.
     * Uses {@link #INPUT_DATETIME_FORMAT}
     *
     * @throws UxieSyntaxException incorrect format of input string
     */
    public static LocalDateTime inputParse(String dateTimeString) throws UxieSyntaxException {
        try {
            return LocalDateTime.parse(dateTimeString, INPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new UxieSyntaxException(
                    String.format("Your date/time format is incorrect. (%s)", INPUT_DATETIME_FORMAT));
        }
    }

    /**
     * Returns LocalDateTime object as String in output format.
     * Uses {@link #OUTPUT_DATETIME_FORMAT}
     */
    public static String outputParse(LocalDateTime dateTime) {
        return OUTPUT_DATETIME_FORMAT.format(dateTime);
    }

    /**
     * Parses input String to LocalDateTime object for local storage.
     * Uses {@link #STORAGE_DATETIME_FORMAT}
     *
     * @return LocalDateTime produced from parsing stored data
     * @throws UxieSyntaxException incorrect format of input string
     */
    public static LocalDateTime storageReadParse(String dateTimeString) throws UxieSyntaxException {
        try {
            return LocalDateTime.parse(dateTimeString, STORAGE_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new UxieSyntaxException(
                    String.format("Your date/time format is incorrect. (%s)", STORAGE_DATETIME_FORMAT));
        }
    }

    /**
     * Returns DateTime object as String in output format for local storage.
     * Uses {@link #STORAGE_DATETIME_FORMAT}
     */
    public static String storageWriteParse(LocalDateTime dateTime) {
        return STORAGE_DATETIME_FORMAT.format(dateTime);
    }

}
