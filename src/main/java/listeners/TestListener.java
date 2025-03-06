package listeners;

import org.testng.*;
import pages.EmailPage;
import utils.ChatNotifierUtils;
import utils.ZipUtils;
import utils.ScreenshotUtils;

import java.io.File;
import java.util.List;

public class TestListener implements ISuiteListener {

    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;
    private static int skippedTests = 0;

    private static final String ALLURE_RESULTS_DIR = "allure-results";
    private static final String ZIP_FILE_PATH = "allure-results.zip";

    @Override
    public void onStart(ISuite suite) {
        System.out.println("🔵 Test Suite Started: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        for (ISuiteResult result : suite.getResults().values()) {
            ITestContext context = result.getTestContext();
            totalTests += context.getAllTestMethods().length;
            passedTests += context.getPassedTests().size();
            failedTests += context.getFailedTests().size();
            skippedTests += context.getSkippedTests().size();
        }

        System.out.println("✅ Total: " + totalTests + " | ✅ Passed: " + passedTests +
                " | ❌ Failed: " + failedTests + " | ⚠️ Skipped: " + skippedTests);

        // 📧 Send Email with Suite Results
        sendSuiteResultsEmail();

        // 📢 Send Suite Results to Google Chat
        sendSuiteResultsToChat();
    }

    private void sendSuiteResultsEmail() {
        try {
            System.out.println("📧 Preparing to send email...");

            // Ensure ScreenshotUtils is properly implemented
            List<String> screenshotPaths = ScreenshotUtils.getAllScreenshots();

            // Ensure ZipUtils is correctly implemented
            File allureZipFile = ZipUtils.zipDirectory(ALLURE_RESULTS_DIR, ZIP_FILE_PATH);

            if (allureZipFile == null || !allureZipFile.exists()) {
                System.err.println("❌ Allure ZIP file was not created!");
                return;
            }

            EmailPage emailPage = new EmailPage();
            emailPage.sendSuiteResultsEmail(screenshotPaths);

            System.out.println("✅ Email sent successfully!");
        } catch (Exception e) {
            System.err.println("❌ Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendSuiteResultsToChat() {
        try {
            System.out.println("📢 Sending suite results to Google Chat...");

            String suiteName = "UrbuddiWithTestNG";
            String triggeredBy = "Charan Abhinav Pydimarri";
            String reportLink = "http://192.168.100.77:51643/index.html";

            ChatNotifierUtils.sendSuiteResultsToChat(
                    suiteName, triggeredBy, totalTests, passedTests, failedTests, skippedTests, reportLink
            );

            System.out.println("✅ Suite results sent to Google Chat successfully!");
        } catch (Exception e) {
            System.err.println("❌ Error sending suite results to Google Chat: " + e.getMessage());
        }
    }

    public static int getTotalTests() { return totalTests; }
    public static int getPassedTests() { return passedTests; }
    public static int getFailedTests() { return failedTests; }
    public static int getSkippedTests() { return skippedTests; }
}
