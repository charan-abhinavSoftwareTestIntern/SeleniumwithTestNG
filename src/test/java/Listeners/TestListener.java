// This will send the Zip files to mail.

//package Listeners;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import pages.EmailPage;
//import utils.DriverManager;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestListener implements ITestListener {
//    private final StringBuilder report = new StringBuilder();
//    private final EmailPage emailPage = new EmailPage();
//    private final List<String> screenshotPaths = new ArrayList<>();
//
//    private static final String SCREENSHOT_DIR = "target/screenshots";
//    private static final String ALLURE_RESULTS_DIR = "target/allure-results";
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        report.append("❌ FAILED: ").append(result.getName()).append("\n");
//        captureScreenshot(result);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        report.append("✅ PASSED: ").append(result.getName()).append("\n");
//        captureScreenshot(result);
//    }
//
//    private void captureScreenshot(ITestResult result) {
//        try {
//            WebDriver driver = DriverManager.getDriver();
//            if (driver == null) {
//                System.err.println("❌ WebDriver instance is null, cannot capture screenshot for: " + result.getName());
//                return;
//            }
//
//            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            Path screenshotDir = Paths.get(SCREENSHOT_DIR);
//            Files.createDirectories(screenshotDir);
//
//            String screenshotPath = SCREENSHOT_DIR + "/" + result.getName() + ".png";
//            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
//            screenshotPaths.add(screenshotPath);
//
//            System.out.println("📸 Screenshot saved: " + screenshotPath);
//        } catch (Exception e) {
//            System.err.println("❌ Error capturing screenshot for " + result.getName() + ": " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        System.out.println("📨 Sending test results email...");
//        sendTestResultsEmail();
//    }
//
//    public void sendTestResultsEmail() {
//        try {
//            emailPage.sendTestResultsWithAttachments(screenshotPaths);
//            System.out.println("✅ Test results email sent successfully!");
//        } catch (Exception e) {
//            System.err.println("❌ Error sending test results email: " + e.getMessage());
//        }
//    }
//}

// This code will send the UnZip files to mail.

//package Listeners;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import pages.EmailPage;
//import utils.DriverManager;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestListener implements ITestListener {
//    private final StringBuilder report = new StringBuilder();
//    private final EmailPage emailPage = new EmailPage();
//    private final List<String> screenshotPaths = new ArrayList<>();
//    private final List<String> allureReportFiles = new ArrayList<>();
//
//    private static final String SCREENSHOT_DIR = "target/screenshots";
//    private static final String ALLURE_RESULTS_DIR = "target/allure-results";
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        report.append("❌ FAILED: ").append(result.getName()).append("\n");
//        captureScreenshot(result);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        report.append("✅ PASSED: ").append(result.getName()).append("\n");
//        captureScreenshot(result);
//    }
//
//    private void captureScreenshot(ITestResult result) {
//        try {
//            WebDriver driver = DriverManager.getDriver();
//            if (driver == null) {
//                System.err.println("❌ WebDriver instance is null, cannot capture screenshot for: " + result.getName());
//                return;
//            }
//
//            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            Path screenshotDir = Paths.get(SCREENSHOT_DIR);
//            Files.createDirectories(screenshotDir);
//
//            String screenshotPath = SCREENSHOT_DIR + "/" + result.getName() + ".png";
//            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
//            screenshotPaths.add(screenshotPath);
//
//            System.out.println("📸 Screenshot saved: " + screenshotPath);
//        } catch (Exception e) {
//            System.err.println("❌ Error capturing screenshot for " + result.getName() + ": " + e.getMessage());
//        }
//    }
//
//    private void collectAllureFiles() {
//        try {
//            File allureDir = new File(ALLURE_RESULTS_DIR);
//            if (allureDir.exists() && allureDir.isDirectory()) {
//                File[] files = allureDir.listFiles();
//                if (files != null) {
//                    for (File file : files) {
//                        allureReportFiles.add(file.getAbsolutePath());
//                    }
//                }
//            } else {
//                System.err.println("⚠️ Allure results directory not found: " + ALLURE_RESULTS_DIR);
//            }
//        } catch (Exception e) {
//            System.err.println("❌ Error collecting Allure files: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        System.out.println("📨 Sending test results email...");
//        collectAllureFiles(); // Collect individual Allure report files
//        sendTestResultsEmail();
//    }
//
//    public void sendTestResultsEmail() {
//        try {
//            emailPage.sendTestResultsWithAttachments(screenshotPaths, allureReportFiles);
//            System.out.println("✅ Test results email sent successfully!");
//        } catch (Exception e) {
//            System.err.println("❌ Error sending test results email: " + e.getMessage());
//        }
//    }
//}

// The below code will send mail along with subject.

//package Listeners;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.*;
//
//import pages.EmailPage;
//import utils.DriverManager;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestListener implements ITestListener {
//    private final StringBuilder report = new StringBuilder();
//    private final EmailPage emailPage = new EmailPage();
//    private final List<String> screenshotPaths = new ArrayList<>();
//    private final List<String> allureReportFiles = new ArrayList<>();
//
//    private static final String SCREENSHOT_DIR = "target/screenshots";
//    private static final String ALLURE_RESULTS_DIR = "target/allure-results";
//
//    private int passedTests = 0;
//    private int failedTests = 0;
//    private int skippedTests = 0;
//    private int totalTests = 0;
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        totalTests++;
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        passedTests++;
//        report.append("✔ PASSED: ").append(result.getName()).append("\n");
//        captureScreenshot(result);
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        failedTests++;
//        report.append("❌ FAILED: ").append(result.getName()).append("\n");
//        captureScreenshot(result);
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        skippedTests++;
//        report.append("⚠ SKIPPED: ").append(result.getName()).append("\n");
//    }
//
//    private void captureScreenshot(ITestResult result) {
//        try {
//            WebDriver driver = DriverManager.getDriver();
//            if (driver == null) {
//                System.err.println("❌ WebDriver instance is null, cannot capture screenshot for: " + result.getName());
//                return;
//            }
//
//            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            Path screenshotDir = Paths.get(SCREENSHOT_DIR);
//            Files.createDirectories(screenshotDir);
//
//            String screenshotPath = SCREENSHOT_DIR + "/" + result.getName() + ".png";
//            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
//            screenshotPaths.add(screenshotPath);
//
//            System.out.println("📸 Screenshot saved: " + screenshotPath);
//        } catch (Exception e) {
//            System.err.println("❌ Error capturing screenshot for " + result.getName() + ": " + e.getMessage());
//        }
//    }
//
//    private void collectAllureFiles() {
//        try {
//            File allureDir = new File(ALLURE_RESULTS_DIR);
//            if (allureDir.exists() && allureDir.isDirectory()) {
//                File[] files = allureDir.listFiles();
//                if (files != null) {
//                    for (File file : files) {
//                        allureReportFiles.add(file.getAbsolutePath());
//                    }
//                }
//            } else {
//                System.err.println("⚠️ Allure results directory not found: " + ALLURE_RESULTS_DIR);
//            }
//        } catch (Exception e) {
//            System.err.println("❌ Error collecting Allure files: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        System.out.println("📨 Sending test results email...");
//        collectAllureFiles(); // Collect individual Allure report files
//        sendTestResultsEmail();
//    }
//
//    public void sendTestResultsEmail() {
//        try {
//            String subject = (failedTests == 0) ? "✅ Test Execution Passed" : "❌ Test Execution Failed";
//
//            String emailBody = report.toString() +
//                    "\n\nTotal: " + totalTests +
//                    " | ✅ Passed: " + passedTests +
//                    " | ❌ Failed: " + failedTests +
//                    " | ⚠ Skipped: " + skippedTests;
//
//            emailPage.sendTestResultsWithAttachments(screenshotPaths, allureReportFiles, subject, emailBody);
//            System.out.println("✅ Test results email sent successfully!");
//        } catch (Exception e) {
//            System.err.println("❌ Error sending test results email: " + e.getMessage());
//        }
//    }
//}

 //This code will send results to multiple mails at a time.

package Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pages.EmailPage;
import utils.ScreenshotUtils;

import java.util.ArrayList;
import java.util.List;

public class TestListener implements ITestListener {
    private final List<String> screenshotPaths = new ArrayList<>();
    private final EmailPage emailPage = new EmailPage(); // ✅ Instantiate EmailPage once

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getName());
        String screenshotPath = ScreenshotUtils.captureScreenshot(result.getName());
        if (screenshotPath != null) {
            screenshotPaths.add(screenshotPath);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getName());
        String screenshotPath = ScreenshotUtils.captureScreenshot(result.getName());
        if (screenshotPath != null) {
            screenshotPaths.add(screenshotPath);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("📨 Sending Email with Test Results...");
        try {
            emailPage.sendTestResultsWithAttachments(screenshotPaths);
            System.out.println("✅ Email Sent Successfully!");
        } catch (Exception e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Other required overrides (empty implementations)
//    @Override
//    public void onTestStart(ITestResult result) {}
//    @Override
//    public void onTestSkipped(ITestResult result) {}
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
//    @Override
//    public void onStart(ITestContext context) {}
}
