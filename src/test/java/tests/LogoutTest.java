package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LogoutPage;

public class LogoutTest extends BaseTest{

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Logout test for UrBuddi")
    @Feature("Logout Feature")
    public void logout(){
        WebDriver driver = BaseTest.map.get("charan");
        LogoutPage logoutPage = new LogoutPage(driver);

        logoutPage.clickLogoutSection();
        Assert.assertEquals(logoutPage.getLogoutTitle(),"Logout");
        logoutPage.clickLogoutButton();
        Assert.assertEquals(logoutPage.getWelcomeMessage(), "Welcome to urBuddi");

    }

}
