package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Class for OrangeHRM Dashboard Module functionality.
 */
public class DashboardTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify Dashboard display and header text")
    public void testVerifyDashboard() {
        logger.info("Executing testVerifyDashboard");
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard page should be visible");
        Assert.assertEquals(dashboardPage.getDashboardHeading(), "Dashboard", "Heading text should be 'Dashboard'");
        Assert.assertTrue(dashboardPage.isUserDropdownDisplayed(), "User profile dropdown should be displayed on header");
    }

    @Test(priority = 2, groups = {"regression"}, description = "Verify sidebar menu navigation to Admin module")
    public void testSidebarNavigation() {
        logger.info("Executing testSidebarNavigation");
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        
        dashboardPage.clickMenu("Admin");
        Assert.assertTrue(getDriver().getCurrentUrl().toLowerCase().contains("admin"), "URL should contain 'admin'");
    }
}
