package com.orangehrm.pages;

import com.orangehrm.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Class representing the OrangeHRM Dashboard Page.
 */
public class DashboardPage {

    private WebDriver driver;

    // Locators
    private By dashboardHeader = By.cssSelector(".oxd-topbar-header-breadcrumb-module");
    private By userProfileDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private By sidePanel = By.cssSelector(".oxd-sidepanel-body");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDashboardDisplayed() {
        try {
            WebElement header = WaitUtility.waitForElementToBeVisible(driver, dashboardHeader, 15);
            return header.isDisplayed() && header.getText().equalsIgnoreCase("Dashboard");
        } catch (Exception e) {
            return false;
        }
    }

    public String getDashboardHeading() {
        try {
            return WaitUtility.waitForElementToBeVisible(driver, dashboardHeader, 10).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickMenu(String menuName) {
        By menuLocator = By.xpath("//span[text()='" + menuName + "']");
        WebElement menuElem = WaitUtility.waitForElementToBeClickable(driver, menuLocator, 10);
        menuElem.click();
    }

    public boolean isUserDropdownDisplayed() {
        try {
            return WaitUtility.waitForElementToBeVisible(driver, userProfileDropdown, 10).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
