package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.PropertyFileReaderUtil;

public class LoginTest extends BaseTest {


    @Test
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
