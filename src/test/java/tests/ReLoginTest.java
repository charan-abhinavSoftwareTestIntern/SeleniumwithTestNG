package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataStorageUtils;
import utils.PropertyFileReaderUtil;

public class ReLoginTest extends BaseTest {

    @Test
    public void reLoginAsNewEmployee() {
        // Retrieve the stored email and password
        String email = DataStorageUtils.getFakeEmail();
        String password = DataStorageUtils.getFakePassword();

        // Initialize the WebDriver
        WebDriver driver = BaseTest.map.get("charan");

        // Navigate to the login page
        LoginPage loginPage = new LoginPage(driver);
        loginPage.url(PropertyFileReaderUtil.getProperty("baseURL"));

        // Enter the stored email and password
        loginPage.enterEmailId(email);
        loginPage.enterPassword(password);

        // Click the login button
        loginPage.clickLoginButton();

        // Assert that the login was successful by checking the dashboard title
        Assert.assertEquals(loginPage.getDashboardTitle(), "Dashboard", "Login failed with the generated credentials.");
    }

}
