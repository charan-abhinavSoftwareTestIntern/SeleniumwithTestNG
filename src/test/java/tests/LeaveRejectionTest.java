package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LeaveRejectionPage;
import utils.DataStorageUtils;
import utils.FakerDataUtils;

import java.awt.*;

public class LeaveRejectionTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Leave rejection test for UrBuddi")
    @Feature("Leave rejection Feature")
    public void rejectLeave() throws AWTException {

        String firstname = DataStorageUtils.getFakeFirstName();
        WebDriver driver = BaseTest.map.get("charan");

        LeaveRejectionPage leaveRejectionPage = new LeaveRejectionPage(driver);

        leaveRejectionPage.clickLeaveManagementSection();
        leaveRejectionPage.clickRequestSubSection();
        leaveRejectionPage.searchLeaveAppliedEmployee(firstname);
        leaveRejectionPage.navigatingToStatusField();
        leaveRejectionPage.clickRejectButton();

       Assert.assertTrue(leaveRejectionPage.isLeaveRejectionPopupDisplayed());

        leaveRejectionPage.enterLeaveRejectionReason(FakerDataUtils.generateFakeLeaveReason());
        leaveRejectionPage.clickOnSubmitButton();

        Assert.assertTrue(leaveRejectionPage.isLeaveRejectedMessageDisplayed(), "Leave Rejected");
    }
}
