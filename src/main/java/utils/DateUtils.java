package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    // Method to check if given LocalDate(today) is a weekday.
    public static boolean isWeekday(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    }

    // Method to get the next weekday if the given date is a weekend.
    public static LocalDate getNextWeekday(LocalDate date) {
        while (!isWeekday(date)) {
            date = date.plusDays(1); // Move to the next day
        }
        return date;
    }

    // This method takes a String date in yyyy-MM-dd format, processes it, and returns the next available
    // weekday in the same format.
    public static String getNextWeekdays(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(date, formatter);
        return getNextWeekday(inputDate).format(formatter);
    }
}
