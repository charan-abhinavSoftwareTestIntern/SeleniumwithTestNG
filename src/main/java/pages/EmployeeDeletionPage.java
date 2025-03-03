package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DataStorageUtils;
import utils.SeleniumUtils;

import java.time.Duration;

public class EmployeeDeletionPage extends SeleniumUtils {

    WebDriver driver;
    WebDriverWait wait;

    public EmployeeDeletionPage(WebDriver driver){

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    String employeeId = DataStorageUtils.getFakeEmployeeId();

    // Locators
    public final By employeesSection = By.xpath("//p[text()='Employees']");
    public final By searchEmployeeWithId = By.cssSelector("[aria-label='EMP ID Filter Input']");
    // public final By employeeCheckBox = By.id("ag-1309-input");
    public final By employeeCheckBox = By.xpath("//*[text()='" + employeeId + "']/../descendant::input");
    public final By deleteIcon = By.cssSelector("[class='deleteIcon']");
    public final By employeeDeletedSuccessMessage = By.xpath("//div[text()='Employee Deleted Successfully']");

    // Methods

    public void clickEmployeesSection(){
        clickElement(employeesSection);
    }

    public void enterGeneratedFakeEmployeeId(String id){
        sendKeysToElement(searchEmployeeWithId, id);
    }

    public void clickOnCheckBox(){
        clickElement(employeeCheckBox);
    }

    public void clickDeleteIcon(){
        clickElement(deleteIcon);
    }

    public boolean isDeletedSuccessfullyMessageDisplayed(){
        return isElementDisplayed(employeeDeletedSuccessMessage);
    }
}
