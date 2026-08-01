package com.orangehrm.pages;

import com.orangehrm.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Class for Logout workflow execution.
 */
public class LogoutPage {

    private WebDriver driver;

    // Locators
    private By userProfileDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private By logoutLink = By.xpath("//a[text()='Logout']");

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickUserProfile() {
        WebElement dropdown = WaitUtility.waitForElementToBeClickable(driver, userProfileDropdown, 10);
        dropdown.click();
    }

    public LoginPage clickLogout() {
        WebElement logout = WaitUtility.waitForElementToBeClickable(driver, logoutLink, 10);
        logout.click();
        return new LoginPage(driver);
    }

    public LoginPage logout() {
        clickUserProfile();
        return clickLogout();
    }
}
