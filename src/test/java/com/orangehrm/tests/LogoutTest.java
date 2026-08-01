package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.LogoutPage;
import com.orangehrm.utilities.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Class for Logout functionality.
 */
public class LogoutTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify successful logout redirects to login page")
    public void testLogout() {
        logger.info("Executing testLogout");
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Should be on Dashboard before logout");

        LogoutPage logoutPage = new LogoutPage(getDriver());
        LoginPage loginPageAfterLogout = logoutPage.logout();

        Assert.assertTrue(loginPageAfterLogout.isLoginPageDisplayed(), "Login page should be displayed after logout");
        Assert.assertTrue(loginPageAfterLogout.isLogoDisplayed(), "OrangeHRM logo should appear on the login page after logout");
    }
}
