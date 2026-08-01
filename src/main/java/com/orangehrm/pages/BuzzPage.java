package com.orangehrm.pages;

import com.orangehrm.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Class for Buzz Newsfeed Module.
 */
public class BuzzPage {

    private WebDriver driver;

    // Locators
    private By moduleHeader = By.cssSelector(".oxd-topbar-header-breadcrumb-module");
    private By postTextArea = By.cssSelector(".orangehrm-buzz-post-input, textarea.oxd-buzz-post-input, textarea");
    private By postButton = By.cssSelector("button[type='submit']");
    private By feedPostBody = By.cssSelector(".orangehrm-buzz-post-body");

    public BuzzPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isBuzzPageDisplayed() {
        try {
            WebElement header = WaitUtility.waitForElementToBeVisible(driver, moduleHeader, 10);
            return header.isDisplayed() && header.getText().equalsIgnoreCase("Buzz");
        } catch (Exception e) {
            return false;
        }
    }

    public void enterPostContent(String postText) {
        WebElement input = WaitUtility.waitForElementToBeVisible(driver, postTextArea, 10);
        input.clear();
        input.sendKeys(postText);
    }

    public void clickPost() {
        WebElement btn = WaitUtility.waitForElementToBeClickable(driver, postButton, 10);
        btn.click();
    }

    public void createPost(String postText) {
        enterPostContent(postText);
        clickPost();
    }

    public boolean isFeedDisplayed() {
        try {
            return WaitUtility.waitForElementToBeVisible(driver, feedPostBody, 10).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
