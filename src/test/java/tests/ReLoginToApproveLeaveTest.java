package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.PropertyFileReaderUtil;

public class ReLoginToApproveLeaveTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Re-Login To Approve Leave test for UrBuddi")
    @Feature("Re-Login To Approve Leave Test Feature")
    public void reLoginToApproveLeave(){

        // Initialize the WebDriver
        WebDriver driver = BaseTest.map.get("charan");

        // Navigate to the login page
        LoginPage loginPage = new LoginPage(driver);
        loginPage.url(PropertyFileReaderUtil.getProperty("baseURL"));

        // Enter the stored email and password
        loginPage.enterEmailId(PropertyFileReaderUtil.getProperty("username"));
        loginPage.enterPassword(PropertyFileReaderUtil.getProperty("password"));

        // Click the login button
        loginPage.clickLoginButton();
    }


}
