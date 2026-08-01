package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ConfigReader;
import com.orangehrm.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Test Class for OrangeHRM Login Module functionality.
 */
public class LoginTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        logger.info("Executing testValidLogin");
        LoginPage loginPage = new LoginPage(getDriver());
        
        Assert.assertTrue(loginPage.isLogoDisplayed(), "OrangeHRM Logo should be displayed on login page");
        
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard page should be displayed after successful login");
    }

    @Test(priority = 2, groups = {"regression"}, description = "Verify error message on invalid login credentials", dataProvider = "invalidLoginData")
    public void testInvalidLogin(String username, String password) {
        logger.info("Executing testInvalidLogin with user: " + username);
        LoginPage loginPage = new LoginPage(getDriver());
        
        loginPage.login(username, password);
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.toLowerCase().contains("invalid"), "Error message should indicate invalid credentials. Found: " + errorMsg);
    }

    @Test(priority = 3, groups = {"regression"}, description = "Verify login attempt with blank username")
    public void testBlankUsername() {
        logger.info("Executing testBlankUsername");
        LoginPage loginPage = new LoginPage(getDriver());
        
        loginPage.login("", ConfigReader.getPassword());
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "User should remain on Login Page when username is blank");
    }

    @Test(priority = 4, groups = {"regression"}, description = "Verify login attempt with blank password")
    public void testBlankPassword() {
        logger.info("Executing testBlankPassword");
        LoginPage loginPage = new LoginPage(getDriver());
        
        loginPage.login(ConfigReader.getUsername(), "");
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "User should remain on Login Page when password is blank");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        String excelPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data" + File.separator + "EmployeeData.xlsx";
        File file = new File(excelPath);
        if (file.exists()) {
            ExcelUtility excel = new ExcelUtility(excelPath);
            Object[][] data = excel.getTestData("InvalidLogin");
            if (data.length > 0) return data;
        }
        // Fallback default test data
        return new Object[][]{
                {"InvalidUser", "admin123"},
                {"Admin", "WrongPassword123"}
        };
    }
}
