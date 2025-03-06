package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.PropertyFileReaderUtil;

public class LoginTest extends BaseTest {


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login test for UrBuddi")
    @Feature("Login Feature")
    public void loginToUrBuddy() {
        WebDriver driver = BaseTest.map.get("charan");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.url(PropertyFileReaderUtil.getProperty("baseURL"));
        loginPage.enterEmailId(PropertyFileReaderUtil.getProperty("username"));
        loginPage.enterPassword(PropertyFileReaderUtil.getProperty("password"));
        loginPage.clickLoginButton();
        loginPage.handleAlert(driver);

       Assert.assertEquals(loginPage.getDashboardTitle(), "Dashboard");
    }
}
