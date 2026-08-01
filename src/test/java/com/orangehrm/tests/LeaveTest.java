package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LeavePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Class for Leave Management Module.
 */
public class LeaveTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify Leave module navigation")
    public void testLeavePageDisplay() {
        logger.info("Executing testLeavePageDisplay");
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        dashboardPage.clickMenu("Leave");

        LeavePage leavePage = new LeavePage(getDriver());
        Assert.assertTrue(leavePage.isLeavePageDisplayed(), "Leave page header should be displayed");
    }

    @Test(priority = 2, groups = {"regression"}, description = "Verify searching leave records")
    public void testSearchLeave() {
        logger.info("Executing testSearchLeave");
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        dashboardPage.clickMenu("Leave");

        LeavePage leavePage = new LeavePage(getDriver());
        leavePage.clickSearch();
        String recordsText = leavePage.getRecordsCountText();
        logger.info("Leave search result: " + recordsText);
        Assert.assertTrue(recordsText.length() > 0, "Leave records count text should be visible");
    }
}
