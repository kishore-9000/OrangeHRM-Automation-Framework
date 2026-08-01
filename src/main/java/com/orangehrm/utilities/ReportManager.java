package com.orangehrm.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

/**
 * Thread-safe ExtentReports management.
 */
public class ReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            String reportDir = System.getProperty("user.dir") + File.separator + "Reports";
            File dir = new File(reportDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String reportPath = reportDir + File.separator + "ExtentReport_" + JavaUtility.getFormattedTimestamp() + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("OrangeHRM Automation Report");
            sparkReporter.config().setReportName("OrangeHRM Regression Test Execution Results");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Application", "OrangeHRM Web Application");
            extent.setSystemInfo("Environment", "QA Automation");
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }

    public static synchronized ExtentTest createTest(String testName, String description) {
        ExtentTest test = getReporter().createTest(testName, description);
        extentTest.set(test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    public static synchronized void logInfo(String message) {
        if (getTest() != null) {
            getTest().log(Status.INFO, message);
        }
    }

    public static synchronized void logPass(String message) {
        if (getTest() != null) {
            getTest().log(Status.PASS, message);
        }
    }

    public static synchronized void logFail(String message) {
        if (getTest() != null) {
            getTest().log(Status.FAIL, message);
        }
    }

    public static synchronized void logSkip(String message) {
        if (getTest() != null) {
            getTest().log(Status.SKIP, message);
        }
    }

    public static synchronized void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
