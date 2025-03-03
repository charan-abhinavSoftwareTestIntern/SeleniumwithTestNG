package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

import java.awt.*;
import java.time.Duration;

public class LeaveRejectionPage extends SeleniumUtils {

    WebDriver driver;
    WebDriverWait wait;

    public LeaveRejectionPage (WebDriver driver){

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    // Locators
    public final By leaveManagementSection = By.xpath("//p[text()='Leave Management']");
    public final By requestSubSection = By.xpath("(//div[@class='d-flex align-items-center leaves-tab-container'])/button[2]");
    public final By nameFilter = By.cssSelector("[aria-label='NAME Filter Input']");
    public final By startDateHeader = By.xpath("//span[text()='START DATE']");
    public final By rejectButton = By.xpath("//button[text()='Reject']");
    public final By leaveRejectMessage = By.xpath("//div[@role='status']");
    public final By leaveRejectPopup = By.cssSelector("[class='modal-heading']");
    public final By closeButton = By.className("close-btn");
    public final By reasonBox = By.xpath("//label[text()='Reason*']/ancestor::div/textarea");
    public final By cancelButton = By.xpath("//div[@class='d-flex justify-content-end gap-3 mt-3']/button[1]");
    public final By submitButton = By.xpath("//div[@class='d-flex justify-content-end gap-3 mt-3']/button[2]");

    // Methods

    public void clickLeaveManagementSection(){
        clickElement(leaveManagementSection);
    }

    public void clickRequestSubSection(){
        clickElement(requestSubSection);
    }

    public void searchLeaveAppliedEmployee(String firstname){
        sendKeysToElement(nameFilter, firstname);
    }

    // Navigate to Status Field (Uses Dynamic Locator)
    public void navigatingToStatusField() throws AWTException {
        navigateToTargetUsingRobot(startDateHeader, 7);
    }

    public void clickRejectButton(){
        clickElement(rejectButton);
    }

    public void enterLeaveRejectionReason(String reason){
        sendKeysToElement(reasonBox, reason);
    }

    public void clickOnSubmitButton(){
        clickElement(submitButton);
    }

    // Assertions
    public boolean isLeaveRejectedMessageDisplayed(){
        return isElementDisplayed(leaveRejectMessage);
    }

    public boolean isLeaveRejectionPopupDisplayed(){
        return isElementDisplayed(leaveRejectPopup);
    }

}
