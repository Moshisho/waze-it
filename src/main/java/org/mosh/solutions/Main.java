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
            LOGGER.info("Started...");
            browser = WebDriverFactory.get(args[0]);

            LOGGER.info(browser.getTitle());



        } catch (Exception global) {
            LOGGER.severe(global.getMessage());
        } finally {
            browser.close();
        }
    }


}
