package org.mosh.solutions;

import org.mosh.solutions.factories.WebDriverFactory;
import org.mosh.solutions.pages.WazeHomePage;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class Main {

    private static WebDriver browser;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            WazeHomePage wazeHomePage = openWazeHome(args[0]);
            wazeHomePage.goToLiveMap();

            LOGGER.info(browser.getTitle());


        } catch (Exception global) {
            LOGGER.severe(global.getMessage());
        } finally {
            browser.close();
        }
    }

    private static WazeHomePage openWazeHome(String browserName) {
        LOGGER.info("Started...");
        browser = WebDriverFactory.get(browserName);
        return new WazeHomePage(browser);
    }


}
