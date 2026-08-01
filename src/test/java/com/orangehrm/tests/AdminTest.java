package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.AdminPage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Class for OrangeHRM Admin User Management Module.
 */
public class AdminTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify Admin module navigation and display")
    public void testAdminPageDisplay() {
        logger.info("Executing testAdminPageDisplay");
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        dashboardPage.clickMenu("Admin");

        AdminPage adminPage = new AdminPage(getDriver());
        Assert.assertTrue(adminPage.isAdminPageDisplayed(), "Admin page header should be displayed");
    }

    @Test(priority = 2, groups = {"regression"}, description = "Verify searching user by username in Admin module")
    public void testSearchUser() {
        logger.info("Executing testSearchUser");
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        dashboardPage.clickMenu("Admin");

        AdminPage adminPage = new AdminPage(getDriver());
        adminPage.searchUser("Admin");
        
        String recordsText = adminPage.getRecordsCountText();
        logger.info("Search Result: " + recordsText);
        Assert.assertTrue(recordsText.length() > 0, "Records found banner should be displayed after search");
    }
}
