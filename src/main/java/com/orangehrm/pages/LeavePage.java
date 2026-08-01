package com.orangehrm.pages;

import com.orangehrm.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Class for Leave Management Module.
 */
public class LeavePage {

    private WebDriver driver;

    // Locators
    private By moduleHeader = By.cssSelector(".oxd-topbar-header-breadcrumb-module");
    private By applyTab = By.xpath("//a[text()='Apply']");
    private By leaveListTab = By.xpath("//a[text()='Leave List']");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By recordsText = By.xpath("//span[contains(@class,'oxd-text--span') and (contains(.,'Found') or contains(.,'Records'))]");

    public LeavePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLeavePageDisplayed() {
        try {
            WebElement header = WaitUtility.waitForElementToBeVisible(driver, moduleHeader, 10);
            return header.isDisplayed() && header.getText().equalsIgnoreCase("Leave");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickApplyTab() {
        WebElement tab = WaitUtility.waitForElementToBeClickable(driver, applyTab, 10);
        tab.click();
    }

    public void clickLeaveListTab() {
        WebElement tab = WaitUtility.waitForElementToBeClickable(driver, leaveListTab, 10);
        tab.click();
    }

    public void clickSearch() {
        WebElement btn = WaitUtility.waitForElementToBeClickable(driver, searchButton, 10);
        btn.click();
    }

    public String getRecordsCountText() {
        try {
            return WaitUtility.waitForElementToBeVisible(driver, recordsText, 10).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
