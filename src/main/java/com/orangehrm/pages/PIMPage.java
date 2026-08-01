package com.orangehrm.pages;

import com.orangehrm.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Class for PIM (Personal Information Management) Module.
 */
public class PIMPage {

    private WebDriver driver;

    // Locators
    private By moduleHeader = By.cssSelector(".oxd-topbar-header-breadcrumb-module");
    private By addEmployeeTab = By.xpath("//a[text()='Add Employee']");
    private By addButton = By.xpath("//button[normalize-space()='Add']");
    private By firstNameInput = By.name("firstName");
    private By middleNameInput = By.name("middleName");
    private By lastNameInput = By.name("lastName");
    private By saveButton = By.cssSelector("button[type='submit']");
    private By employeeNameSearchInput = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By recordsText = By.xpath("//span[contains(@class,'oxd-text--span') and (contains(.,'Found') or contains(.,'Records'))]");

    public PIMPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPIMPageDisplayed() {
        try {
            WebElement header = WaitUtility.waitForElementToBeVisible(driver, moduleHeader, 10);
            return header.isDisplayed() && header.getText().equalsIgnoreCase("PIM");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddEmployee() {
        try {
            WebElement tab = WaitUtility.waitForElementToBeClickable(driver, addEmployeeTab, 5);
            tab.click();
        } catch (Exception e) {
            WebElement btn = WaitUtility.waitForElementToBeClickable(driver, addButton, 5);
            btn.click();
        }
    }

    public void enterFirstName(String firstName) {
        WebElement input = WaitUtility.waitForElementToBeVisible(driver, firstNameInput, 10);
        input.clear();
        input.sendKeys(firstName);
    }

    public void enterMiddleName(String middleName) {
        WebElement input = WaitUtility.waitForElementToBeVisible(driver, middleNameInput, 10);
        input.clear();
        input.sendKeys(middleName);
    }

    public void enterLastName(String lastName) {
        WebElement input = WaitUtility.waitForElementToBeVisible(driver, lastNameInput, 10);
        input.clear();
        input.sendKeys(lastName);
    }

    public void clickSave() {
        WebElement btn = WaitUtility.waitForElementToBeClickable(driver, saveButton, 10);
        btn.click();
    }

    public void addEmployee(String firstName, String middleName, String lastName) {
        clickAddEmployee();
        enterFirstName(firstName);
        enterMiddleName(middleName);
        enterLastName(lastName);
        clickSave();
    }

    public void searchEmployee(String employeeName) {
        WebElement input = WaitUtility.waitForElementToBeVisible(driver, employeeNameSearchInput, 10);
        input.clear();
        input.sendKeys(employeeName);
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
