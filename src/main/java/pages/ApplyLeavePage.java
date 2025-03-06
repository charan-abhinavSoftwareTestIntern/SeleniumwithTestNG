package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DataStorageUtils;
import utils.FakerDataUtils;
import utils.SeleniumUtils;
import java.time.Duration;


public class ApplyLeavePage extends SeleniumUtils {

    WebDriver driver;
    WebDriverWait wait;
    private Faker faker;

    public ApplyLeavePage(WebDriver driver){
        this.driver = driver;
        faker = new Faker();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
    public final By fromDateField = By.id("fromDate");
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

    public void clickOkButtonOnLopWarning() {
        clickElement(okButton);
    }

    // Generate and store 'From' date
    public void generateAndStoreAppliedFromDate() {
        String fakeFromDate = FakerDataUtils.generateFutureFromDate();

        // Store 'From' date in its applied format (MM-dd-yyyy)
        DataStorageUtils.setAppliedFakeFromDate(fakeFromDate);
        System.out.println("Stored Faker 'From' Date (Applied Format): " + fakeFromDate);

        // Enter 'From' date in the input field
        sendKeysToElement(fromDateField, fakeFromDate);
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

//    public void applyLeave(String userLead, String userSubject, String userReason) {
//        LocalDate leaveStartDate = DateUtils.getFutureWeekday(3); // Get a valid future weekday, 3 days ahead
//        LocalDate leaveEndDate = leaveStartDate.plusDays(1); // Applying for a 2-day leave
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        String formattedFromDate = leaveStartDate.format(formatter);
//        String formattedToDate = leaveEndDate.format(formatter);
//
//        clickApplyLeaveSection();
//        clickApplyLeaveButton();
//        clickOkButtonOnLopWarning();
//        enterFromDate(formattedFromDate);
//        enterToDate(formattedToDate);
//        selectLeadFromDropdown(userLead);
//        enterSubject(userSubject);
//        enterReasonForLeave(userReason);
//        clickOnLeaveRadioButton();
//        clickOnSubmitButton();
//
//        System.out.println("Applied Leave From: " + formattedFromDate + " To: " + formattedToDate);
//    }
}
