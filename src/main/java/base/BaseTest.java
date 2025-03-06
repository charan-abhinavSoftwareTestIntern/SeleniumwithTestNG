//package base;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.BeforeTest;
//import utils.ScreenshotUtils;
//
//import java.util.HashMap;
//import java.util.concurrent.TimeUnit;
//
//public class BaseTest {
//    public WebDriver driver;
//    public static HashMap<String, WebDriver> map = new HashMap<>();
//
//    /**
//     * Initializes the WebDriver based on the browser type
//     */
//    @BeforeSuite
//    public void initializeDriver() {
//        String browser = System.getProperty("browser", "chrome"); // Dynamic selection with default Chrome
//
//        switch (browser.toLowerCase()) {
//            case "chrome":
//                driver = initializeChromeDriver();
//                break;
//
//            case "firefox":
//                driver = initializeFirefoxDriver();
//                break;
//
//            default:
//                System.out.println("❌ Invalid browser specified. Please choose between Chrome or Firefox.");
//                return;
//        }
//
//        if (driver != null) {
//            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//            driver.manage().window().maximize();
//            map.put("charan", driver);
//
//            // ✅ Set WebDriver for ScreenshotUtil
//            ScreenshotUtils.setDriver(driver);
//        }
//    }
//
//    /**
//     * Initializes ChromeDriver with desired options
//     */
//    private WebDriver initializeChromeDriver() {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-notifications"); // Block notifications
//        options.addArguments("--start-maximized");       // Start maximized
//        System.out.println("🚀 Chrome browser is launched with configured options.");
//        return new ChromeDriver(options);
//    }
//
//    /**
//     * Initializes FirefoxDriver with desired options
//     */
//    private WebDriver initializeFirefoxDriver() {
//        WebDriverManager.firefoxdriver().setup();
//        FirefoxOptions options = new FirefoxOptions();
//        options.addPreference("dom.webnotifications.enabled", false); // Block notifications
//        System.out.println("🚀 Firefox browser is launched with configured options.");
//        return new FirefoxDriver(options);
//    }
//
//    /**
//     * Navigates to the specified URL
//     */
//    public void navigateToURL(String url) {
//        if (driver != null) {
//            driver.get(url);
//        } else {
//            System.out.println("⚠️ Driver not initialized. Cannot navigate to URL.");
//        }
//    }
//
//    /**
//     * Returns the current WebDriver instance
//     */
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    /**
//     * Closes the browser window and cleans up resources
//     */
//    @AfterSuite
//    public void closeWindow() {
//        if (driver != null) {
//            driver.quit();
//            System.out.println("✅ Browser is closed.");
//        }
//    }
//}
package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ScreenshotUtils;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public static HashMap<String, WebDriver> map = new HashMap<>();

    /**
     * Initializes the WebDriver based on the browser type.
     */
    @BeforeSuite
    public void initializeDriver() {
        String browser = System.getProperty("browser", "chrome"); // Dynamic selection with default Chrome

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = initializeChromeDriver();
                break;

            case "firefox":
                driver = initializeFirefoxDriver();
                break;

            default:
                System.out.println("❌ Invalid browser specified. Please choose between Chrome or Firefox.");
                return;
        }

        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Reduced implicit wait for better performance
            driver.manage().window().maximize();
            map.put("charan", driver);

            // ✅ Set WebDriver for ScreenshotUtil
            ScreenshotUtils.setDriver(driver);
        }
    }

    /**
     * Initializes ChromeDriver with optimized options for CI/CD execution.
     */
    private WebDriver initializeChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--headless=new");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-background-timer-throttling");
        options.addArguments("--disable-backgrounding-occluded-windows");
        options.addArguments("--disable-renderer-backgrounding");

        // **Fix:** Use a unique temporary Chrome user data directory
        options.addArguments("--user-data-dir=/tmp/chrome-user-data-" + System.currentTimeMillis());

        System.out.println("🚀 Chrome browser is launched with optimized options.");
        return new ChromeDriver(options);
    }

    /**
     * Initializes FirefoxDriver with necessary configurations.
     */
    private WebDriver initializeFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);
        System.out.println("🚀 Firefox browser is launched with configured options.");
        return new FirefoxDriver(options);
    }

    /**
     * Navigates to the specified URL.
     */
    public void navigateToURL(String url) {
        if (driver != null) {
            driver.get(url);
        } else {
            System.out.println("⚠️ Driver not initialized. Cannot navigate to URL.");
        }
    }

    /**
     * Returns the current WebDriver instance.
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Closes the browser window and cleans up resources.
     */
    @AfterSuite
    public void closeWindow() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser is closed.");
        }
    }
}
