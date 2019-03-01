package org.mosh.solutions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WazeHomePage extends BasePage {

    private static final String HOME_PAGE_URL = "https://www.waze.com";
    private static final String LIVE_MAP_TEXT = "Live map";

    public WazeHomePage(WebDriver driver) {
        super(driver);
        driver.navigate().to(HOME_PAGE_URL);
    }

    public LiveMapPage goToLiveMap() {
        super.click(By.linkText(LIVE_MAP_TEXT));
        return new LiveMapPage(driver);
    }

}
