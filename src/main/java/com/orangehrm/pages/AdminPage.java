package com.orangehrm.pages;

import com.orangehrm.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Class for the Admin User Management module.
 */
public class AdminPage {

    private WebDriver driver;

    // Locators
    private By moduleHeader = By.cssSelector(".oxd-topbar-header-breadcrumb-module");
    private By usernameInput = By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By resetButton = By.xpath("//button[normalize-space()='Reset']");
    private By addButton = By.xpath("//button[normalize-space()='Add']");
    private By recordsCountText = By.xpath("//span[contains(@class,'oxd-text--span') and (contains(.,'Found') or contains(.,'Records'))]");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAdminPageDisplayed() {
        try {
            WebElement header = WaitUtility.waitForElementToBeVisible(driver, moduleHeader, 10);
            return header.isDisplayed() && header.getText().equalsIgnoreCase("Admin");
        } catch (Exception e) {
            return false;
        }
    }

    public void enterUsername(String username) {
        WebElement input = WaitUtility.waitForElementToBeVisible(driver, usernameInput, 10);
        input.clear();
        input.sendKeys(username);
    }

    public void clickSearch() {
        WebElement btn = WaitUtility.waitForElementToBeClickable(driver, searchButton, 10);
        btn.click();
    }

    public void clickReset() {
        WebElement btn = WaitUtility.waitForElementToBeClickable(driver, resetButton, 10);
        btn.click();
    }

    public void clickAddUser() {
        WebElement btn = WaitUtility.waitForElementToBeClickable(driver, addButton, 10);
        btn.click();
    }

    public String getRecordsCountText() {
        try {
            return WaitUtility.waitForElementToBeVisible(driver, recordsCountText, 10).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void searchUser(String username) {
        enterUsername(username);
        clickSearch();
    }
}
