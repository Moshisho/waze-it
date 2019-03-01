package org.mosh.solutions.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

    public static WebDriver get(String browserName) {

        if ("Chrome".equalsIgnoreCase(browserName)) {
            return new ChromeDriver();
        } else {
            throw new IllegalArgumentException("Browser name provided not supported, use 'chrome'");
        }
    }

}
