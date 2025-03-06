package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

import java.awt.*;
import java.time.Duration;


public class LeaveApprovalPage extends SeleniumUtils {

    WebDriver driver;
    WebDriverWait wait;

    public LeaveApprovalPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    public final By leaveManagementSection = By.xpath("//p[text()='Leave Management']");
    public final By requestSubSection = By.xpath("(//div[@class='d-flex align-items-center leaves-tab-container'])/button[2]");
    public final By nameFilter = By.cssSelector("[aria-label='NAME Filter Input']");
    public final By startDateHeader = By.xpath("//span[text()='START DATE']");
    public final By approveButton = By.xpath("//button[text()='Approve']");
    public final By leaveApproveMessage = By.xpath("//div[@role='status']");


    // Methods
    public void clickOnLeaveManagementSection() {
        clickElement(leaveManagementSection);
    }

    public void clickOnRequestSubSection(){
        clickElement(requestSubSection);
    }

    public void searchForLeaveAppliedEmployee(String firstName){
        sendKeysToElement(nameFilter, firstName);
    }

    public void navigatingToStatusField() throws AWTException {
        navigateToTargetUsingRobot(startDateHeader, 7);
    }

    public void clickOnApproveButton(){
        clickElement(approveButton);
    }

    // Assertion
    public boolean isLeaveApprovedMessageDisplayed(){
        return isElementDisplayed(leaveApproveMessage);
    }

}
