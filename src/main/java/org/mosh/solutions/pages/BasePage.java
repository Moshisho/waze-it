package org.mosh.solutions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

abstract class BasePage {

    private static final int TIMEOUT = 10;
    private static final int POLLING = 100;

    WebDriver driver;
    private WebDriverWait wait;

    private static final Logger LOGGER = Logger.getLogger(BasePage.class.getName());

    BasePage(WebDriver driver) {
        LOGGER.info("init page " + this.getClass().getSimpleName());
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
    }

    void click(By locator) {
        LOGGER.info("clicking " + locator.toString());
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
        LOGGER.info("clicked");
    }

    void input(By locator, String value) {
        LOGGER.info("writing to input " + locator.toString());
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(value);
        LOGGER.info("succeeded writing");
    }

    WebElement find(By locator) {
        LOGGER.info("searching element " + locator.toString());
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

}

