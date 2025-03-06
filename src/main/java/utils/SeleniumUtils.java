package utils;

import base.BaseTest;
import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SeleniumUtils {

    private WebDriver driver;

    // Constructor to initialize WebDriver
    public SeleniumUtils() {
        this.driver = BaseTest.map.get("charan");
    }

    // Wait for an element to be visible
    public WebElement waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Fetching web element of locator
    public WebElement getWebElement(By locator) {
        return driver.findElement(locator);
    }

    public void url(String url) {
        driver.get(url);
    }

//     Handle dropdown by selecting an option (text)
    public void selectDropdownOptionByText(By locator, String optionText) {
        WebElement dropdown = waitForElementToBeVisible(locator, 10);
        dropdown.click(); // Click to open the dropdown
        List<WebElement> options = dropdown.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if (option.getText().equals(optionText)) {
                option.click();
                break;
            }
        }
    }

    // Click on a fetched web element with error handling
    public void clickElement(By locator) {
        try {
            WebElement element = waitForElementToBeVisible(locator, 10);
            element.click();
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to click on element: " + locator, e);
        }
    }


    // Send text to an input field
    public void sendKeysToElement(By locator, String text) {
        try {
            WebElement element = waitForElementToBeVisible(locator, 10);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to send keys to element: " + locator, e);
        }
    }

    // Check if an element is displayed
    public boolean isElementDisplayed(By locator) {
        try {
            return waitForElementToBeVisible(locator, 10).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    // Navigate to a target element using Robot Class
    public void navigateToTargetUsingRobot(By locator, int rightArrowPresses) throws AWTException {
        Robot robot = new Robot();
        WebElement header = waitForElementToBeVisible(locator, 10);
        header.click();
        robot.delay(1000);
        for (int i = 0; i < rightArrowPresses; i++) {
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
            robot.delay(500);
        }
    }

    // JavaScript Click Method
    public static void clickUsingJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

}