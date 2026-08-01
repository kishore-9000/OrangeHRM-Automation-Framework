package com.orangehrm.utilities;

import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class to read configuration key-value pairs from config.properties.
 */
public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Unable to find config.properties in classpath");
            } else {
                properties.load(input);
            }
        } catch (Exception e) {
            System.err.println("Exception while loading config.properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static String getUrl() {
        return getProperty("url", "https://opensource-demo.orangehrmlive.com/");
    }

    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public static String getUsername() {
        return getProperty("username", "Admin");
    }

    public static String getPassword() {
        return getProperty("password", "admin123");
    }

    public static int getTimeout() {
        String timeout = getProperty("timeout", "20");
        return Integer.parseInt(timeout);
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }
}
