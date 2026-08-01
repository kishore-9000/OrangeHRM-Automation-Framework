package com.orangehrm.base;

import com.orangehrm.utilities.ConfigReader;
import com.orangehrm.utilities.ReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

/**
 * Abstract Base Test class defining test execution lifecycle and setup/teardown hooks.
 */
public abstract class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        logger.info("Initializing Test Execution Suite & Extent Reports");
        ReportManager.getReporter();
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("") String browser) {
        String selectedBrowser = (browser != null && !browser.isEmpty()) ? browser : ConfigReader.getBrowser();
        logger.info("Starting test execution on browser: " + selectedBrowser);
        
        WebDriver driver = DriverFactory.initDriver(selectedBrowser);
        String appUrl = ConfigReader.getUrl();
        logger.info("Navigating to application URL: " + appUrl);
        driver.get(appUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        logger.info("Closing browser session...");
        DriverFactory.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        logger.info("Flushing Extent Reports & Cleaning up suite execution");
        ReportManager.flushReport();
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
