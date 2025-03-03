package utils;

import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FakerDataUtils {

    private static final Faker faker = new Faker();

    public static String generateFirstName() {
        return faker.name().firstName();
    }

    public static String generateLastName() {
        return faker.name().lastName();
    }

    // Method to generate employee ID in the format "OW000"
    public static String generateEmployeeId() {
        Random random = new Random();
        int idNumber = random.nextInt(9000) + 100; // generates a number between 100 and 999
        return "OW" + idNumber;
    }

    public static String generateEmail() {
        String emailPrefix = faker.internet().emailAddress().split("@")[0]; // Extracts the prefix before '@'
        return emailPrefix + "@optimworks.com";
    }

    public static String generatePassword() {
        return faker.internet().password();
    }

    public static String generateDateOfBirth() {
        // Generate a random date of birth for an individual aged between 18 and 80 years
        Date birthday = faker.date().birthday();

        // Format the date to a string (e.g., "MM/dd/yyyy")
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(birthday);
    }

    public static String generateDepartment() {
        return faker.company().profession();
    }

    // Method to generate a random mobile number in the format "9123456789"
    public static String generateMobileNumber() {
        return "9" + faker.number().digits(9); // "9" followed by 9 random digits
    }

    public static String generateDesignation() {
        return faker.job().title();
    }

    // Method to generate a random salary as a String (e.g., "10000")
    public static String generateRandomSalary() {
        int salary = 10000 + faker.random().nextInt(90000); // Salary between 10,000 and 99,999
        return String.valueOf(salary); // Return salary as String
    }

    public static String generateCity() {
        return faker.address().city();
    }

    // Method to generate a random leave subject
    public static String generateFakeLeaveSubject() {
        // You can customize this to include more variety or specific patterns if necessary
        return faker.lorem().sentence();  // Generates a random sentence which can be used as a subject.
    }

    // Method to generate a random reason for leave
    public static String generateFakeLeaveReason() {
        // You can customize the reason by mixing predefined text or using random sentences
        return faker.lorem().sentence();  // Generates a random sentence that can be used as a reason.
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Generates a random future 'from' date within the next 30 days.
     * @return Future 'from' date as String (yyyy-MM-dd)
     */
    public static String generateFutureFromDate() {
        LocalDate fromDate = LocalDate.now().plusDays(faker.number().numberBetween(1, 30));
        return fromDate.format(formatter);
    }

    /**
     * Generates a 'to' date that is always after the given 'from' date.
     * @param fromDate A valid 'from' date (String format yyyy-MM-dd)
     * @return Future 'to' date as String (yyyy-MM-dd)
     */
    public static String generateFutureToDate(String fromDate) {
        LocalDate from = LocalDate.parse(fromDate, formatter);

        // Ensuring at least 2 days gap to avoid same-day issues
        LocalDate toDate = from.plusDays(faker.number().numberBetween(2, 10));

        return toDate.format(formatter);
    }

    /**
     * Generates a valid joining date that is always in the past (before today).
     * @return A past joining date as a String (MM-dd-yyyy)
     */
    public static String generateValidJoiningDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        // Generate a random past year (e.g., between 2020 and the current year)
        int currentYear = LocalDate.now().getYear();
        int year = ThreadLocalRandom.current().nextInt(2020, currentYear + 1); // Ensures it's before or equal to this year

        // Generate a random month (between 1 and 12)
        int month = ThreadLocalRandom.current().nextInt(1, 13);

        // Get the maximum valid day for the generated month and year
        int maxDay = LocalDate.of(year, month, 1).lengthOfMonth();

        // Generate a random valid day
        int day = ThreadLocalRandom.current().nextInt(1, maxDay + 1);

        // Create the joining date
        LocalDate joiningDate = LocalDate.of(year, month, day);

        // Ensure the date is strictly before today
        while (!joiningDate.isBefore(LocalDate.now())) {
            year = ThreadLocalRandom.current().nextInt(2020, currentYear);
            month = ThreadLocalRandom.current().nextInt(1, 13);
            maxDay = LocalDate.of(year, month, 1).lengthOfMonth();
            day = ThreadLocalRandom.current().nextInt(1, maxDay + 1);
            joiningDate = LocalDate.of(year, month, day);
        }

        // Return the formatted joining date
        return joiningDate.format(dateFormatter);
    }


}

