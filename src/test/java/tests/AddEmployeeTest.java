package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import utils.DataStorageUtils;
import utils.FakerDataUtils;

public class AddEmployeeTest  {

    @Test
    public void addingEmployee() throws InterruptedException {

        // Get WebDriver from BaseTest map
        WebDriver driver = BaseTest.map.get("charan");

        // Initialize AddEmployeePage
        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);

        // Start adding employee steps
        addEmployeePage.clickEmployeesSection();
        addEmployeePage.clickAddEmployee();

        // Generate and store fake employee details (email and password)
        addEmployeePage.generateAndStoreFakeEmployeeDetails();

        addEmployeePage.enterFirstName(FakerDataUtils.generateFirstName());
        addEmployeePage.enterLastName(FakerDataUtils.generateLastName());
        addEmployeePage.enterEmpId(FakerDataUtils.generateEmployeeId());
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

        System.out.println("Generated Email: " + generatedEmail);
        System.out.println("Generated Password: " + generatedPassword);

        // Optionally assert that email and password are correctly generated (additional validation)
        Assert.assertNotNull(generatedEmail, "Generated email is null!");
        Assert.assertNotNull(generatedPassword, "Generated password is null!");
    }
}
