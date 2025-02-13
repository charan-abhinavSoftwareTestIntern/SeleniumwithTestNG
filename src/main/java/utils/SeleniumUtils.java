package utils;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumUtils {

    WebDriver driver = BaseTest.map.get("charan");

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

    // Click on a fetched web element
    public void clickElement(By locator) {
        getWebElement(locator).isDisplayed();
        getWebElement(locator).click();
    }


    // Send text to an input field
    public void sendKeysToElement(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator, 10);
        element.clear();  // Clear any pre-filled text
        element.sendKeys(text);
    }

    // Handle dropdown by selecting an option (text)
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

    // Check if an element is displayed
    public boolean isElementDisplayed(By locator) {
        waitForElementToBeVisible(locator, 30);
        WebElement element = driver.findElement(locator);
        return element.isDisplayed();
    }

    // Close the current browser window
    public void closeWindow() {
        driver.close();
    }

}


