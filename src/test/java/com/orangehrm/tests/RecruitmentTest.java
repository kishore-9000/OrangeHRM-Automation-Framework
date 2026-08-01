package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.RecruitmentPage;
import com.orangehrm.utilities.ConfigReader;
import com.orangehrm.utilities.JavaUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Class for Recruitment Candidates Module.
 */
public class RecruitmentTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify Recruitment module navigation")
    public void testRecruitmentPageDisplay() {
        logger.info("Executing testRecruitmentPageDisplay");
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        dashboardPage.clickMenu("Recruitment");

        RecruitmentPage recruitmentPage = new RecruitmentPage(getDriver());
        Assert.assertTrue(recruitmentPage.isRecruitmentPageDisplayed(), "Recruitment page header should be displayed");
    }

    @Test(priority = 2, groups = {"regression"}, description = "Verify candidate search in Recruitment module")
    public void testSearchCandidate() {
        logger.info("Executing testSearchCandidate");
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        dashboardPage.clickMenu("Recruitment");

        RecruitmentPage recruitmentPage = new RecruitmentPage(getDriver());
        recruitmentPage.clickSearch();
        String recordsText = recruitmentPage.getRecordsCountText();
        logger.info("Recruitment search result: " + recordsText);
        Assert.assertTrue(recordsText.length() > 0, "Candidate records count text should be visible");
    }
}
