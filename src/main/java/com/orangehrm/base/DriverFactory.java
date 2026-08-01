package com.orangehrm.base;

import com.orangehrm.utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * Factory class for managing thread-safe WebDriver instances.
 */
public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver initDriver(String browser) {
        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.getBrowser();
        }

        logger.info("Initializing WebDriver for browser: " + browser);
        boolean isHeadless = ConfigReader.isHeadless();

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--remote-allow-origins=*");
                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }
                driverThreadLocal.set(new ChromeDriver(chromeOptions));
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("-headless");
                }
                driverThreadLocal.set(new FirefoxDriver(firefoxOptions));
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                if (isHeadless) {
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--window-size=1920,1080");
                }
                driverThreadLocal.set(new EdgeDriver(edgeOptions));
                break;

            default:
                logger.error("Unsupported browser type: " + browser + ". Defaulting to Chrome.");
                WebDriverManager.chromedriver().setup();
                driverThreadLocal.set(new ChromeDriver());
                break;
        }

        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getTimeout()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        logger.info("WebDriver initialized successfully for thread: " + Thread.currentThread().getId());
        return driver;
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            logger.info("Quitting WebDriver for thread: " + Thread.currentThread().getId());
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
