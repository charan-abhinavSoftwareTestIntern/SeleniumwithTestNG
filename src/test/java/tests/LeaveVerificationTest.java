package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LeaveVerificationPage;
import utils.DataStorageUtils;
import utils.FakerDataUtils;

public class LeaveVerificationTest extends BaseTest {

    // Testcase to check leave is approved.
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Leave verification test for UrBuddi")
    @Feature("Leave verification Feature")
    public void verifyLeaveStatus(){

        WebDriver driver = BaseTest.map.get("charan");

        LeaveVerificationPage leaveVerificationPage = new LeaveVerificationPage(driver);
        leaveVerificationPage.clickOnLeaveManagementSection();

        // Retrieve stored 'From' date in applied format (MM-dd-yyyy)
        String appliedFromDate = DataStorageUtils.getAppliedFakerFromDate();
        System.out.println("Retrieved Stored 'From' Date (Applied Format): " + appliedFromDate);

        // Convert to verification format (dd-MM-yyyy)
        String verificationDate = FakerDataUtils.convertDateForVerification(appliedFromDate);
        System.out.println("Converted 'From' Date for Verification: " + verificationDate);

        // Search for the applied leave date in the verification format
        leaveVerificationPage.searchForAppliedDate(verificationDate);

        //Assert.assertTrue(leaveVerificationPage.getLeaveStatus(), "Approved");
        Assert.assertTrue(leaveVerificationPage.getLeaveStatus(), "Leave status should be 'Rejected'");

    }
}
