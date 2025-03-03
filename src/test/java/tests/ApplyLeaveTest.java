//package tests;
//
//import base.BaseTest;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import pages.ApplyLeavePage;
//import utils.DateUtils;
//import utils.FakerDataUtils;
//
//import java.time.LocalDate;
//
//public class ApplyLeaveTest {
//
//    @Test
//    public void applyingLeave () throws InterruptedException {
//
//        // Get WebDriver from BaseTest map
//        WebDriver driver = BaseTest.map.get("charan");
//
//        // Initialize ApplyLeavePage object
//        ApplyLeavePage applyLeavePage = new ApplyLeavePage(driver);
//
//        // Get the current date
//        LocalDate today = LocalDate.now();
//
//        // Ensure leave is scheduled only on working days
//        LocalDate leaveFromDate = DateUtils.getNextWeekday(today);
//        LocalDate leaveToDate = leaveFromDate.plusDays(1); // Example: Applying for 2 days
//
//        // Convert to String format (YYYY-MM-DD)
//        String fromDate = leaveFromDate.toString();
//        String toDate = DateUtils.getNextWeekday(leaveToDate).toString();
//
////        // Generate Faker data for leave application
////        String fromDate = FakerDataUtils.generateFutureFromDate();  // Generate 'From' date
////        String toDate = FakerDataUtils.generateFutureToDate(fromDate); // Generate 'To' date based on 'From' date
//
//        // Perform page actions (clicking sections, applying leave with popup handling)
//        applyLeavePage.clickApplyLeaveSection();
//        applyLeavePage.clickApplyLeaveButton();
//        applyLeavePage.clickOkButtonOnLopWarning();
//        applyLeavePage.enterFromDate(fromDate);
//        applyLeavePage.enterToDate(toDate);
//        applyLeavePage.selectLeadFromDropdown("charanabhinav.pydimarri@optimworks.com");
//        applyLeavePage.enterSubject(FakerDataUtils.generateFakeLeaveSubject());
//        applyLeavePage.enterReasonForLeave(FakerDataUtils.generateFakeLeaveReason());
//        applyLeavePage.clickOnLeaveRadioButton();
//        applyLeavePage.clickOnSubmitButton();
//
//        // Assert if the employee is Applied leave Successfully
//        Assert.assertTrue(applyLeavePage.leaveAppliedSuccessfullyIsDisplayed(), "Leave Applied Successfully");
//    }
//
//
//}
package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ApplyLeavePage;
import utils.FakerDataUtils;
import utils.PropertyFileReaderUtil;

public class ApplyLeaveTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Apply Leave test for UrBuddi")
    @Feature("Apply Leave Feature")
//    public void applyingLeave() throws InterruptedException {
//        // Get WebDriver from BaseTest map
//        WebDriver driver = BaseTest.map.get("charan");
//
//        // Initialize ApplyLeavePage object
//        ApplyLeavePage applyLeavePage = new ApplyLeavePage(driver);
//
//        // Apply leave (ensuring it's only on working days)
//        applyLeavePage.applyLeave(PropertyFileReaderUtil.getProperty("username"), FakerDataUtils.generateFakeLeaveSubject(), FakerDataUtils.generateFakeLeaveReason() );
//
//        // Assert if the leave was applied successfully
//        Assert.assertTrue(applyLeavePage.leaveAppliedSuccessfullyIsDisplayed(), "Leave Applied Successfully");
//    }

    public void applyingLeave() throws InterruptedException {
        WebDriver driver = BaseTest.map.get("charan");
        ApplyLeavePage applyLeavePage = new ApplyLeavePage(driver);

        // Receives date from test data properties
        String userSpecifiedDate = PropertyFileReaderUtil.getProperty("currentDate");
        System.out.println("Leave Date from properties: " + userSpecifiedDate);// Example input date
//        applyLeavePage.applyLeave(PropertyFileReaderUtil.getProperty("username"), FakerDataUtils.generateFakeLeaveSubject(), FakerDataUtils.generateFakeLeaveReason());
        applyLeavePage.applyLeave(userSpecifiedDate, PropertyFileReaderUtil.getProperty("username"), FakerDataUtils.generateFakeLeaveSubject(), FakerDataUtils.generateFakeLeaveReason());

        Assert.assertTrue(applyLeavePage.leaveAppliedSuccessfullyIsDisplayed(), "Leave Applied Successfully");
    }
}
