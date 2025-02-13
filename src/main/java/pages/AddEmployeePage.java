package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DataStorageUtils;
import utils.FakerDataUtils;
import utils.PropertyFileReaderUtil;
import utils.SeleniumUtils;

public class AddEmployeePage extends SeleniumUtils {

    WebDriver driver;
    private Faker faker;

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        faker = new Faker();  // Initialize Faker instance to generate fake data

    }

    //Locators
    public final By employeesSection = By.xpath("//p[text()='Employees']");
    public final By addEmployeeButton = By.xpath("//button[text()='Add Employee']");
    public final By addEmployeePageTitle = By.className("modal-heading");
    public final By closeIcon = By.cssSelector(".close-btn-div svg");
    public final By firstName = By.name("firstName");
    public final By lastName = By.name("lastName");
    public final By employeeId = By.id("employeeID");
    public final By email = By.cssSelector("[name='email']");
    public final By role = By.id("role");
    public final By password = By.cssSelector("[name='password']");
    public final By dateOfBirth = By.cssSelector("[name='dob']");
    public final By joiningDate = By.name("joiningDate");
    public final By qualifications = By.cssSelector("[name='qualifications']");
    public final By department = By.cssSelector("[name='department']");
    public final By gender = By.cssSelector("[name='gender']");
    public final By mobileNumber = By.name("mobileNumber");
    public final By bloodGroup = By.name("bloodGroup");
    public final By designation = By.cssSelector("[name='designation']");
    public final By salary = By.id("salary");
    public final By location = By.cssSelector("[name='location']");
    public final By reportingTo = By.cssSelector("[name='reportingTo']");
    public final By certificatesDropdown = By.cssSelector(".dropdown-btn");
    public final By tenthCheckbox = By.cssSelector("[name='10th']");
    public final By intermediateCheckbox = By.cssSelector("[name='Intermediate']");
    public final By degreeCheckbox = By.cssSelector("[name='Degree']");
    public final By addButton = By.cssSelector(".add-employee-form-container + * button:last-of-type");
    public final By savedSuccessfully = By.xpath("(//div[@role='status'])[last()]");

    // Methods
    public void clickEmployeesSection() {
        clickElement(employeesSection);
    }

    public void clickAddEmployee() {
        clickElement(addEmployeeButton);
    }

    public void enterFirstName(String userFirstName) {
        sendKeysToElement(firstName, userFirstName);
    }

    public void enterLastName(String userLastName) {
        sendKeysToElement(lastName, userLastName);
    }

    public void enterEmpId(String userid) {
        sendKeysToElement(employeeId, userid);
    }


    public void enterRole(String userRoleName) {
        selectDropdownOptionByText(role, userRoleName);
    }


    // Modified to generate and store fake email and password
    public void generateAndStoreFakeEmployeeDetails() {
        String fakeEmail = FakerDataUtils.generateEmail();
        String fakePassword = FakerDataUtils.generatePassword();

        // Store the generated email and password in DataStorage
        DataStorageUtils.setFakeEmail(fakeEmail);
        DataStorageUtils.setFakePassword(fakePassword);

        // Fill in the form fields with fake data
        sendKeysToElement(email, fakeEmail);
        sendKeysToElement(password, fakePassword);
    }

    public void enterDob(String userDob) {
        sendKeysToElement(dateOfBirth, userDob);
    }

    public void enterDoj(String userDoj) {
        sendKeysToElement(joiningDate, userDoj);
    }

    public void enterQualifications(String userQualifications) {
        selectDropdownOptionByText(qualifications, userQualifications);
    }

    public void enterDepartment(String userDepartment) {
        sendKeysToElement(department, userDepartment);
    }

    public void enterGender(String userGender) {
        selectDropdownOptionByText(gender, userGender);
    }

    public void enterMobileNumber(String userMobileNumber) {
        sendKeysToElement(mobileNumber, userMobileNumber);
    }

    public void enterBloodGroup(String userBloodGroup) {
        selectDropdownOptionByText(bloodGroup, userBloodGroup);
    }

    public void enterDesignation(String userDesignation) {
        sendKeysToElement(designation, userDesignation);
    }

    public void enterSalary(String userSalary) {
        sendKeysToElement(salary, userSalary);
    }

    public void enterLocation(String userLocation) {
        sendKeysToElement(location, userLocation);
    }

    public void enterReportingTo(String userReportingTo) {
        selectDropdownOptionByText(reportingTo, userReportingTo);
    }

    public void clickOnCertificates() {
        clickElement(certificatesDropdown);
    }

    public void clickOnTenth() {
        clickElement(tenthCheckbox);
    }

    public void clickOnIntermediate() {
        clickElement(intermediateCheckbox);
    }

    public void clickOnDegree() {
        waitForElementToBeVisible(degreeCheckbox, PropertyFileReaderUtil.getWaitInSeconds());
        clickElement(degreeCheckbox);
    }

    public void clickAddButton() {
        waitForElementToBeVisible(addButton, 30);
        clickElement(addButton);
    }

    public boolean isSavedSuccessfullyMessageDisplayed() {
        return isElementDisplayed(savedSuccessfully);
    }

}
