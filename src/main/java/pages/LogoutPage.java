package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

import java.time.Duration;

public class LogoutPage extends SeleniumUtils {

    WebDriver driver;
    WebDriverWait wait;

    public LogoutPage (WebDriver driver){

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    public final By logoutSection = By.cssSelector("[class='d-flex align-items logout-list-item mt-2']");
    public final By logoutPageTitle = By.cssSelector("[class='modal-heading']");
    public final By closeButton = By.cssSelector("[class='close-btn']");
    public final By noButton = By.xpath("//div[@class='d-flex gap-2']/button[1]");
    public final By yesButton = By.xpath("//div[@class='d-flex gap-2']/button[2]");
    public final By welcomeMessage = By.cssSelector("[class='welcomeMessage']");

    // Methods
    public void clickLogoutSection(){
        clickElement(logoutSection);
    }

    public void clickLogoutButton(){
        clickElement(yesButton);
    }

    //Assertions
    public String getLogoutTitle(){
        WebElement logoutTitleDisplay = driver.findElement(By.cssSelector("[class='modal-heading']"));
        return logoutTitleDisplay.getText();
    }

    public String getWelcomeMessage(){
        WebElement welcomeMessageDisplay = driver.findElement(By.cssSelector("[class='welcomeMessage']"));
        return welcomeMessageDisplay.getText();
    }

}
