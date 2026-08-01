package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PIMPage;
import com.orangehrm.utilities.ConfigReader;
import com.orangehrm.utilities.JavaUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Class for PIM (Personal Information Management) Module.
 */
public class PIMTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify PIM module navigation")
    public void testPIMPageDisplay() {
        logger.info("Executing testPIMPageDisplay");
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        dashboardPage.clickMenu("PIM");

        PIMPage pimPage = new PIMPage(getDriver());
        Assert.assertTrue(pimPage.isPIMPageDisplayed(), "PIM page header should be displayed");
    }

    @Test(priority = 2, groups = {"regression"}, description = "Verify adding a new employee in PIM module")
    public void testAddEmployee() {
        logger.info("Executing testAddEmployee");
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        dashboardPage.clickMenu("PIM");

        PIMPage pimPage = new PIMPage(getDriver());
        String firstName = "Auto" + JavaUtility.getRandomString(4);
        String middleName = "Test";
        String lastName = "User" + JavaUtility.getRandomString(4);

        pimPage.addEmployee(firstName, middleName, lastName);
        logger.info("Added employee: " + firstName + " " + lastName);
        Assert.assertTrue(getDriver().getCurrentUrl().toLowerCase().contains("pim"), "User should remain in PIM section after adding employee");
    }
}
