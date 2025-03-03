package Listeners;

import base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import pages.EmailPage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestListener implements ITestListener {
    private final StringBuilder report = new StringBuilder();
    private final EmailPage emailPage = new EmailPage();
    private final List<String> screenshotPaths = new ArrayList<>();
    private static final String SCREENSHOT_DIR = "target/screenshots";
    private static final String ALLURE_RESULTS_ZIP = "target/allure-results.zip";

    private int passedTests = 0;
    private int failedTests = 0;
    private int skippedTests = 0;
    private int totalTests = 0;

    @Override
    public void onTestStart(ITestResult result) {
        totalTests++;
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        passedTests++;
        report.append("✔ PASSED: ").append(result.getName()).append("\n");
        captureScreenshot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failedTests++;
        report.append("❌ FAILED: ").append(result.getName()).append("\n");
        captureScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skippedTests++;
        report.append("⚠ SKIPPED: ").append(result.getName()).append("\n");
    }

    private void captureScreenshot(ITestResult result) {
        try {
            WebDriver driver = BaseTest.map.get("charan");
            if (driver == null) {
                System.err.println("❌ WebDriver instance is null, cannot capture screenshot for: " + result.getName());
                return;
            }

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path screenshotDir = Paths.get(SCREENSHOT_DIR);
            Files.createDirectories(screenshotDir);

            String screenshotPath = SCREENSHOT_DIR + "/" + result.getName() + ".png";
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
            screenshotPaths.add(screenshotPath);

            System.out.println("📸 Screenshot saved: " + screenshotPath);
        } catch (Exception e) {
            System.err.println("❌ Error capturing screenshot for " + result.getName() + ": " + e.getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("📨 Sending test results email...");
        sendTestResultsEmail();
    }

    public void sendTestResultsEmail() {
        try {
            String subject = (failedTests == 0) ? "✅ Test Execution Passed" : "❌ Test Execution Failed";
            String emailBody = report.toString() +
                    "\n\nTotal: " + totalTests +
                    " | ✅ Passed: " + passedTests +
                    " | ❌ Failed: " + failedTests +
                    " | ⚠ Skipped: " + skippedTests;

            List<String> allureReportFiles = new ArrayList<>();
            allureReportFiles.add(ALLURE_RESULTS_ZIP);
            emailPage.sendTestResultsWithAttachments(screenshotPaths, allureReportFiles);
            System.out.println("✅ Test results email sent successfully!");
        } catch (Exception e) {
            System.err.println("❌ Error sending test results email: " + e.getMessage());
        }
    }
}
