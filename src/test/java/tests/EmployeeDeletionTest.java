package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmployeeDeletionPage;
import utils.DataStorageUtils;


@Test
@Severity(SeverityLevel.CRITICAL)
@Feature("Employee Deletion Feature")
public class EmployeeDeletionTest {

    public void deletingEmployee() throws InterruptedException {

        WebDriver driver = BaseTest.map.get("charan");

        EmployeeDeletionPage employeeDeletionPage = new EmployeeDeletionPage(driver);

        employeeDeletionPage.clickEmployeesSection();
        employeeDeletionPage.enterGeneratedFakeEmployeeId(DataStorageUtils.getFakeEmployeeId());
        employeeDeletionPage.clickOnCheckBox();
        employeeDeletionPage.clickDeleteIcon();

        Assert.assertTrue(employeeDeletionPage.isDeletedSuccessfullyMessageDisplayed(), "Employee Deleted Successfully");

        employeeDeletionPage.enterGeneratedFakeEmployeeId(DataStorageUtils.getFakeEmployeeId());

    }
}
