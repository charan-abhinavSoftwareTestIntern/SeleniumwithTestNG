package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.SeleniumUtils;

public class ApplyLeavePage extends SeleniumUtils {

    WebDriver driver;
    private Faker faker;

    public ApplyLeavePage(WebDriver driver){
        this.driver = driver;
        faker = new Faker();
    }

    // Locators
    public final By leaveManagementSection = By.xpath("//p[text()='Leave Management']");
    public final By applyLeaveButton = By.xpath("//button[text()='Apply Leave']");
    public final By lopWarningPopup = By.cssSelector("[class='modal-content']");
    public final By lopPopupTitle = By.cssSelector("[class='modal-heading']");
    public final By closeIconLopWarning = By.className("close-btn");
    public final By cancelButton = By.xpath("(//div[@class='d-flex flex-row justify-content-center gap-5'])[2]/button[1]");
    public final By okButton = By.xpath("(//div[@class='d-flex flex-row justify-content-center gap-5'])[2]/button[2]");
    public final By applyLeaveTitle = By.cssSelector("[class='modal-heading']");
    public final By closeIconApplyLeave = By.cssSelector(".close-btn-div svg");
    public final By fromDate = By.id("fromDate");
    public final By toDate = By.id("toDate");
    public final By selectLead = By.cssSelector("[name='lead']");
    public final By subjectInputField = By.cssSelector("[name='subject']");
    public final By reasonForLeave = By.cssSelector("[name='reason']");
    public final By leaveRadioButton = By.id("leave");
    public final By workFromHomeRadioButton = By.id("workFromHome");
    public final By cancelButtonApplyLeave = By.xpath("//div[@class='form-btn-container']/button");
    public final By submitButton = By.cssSelector("[type='submit']");
    public final By leaveAppliedSuccessfully = By.xpath("(//div[@role='status'])[last()]");

    //Methods

    public void clickApplyLeaveSection(){
        clickElement(leaveManagementSection);
    }

    public void clickApplyLeaveButton(){
        clickElement(applyLeaveButton);
    }

//    // Method to perform actions on the popup (click OK button)
    public void clickOkButtonOnLopWarning() {
        clickElement(okButton);
    }

    // Method to apply leave (filling from date)
    public void enterFromDate(String leaveFromDate){
        sendKeysToElement(fromDate, leaveFromDate);
    }

    public void enterToDate(String leaveToDate){
        sendKeysToElement(toDate, leaveToDate);
    }

    public void selectLeadFromDropdown(String userLead){
        selectDropdownOptionByText(selectLead, userLead);
    }

    public void enterSubject(String userSubject){
        sendKeysToElement(subjectInputField, userSubject);
    }

    public void enterReasonForLeave(String userReason){
        sendKeysToElement(reasonForLeave, userReason);
    }

    public void clickOnLeaveRadioButton(){
        clickElement(leaveRadioButton);
    }

    public void clickOnSubmitButton(){
        clickElement(submitButton);
    }

    public boolean leaveAppliedSuccessfullyIsDisplayed(){
        return isElementDisplayed(leaveAppliedSuccessfully);
    }

}
