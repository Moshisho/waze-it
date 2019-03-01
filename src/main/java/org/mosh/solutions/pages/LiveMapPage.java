package org.mosh.solutions.pages;

import org.mosh.solutions.enums.City;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class LiveMapPage extends BasePage {

    private final String addressDropDownXpath = "//li[*/text()='<city>']";
    private final By scheduleContainer = By.className("wm-route-schedule");
    private final By toAddressInput = By.cssSelector("input[placeholder='Where from?']");
    private final By searchAddressInput = By.cssSelector("input[placeholder='Search for an address']");

    LiveMapPage(WebDriver driver) {
        super(driver);
    }

    public void searchForAddress(String address, City city) {
        super.input(searchAddressInput, address);
        clickAddressListItem(city);
        super.waitForElementToAppear(By.xpath("//*[@class='wz-poi__details']"));
    }

    public WazeRoutesElement getDirections(String toAddress, City city) {
        super.click(By.id("gtm-poi-card-get-directions"));
        super.input(toAddressInput, toAddress);
        clickAddressListItem(city);
        return new WazeRoutesElement(driver);
    }

    public WazeRoutesElement leaveAt(int hour) {
        List<WebElement> selects = super.find(scheduleContainer).findElements(By.tagName("select"));
        Select at = new Select(selects.get(0));
        Select time = new Select(selects.get(1));
        if (!at.getFirstSelectedOption().getAttribute("value").equals("at"))
            at.selectByValue("at");

        time.selectByValue(String.format("%02d:00", hour));
        super.waitForElementToDisappear(By.className("s-loading__spinner"));
        return new WazeRoutesElement(driver);
    }

    private void clickAddressListItem(City city) {
        super.click(By.xpath(addressDropDownXpath.replace("<city>", city.getCityWithCountry())));
    }

}
