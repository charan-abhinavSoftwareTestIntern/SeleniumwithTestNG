package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

import java.time.Duration;

public class LeaveVerificationPage extends SeleniumUtils {

    WebDriver driver;
    WebDriverWait wait;
    private Faker faker;

    public LeaveVerificationPage(WebDriver driver){
        faker = new Faker();
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    public final By leaveManagementSection = By.xpath("//p[text()='Leave Management']");
    public final By startDateField = By.cssSelector("[aria-label='START DATE Filter Input']");
    public final By approvedStatus = By.xpath("//div[text()='Approved']");


    // Methods
    public void clickOnLeaveManagementSection(){
        clickElement(leaveManagementSection);
    }


    public void searchForAppliedDate(String appliedDate){
        sendKeysToElement(startDateField, appliedDate);
    }

    // Assertions
//    public boolean getLeaveStatus(){
//        WebElement leaveStatusDisplay = driver.findElement(By.xpath("//div[text()='Approved']"));
//        return leaveStatusDisplay.isDisplayed();
//    }

    public boolean getLeaveStatus(){
        WebElement leaveStatusDisplay = driver.findElement(By.xpath("//div[text()='Rejected']"));
        return leaveStatusDisplay.isDisplayed();
    }
}
