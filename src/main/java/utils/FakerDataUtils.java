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
    private static final DateTimeFormatter APPLY_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static final DateTimeFormatter VERIFY_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Generate First Name
    public static String generateFirstName() {
        return faker.name().firstName();
    }

    // Generate Last Name
    public static String generateLastName() {
        return faker.name().lastName();
    }

    // Generate Employee ID in "OW000" format
    public static String generateEmployeeId() {
        int idNumber = faker.number().numberBetween(100, 999);
        return "OW" + idNumber;
    }

    // Generate Company Email
    public static String generateEmail() {
        String emailPrefix = faker.internet().emailAddress().split("@")[0];
        return emailPrefix + "@optimworks.com";
    }

    // Generate Password
    public static String generatePassword() {
        return faker.internet().password();
    }

    // Generate Date of Birth (MM/dd/yyyy)
    public static String generateDateOfBirth() {
        Date birthday = faker.date().birthday();
        return new SimpleDateFormat("MM/dd/yyyy").format(birthday);
    }

    // Generate Random Department
    public static String generateDepartment() {
        return faker.company().profession();
    }

    // Generate Mobile Number in "9123456789" format
    public static String generateMobileNumber() {
        return "9" + faker.number().digits(9);
    }

    // Generate Designation
    public static String generateDesignation() {
        return faker.job().title();
    }

    // Generate Salary as a String (10,000 to 99,999)
    public static String generateRandomSalary() {
        int salary = faker.number().numberBetween(10000, 99999);
        return String.valueOf(salary);
    }

    // Generate City Name
    public static String generateCity() {
        return faker.address().city();
    }

    // Generate Random Leave Subject
    public static String generateFakeLeaveSubject() {
        return faker.lorem().sentence();
    }

    // Generate Random Leave Reason
    public static String generateFakeLeaveReason() {
        return faker.lorem().sentence();
    }

    // ✅ Generate 'From Date' for Leave Application (MM-dd-yyyy)
    public static String generateFutureFromDate() {
        LocalDate futureDate = LocalDate.now().plusDays(faker.number().numberBetween(1, 10));
        return futureDate.format(APPLY_FORMATTER);
    }

    // ✅ Generate 'To Date' ensuring it's after 'From Date'
    public static String generateFutureToDate(String fromDate) {
        LocalDate parsedFromDate = LocalDate.parse(fromDate, APPLY_FORMATTER);
        LocalDate futureToDate = parsedFromDate.plusDays(faker.number().numberBetween(1, 5));
        return futureToDate.format(APPLY_FORMATTER);
    }

    // ✅ Convert 'From Date' to Verification Format (dd-MM-yyyy)
    public static String convertDateForVerification(String appliedDate) {
        LocalDate parsedDate = LocalDate.parse(appliedDate, APPLY_FORMATTER);
        return parsedDate.format(VERIFY_FORMATTER);
    }

    // ✅ Generate a Valid Joining Date (Past Date, MM-dd-yyyy)
    public static String generateValidJoiningDate() {
        int currentYear = LocalDate.now().getYear();
        int year = ThreadLocalRandom.current().nextInt(2020, currentYear);
        int month = ThreadLocalRandom.current().nextInt(1, 13);
        int maxDay = LocalDate.of(year, month, 1).lengthOfMonth();
        int day = ThreadLocalRandom.current().nextInt(1, maxDay + 1);

        LocalDate joiningDate = LocalDate.of(year, month, day);

        // Ensure the generated date is strictly in the past
        while (!joiningDate.isBefore(LocalDate.now())) {
            year = ThreadLocalRandom.current().nextInt(2020, currentYear);
            month = ThreadLocalRandom.current().nextInt(1, 13);
            maxDay = LocalDate.of(year, month, 1).lengthOfMonth();
            day = ThreadLocalRandom.current().nextInt(1, maxDay + 1);
            joiningDate = LocalDate.of(year, month, day);
        }

        return joiningDate.format(APPLY_FORMATTER);
    }
}
