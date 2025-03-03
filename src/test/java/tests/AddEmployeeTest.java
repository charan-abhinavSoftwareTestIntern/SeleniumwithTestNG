package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import utils.DataStorageUtils;
import utils.FakerDataUtils;

public class AddEmployeeTest  {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Add Employee test for UrBuddi")
    @Feature("Add Employee Feature")
    public void addingEmployee() {

        // Get WebDriver from BaseTest map
        WebDriver driver = BaseTest.map.get("charan");

        // Initialize AddEmployeePage
        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);

        // Start adding employee steps
        addEmployeePage.clickEmployeesSection();
        addEmployeePage.clickAddEmployee();

        // Generate and store fake employee details (email and password)
        addEmployeePage.generateAndStoreFakeEmployeeDetails();

        addEmployeePage.enterRole("HR");
        addEmployeePage.enterDob(FakerDataUtils.generateDateOfBirth());
        addEmployeePage.enterDoj(FakerDataUtils.generateValidJoiningDate());
        addEmployeePage.enterQualifications("B.Tech");
        addEmployeePage.enterDepartment(FakerDataUtils.generateDepartment());
        addEmployeePage.enterGender("Male");
        addEmployeePage.enterMobileNumber(FakerDataUtils.generateMobileNumber());
        addEmployeePage.enterBloodGroup("B+");
        addEmployeePage.enterDesignation(FakerDataUtils.generateDesignation());
        addEmployeePage.enterSalary(FakerDataUtils.generateRandomSalary());
        addEmployeePage.enterLocation(FakerDataUtils.generateCity());
        addEmployeePage.enterReportingTo("charanabhinav.pydimarri@optimworks.com");

        // Click on certificates and add qualifications
        addEmployeePage.clickOnCertificates();
        addEmployeePage.clickOnTenth();
        addEmployeePage.clickOnIntermediate();
        addEmployeePage.clickOnDegree();
        addEmployeePage.clickOnCertificates();

        // Click Add button to submit
        addEmployeePage.clickAddButton();

        // Assert if the employee is saved successfully
        Assert.assertTrue(addEmployeePage.isSavedSuccessfullyMessageDisplayed(),"saved successfully");

        // Optionally, print the generated email and password (for debugging or verification purposes)
        String generatedEmail = DataStorageUtils.getFakeEmail();
        String generatedPassword = DataStorageUtils.getFakePassword();
        String generatedFirstName = DataStorageUtils.getFakeFirstName();
        String generatedLastName = DataStorageUtils.getFakeLastName();
        String generatedEmployeeId = DataStorageUtils.getFakeEmployeeId();

        System.out.println("Generated Email: " + generatedEmail);
        System.out.println("Generated Password: " + generatedPassword);

        // Optionally assert that email and password are correctly generated (additional validation)
        Assert.assertNotNull(generatedEmail, "Generated email is null!");
        Assert.assertNotNull(generatedPassword, "Generated password is null!");
    }
}
