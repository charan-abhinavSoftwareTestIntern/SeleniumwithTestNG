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

    public static LocalDate getFutureWeekday(int daysAhead) {
        LocalDate futureDate = LocalDate.now().plusDays(daysAhead);

        // Adjust the date if it falls on a weekend
        while (futureDate.getDayOfWeek() == DayOfWeek.SATURDAY || futureDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            futureDate = futureDate.plusDays(1);
        }
        return futureDate;
    }
}
