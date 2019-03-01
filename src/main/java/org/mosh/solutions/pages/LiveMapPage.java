package org.mosh.solutions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LiveMapPage extends BasePage {

    private final By searchAddress = By.cssSelector("input[placeholder='Search for an address']");

    LiveMapPage(WebDriver driver) {
        super(driver);
    }

    //set yigal alon 94 in search address (placeholder="Search for an address")
    public void searchForAddress(String address) {
        //yigal alon street 94 tel aviv israel
        super.input(searchAddress, address);
        //li[*/text()="Yigal Alon Street 94" and */text()='Tel Aviv, Israel']
//        super.click();
    }
    //click li with text inside (Tel Aviv-Yafo, Israel) id="awesomplete_list_1"
    //click on id="gtm-poi-card-get-directions"
    //placeholder="Where from?" lohamei hagetaot 7
    //click on li with (Yehud, Israel) like before
    // select option value="at"
    // div[class="wm-routing__list"]
    // 3 h 34 min 330.3 km  regex with group matches or class="wm-route-item__time" and class="wm-route-item__length"

}
