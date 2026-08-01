package com.orangehrm.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Utility for capturing screenshots on test events or failures.
 */
public class ScreenshotUtility {

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        if (driver == null) {
            return null;
        }
        String timestamp = JavaUtility.getFormattedTimestamp();
        String fileName = screenshotName + "_" + timestamp + ".png";
        String destinationPath = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + fileName;
        
        try {
            File dir = new File(System.getProperty("user.dir") + File.separator + "Screenshots");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(destinationPath);
            FileUtils.copyFile(srcFile, destFile);
            return destinationPath;
        } catch (IOException e) {
            System.err.println("Exception while taking screenshot: " + e.getMessage());
            return null;
        }
    }

    public static String captureScreenshotAsBase64(WebDriver driver) {
        if (driver == null) {
            return null;
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
