package com.orangehrm.pages;

import com.orangehrm.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Class for Recruitment Candidates Module.
 */
public class RecruitmentPage {

    private WebDriver driver;

    // Locators
    private By moduleHeader = By.cssSelector(".oxd-topbar-header-breadcrumb-module");
    private By addButton = By.xpath("//button[normalize-space()='Add']");
    private By firstNameInput = By.name("firstName");
    private By middleNameInput = By.name("middleName");
    private By lastNameInput = By.name("lastName");
    private By emailInput = By.xpath("//label[text()='Email']/parent::div/following-sibling::div/input");
    private By saveButton = By.cssSelector("button[type='submit']");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By recordsText = By.xpath("//span[contains(@class,'oxd-text--span') and (contains(.,'Found') or contains(.,'Records'))]");

    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isRecruitmentPageDisplayed() {
        try {
            WebElement header = WaitUtility.waitForElementToBeVisible(driver, moduleHeader, 10);
            return header.isDisplayed() && header.getText().equalsIgnoreCase("Recruitment");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddCandidate() {
        WebElement btn = WaitUtility.waitForElementToBeClickable(driver, addButton, 10);
        btn.click();
    }

    public void enterCandidateDetails(String firstName, String middleName, String lastName, String email) {
        WaitUtility.waitForElementToBeVisible(driver, firstNameInput, 10).sendKeys(firstName);
        WaitUtility.waitForElementToBeVisible(driver, middleNameInput, 10).sendKeys(middleName);
        WaitUtility.waitForElementToBeVisible(driver, lastNameInput, 10).sendKeys(lastName);
        WaitUtility.waitForElementToBeVisible(driver, emailInput, 10).sendKeys(email);
    }

    public void clickSave() {
        WebElement btn = WaitUtility.waitForElementToBeClickable(driver, saveButton, 10);
        btn.click();
    }

    public void addCandidate(String firstName, String middleName, String lastName, String email) {
        clickAddCandidate();
        enterCandidateDetails(firstName, middleName, lastName, email);
        clickSave();
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
