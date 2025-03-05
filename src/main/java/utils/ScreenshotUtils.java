package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScreenshotUtils {
    private static WebDriver driver;
    private static final String SCREENSHOTS_DIR = "target/screenshots";

    /**
     * Set WebDriver instance.
     * @param webDriver WebDriver instance.
     */
    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    /**
     * Capture a screenshot and return its file path.
     * @param screenshotName Name of the screenshot file (without extension).
     * @return Absolute path of the saved screenshot or null if an error occurs.
     */
    public static String captureScreenshot(String screenshotName) {
        if (driver == null) {
            System.err.println("❌ WebDriver is null. Cannot capture screenshot.");
            return null;
        }

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = SCREENSHOTS_DIR + "/" + screenshotName + ".png";

        try {
            Files.createDirectories(Paths.get(SCREENSHOTS_DIR));
            Files.copy(srcFile.toPath(), Paths.get(screenshotPath));
            System.out.println("📸 Screenshot saved: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            System.err.println("❌ Error saving screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieve all screenshot file paths from the default screenshots directory.
     * @return List of screenshot file paths.
     */
    public static List<String> getAllScreenshots() {
        List<String> screenshotPaths = new ArrayList<>();
        File directory = new File(SCREENSHOTS_DIR);

        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("⚠️ Screenshot directory not found: " + SCREENSHOTS_DIR);
            return screenshotPaths;
        }

        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        if (files == null || files.length == 0) {
            System.err.println("⚠️ No screenshots found in directory: " + SCREENSHOTS_DIR);
            return screenshotPaths;
        }

        for (File file : files) {
            screenshotPaths.add(file.getAbsolutePath());
        }

        System.out.println("📁 Found " + screenshotPaths.size() + " screenshots.");
        return screenshotPaths;
    }
}
