package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ApplyLeavePage;
import utils.FakerDataUtils;

public class ApplyLeaveTest {

    @Test
    public void applyingLeave () throws InterruptedException {

        // Get WebDriver from BaseTest map
        WebDriver driver = BaseTest.map.get("charan");

        // Initialize ApplyLeavePage object
        ApplyLeavePage applyLeavePage = new ApplyLeavePage(driver);

        // Generate Faker data for leave application
        String fromDate = FakerDataUtils.generateFutureFromDate();  // Generate 'From' date
        String toDate = FakerDataUtils.generateFutureToDate(fromDate); // Generate 'To' date based on 'From' date


        // Perform page actions (clicking sections, applying leave with popup handling)
        applyLeavePage.clickApplyLeaveSection();
        applyLeavePage.clickApplyLeaveButton();
        applyLeavePage.clickOkButtonOnLopWarning();
        applyLeavePage.enterFromDate(fromDate);
        applyLeavePage.enterToDate(toDate);
        applyLeavePage.selectLeadFromDropdown("nikhil.pachipala@optimworks.com");
        applyLeavePage.enterSubject(FakerDataUtils.generateFakeLeaveSubject());
        applyLeavePage.enterReasonForLeave(FakerDataUtils.generateFakeLeaveReason());
        applyLeavePage.clickOnLeaveRadioButton();
        applyLeavePage.clickOnSubmitButton();

        // Assert if the employee is Applied leave Successfully
        Assert.assertTrue(applyLeavePage.leaveAppliedSuccessfullyIsDisplayed(), "Leave Applied Successfully");
    }


}
