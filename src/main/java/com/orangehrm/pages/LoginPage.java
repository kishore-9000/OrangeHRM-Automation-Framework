package com.orangehrm.pages;

import com.orangehrm.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Class representing the OrangeHRM Login Page.
 */
public class LoginPage {

    private WebDriver driver;

    // Locators
    private By usernameInput = By.name("username");
    private By passwordInput = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By errorMessageBanner = By.cssSelector(".oxd-alert-content-text");
    private By companyLogo = By.cssSelector(".orangehrm-login-branding img");
    private By loginHeader = By.cssSelector(".orangehrm-login-title");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoginPageDisplayed() {
        try {
            return WaitUtility.waitForElementToBeVisible(driver, loginButton, 10).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoDisplayed() {
        try {
            return WaitUtility.waitForElementToBeVisible(driver, companyLogo, 10).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterUsername(String username) {
        WebElement userElem = WaitUtility.waitForElementToBeVisible(driver, usernameInput, 10);
        userElem.clear();
        userElem.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passElem = WaitUtility.waitForElementToBeVisible(driver, passwordInput, 10);
        passElem.clear();
        passElem.sendKeys(password);
    }

    public void clickLogin() {
        WebElement btn = WaitUtility.waitForElementToBeClickable(driver, loginButton, 10);
        btn.click();
    }

    public DashboardPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new DashboardPage(driver);
    }

    public String getErrorMessage() {
        try {
            WebElement errorMsg = WaitUtility.waitForElementToBeVisible(driver, errorMessageBanner, 10);
            return errorMsg.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getLoginTitle() {
        try {
            return WaitUtility.waitForElementToBeVisible(driver, loginHeader, 5).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
