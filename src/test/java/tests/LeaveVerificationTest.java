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
import utils.PropertyFileReaderUtil;

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
        leaveVerificationPage.searchForAppliedDate(PropertyFileReaderUtil.getProperty("currentDate"));

        //Assert.assertTrue(leaveVerificationPage.getLeaveStatus(), "Approved");
        Assert.assertTrue(leaveVerificationPage.getLeaveStatus(), "Rejected");
    }
}
