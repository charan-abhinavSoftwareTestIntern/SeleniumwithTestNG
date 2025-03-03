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

    // Set WebDriver instance
    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    // Capture screenshot and return its path
    public static String captureScreenshot(String screenshotName) {
        if (driver == null) {
            System.err.println("❌ WebDriver is null. Cannot capture screenshot.");
            return null;
        }

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "target/screenshots/" + screenshotName + ".png";

        try {
            Files.createDirectories(Paths.get("target/screenshots"));
            Files.copy(srcFile.toPath(), Paths.get(screenshotPath));
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all saved screenshot paths
    public static List<String> getScreenshotPaths(String directoryPath) {
        List<String> screenshotPaths = new ArrayList<>();
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            System.err.println("⚠️ Screenshot directory not found: " + directoryPath);
            return screenshotPaths;
        }

        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        if (files == null || files.length == 0) {
            System.err.println("⚠️ No screenshots found in directory: " + directoryPath);
            return screenshotPaths;
        }

        for (File file : files) {
            screenshotPaths.add(file.getAbsolutePath());
        }
        return screenshotPaths;
    }
}
