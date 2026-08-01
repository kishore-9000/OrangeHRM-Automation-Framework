package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.BuzzPage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ConfigReader;
import com.orangehrm.utilities.JavaUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Class for Buzz Social Feed Module.
 */
public class BuzzTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify Buzz module navigation")
    public void testBuzzPageDisplay() {
        logger.info("Executing testBuzzPageDisplay");
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        dashboardPage.clickMenu("Buzz");

        BuzzPage buzzPage = new BuzzPage(getDriver());
        Assert.assertTrue(buzzPage.isBuzzPageDisplayed(), "Buzz page header should be displayed");
    }

    @Test(priority = 2, groups = {"regression"}, description = "Verify posting a status message on Buzz feed")
    public void testCreatePost() {
        logger.info("Executing testCreatePost");
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        dashboardPage.clickMenu("Buzz");

        BuzzPage buzzPage = new BuzzPage(getDriver());
        String postMessage = "Automation Test Post " + JavaUtility.getFormattedTimestamp();
        buzzPage.createPost(postMessage);
        
        logger.info("Created post: " + postMessage);
        Assert.assertTrue(buzzPage.isFeedDisplayed(), "News feed should remain visible after posting");
    }
}
