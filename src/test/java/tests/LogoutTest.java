package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LogoutPage;

public class LogoutTest extends BaseTest{

    @Test
    public void logout(){
        WebDriver driver = BaseTest.map.get("charan");
        LogoutPage logoutPage = new LogoutPage(driver);

        logoutPage.clickLogoutSection();
        Assert.assertEquals(logoutPage.getLogoutTitle(),"Logout");
        logoutPage.clickLogoutButton();
        Assert.assertEquals(logoutPage.getWelcomeMessage(), "Welcome to urBuddi");

    }

}
