package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LeaveApprovalPage;
import utils.DataStorageUtils;

import java.awt.*;

public class LeaveApprovalTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Leave Approval test for UrBuddi")
    @Feature("Leave Approval Feature")
    public void approveLeave() throws AWTException {

        String firstName = DataStorageUtils.getFakeFirstName();

        WebDriver driver = BaseTest.map.get("charan");

        LeaveApprovalPage leaveApprovalPage = new LeaveApprovalPage(driver);

        leaveApprovalPage.clickOnLeaveManagementSection();
        leaveApprovalPage.clickOnRequestSubSection();
        leaveApprovalPage.searchForLeaveAppliedEmployee(firstName);
        leaveApprovalPage.navigatingToStatusField();
        leaveApprovalPage.clickOnApproveButton();

        Assert.assertTrue(leaveApprovalPage.isLeaveApprovedMessageDisplayed(), "Leave Approved");

    }

}
