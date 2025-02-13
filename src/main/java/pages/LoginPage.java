package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyFileReaderUtil;
import utils.SeleniumUtils;

import java.time.Duration;

public class LoginPage extends SeleniumUtils {

    // Locators
    public final By emailField = By.id("userEmail");
    public final By passwordField = By.id("userPassword");
    public final By loginButton = By.cssSelector("[type='submit']");
    public final By forgotPasswordLink = By.className("forgot-pswd");
    public final By invalidCredentialsError = By.cssSelector("//p[contains(@class,'err-msg-display')]");
    public final By dashboardPageTitle = By.xpath("//div[@class='page-header-container']/p");

    WebDriver driver;//instance
    PropertyFileReaderUtil propertyFileReader;

    // Constructor to initialize driver and PropertyFileReader
    public LoginPage(WebDriver driver) {// instance
        this.driver = driver;
        this.propertyFileReader = new PropertyFileReaderUtil();

    }

    // Fetch base URL from the properties file
    public String getBaseUrl(){
        return propertyFileReader.getProperty("baseURL");
    }

    /// Methods
    public void enterEmailId(String userName) {
        sendKeysToElement(emailField, userName );
    }

    public void enterPassword(String secretPassword) {
        sendKeysToElement(passwordField, secretPassword);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    // Method to wait for and accept an alert
    public void handleAlert(WebDriver driver) {
        try {
            // Initialize WebDriverWait with a timeout of 10 seconds
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait until the alert is present
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            // Accept the alert
            alert.accept();

            System.out.println("Alert accepted successfully.");
        } catch (Exception e) {
            System.out.println("No alert present or an error occurred: " + e.getMessage());
        }
    }


    /// Assertions
    public String getDashboardTitle() {
        WebElement dashboardTitleDisplay = driver.findElement(By.xpath("//div[@class='page-header-container']/p"));
        return dashboardTitleDisplay.getText();
    }

//    private PropertyFileReader propertyFileReader;

    public LoginPage(){
        propertyFileReader = new PropertyFileReaderUtil(); // Initialize propertyFileReader to get properties
    }

    public void navigateToHomePage() {
        // Use the base URL from the properties file
        String url = getBaseUrl();
        System.out.println("Navigating to: " + url);
        // Code to navigate to the homepage (for example using WebDriver)
    }

}
