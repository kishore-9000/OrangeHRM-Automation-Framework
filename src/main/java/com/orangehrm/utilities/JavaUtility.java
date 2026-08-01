package com.orangehrm.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Common Java utilities for data generation and date formatting.
 */
public class JavaUtility {

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }

    public static int getRandomNumber(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }

    public static String getRandomEmail() {
        return "user_" + System.currentTimeMillis() + "@testmail.com";
    }

    public static String getFormattedTimestamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }

    public static String getFormattedTimestamp(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }
}
