package com.orangehrm.listeners;

import com.aventstack.extentreports.Status;
import com.orangehrm.base.DriverFactory;
import com.orangehrm.utilities.ReportManager;
import com.orangehrm.utilities.ScreenshotUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG Listener for logging and report generation on test status changes.
 */
public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        logger.info("================ START TEST: " + methodName + " ================");
        ReportManager.createTest(methodName, description != null ? description : methodName);
        ReportManager.logInfo("Test Execution Started: " + methodName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.info("================ TEST PASSED: " + methodName + " ================");
        ReportManager.logPass("Test Passed Successfully: " + methodName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.error("================ TEST FAILED: " + methodName + " ================", result.getThrowable());
        
        ReportManager.logFail("Test Failed: " + methodName);
        if (result.getThrowable() != null) {
            ReportManager.getTest().log(Status.FAIL, result.getThrowable());
        }

        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            try {
                String base64Screenshot = ScreenshotUtility.captureScreenshotAsBase64(driver);
                if (base64Screenshot != null) {
                    ReportManager.getTest().addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot");
                }
                String filePath = ScreenshotUtility.captureScreenshot(driver, methodName);
                if (filePath != null) {
                    logger.info("Failure screenshot saved to: " + filePath);
                }
            } catch (Exception e) {
                logger.error("Exception while attaching screenshot to report: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.warn("================ TEST SKIPPED: " + methodName + " ================");
        ReportManager.logSkip("Test Skipped: " + methodName);
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Starting Test Suite Execution: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Finishing Test Suite Execution: " + context.getName());
        ReportManager.flushReport();
    }
}
