// This class is used to send results for multiple mails at a time.

package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScreenshotUtils {
    private static WebDriver driver;

    // ✅ Initialize WebDriver (Call this from BaseTest)
    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    // ✅ Capture Screenshot and return the file path
    public static String captureScreenshot(String testName) {
        if (driver == null) {
            System.err.println("⚠️ WebDriver is not initialized!");
            return null;
        }

        // Generate timestamped filename
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = "screenshots/" + testName + "_" + timestamp + ".png";

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotPath);
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("📸 Screenshot saved: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            System.err.println("❌ Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }
}
