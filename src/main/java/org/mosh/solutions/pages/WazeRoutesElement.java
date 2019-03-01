package org.mosh.solutions.pages;

import org.mosh.solutions.models.RouteResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WazeRoutesElement extends BasePage {

    private final By routesList = By.className("wm-route-list");
    private final By routeDiv = By.className("wm-route-item__title");
    private final By routeDuration = By.className("wm-route-item__time");
    private final By routeDistance = By.className("wm-route-item__length");

    WazeRoutesElement(WebDriver driver) {
        super(driver);
        super.waitForElementToAppear(routesList);
    }

    public List<RouteResult> toRouteResults(int hourOfDay) {
        List<RouteResult> rrs = new ArrayList<>();

        for (WebElement e : super.find(routesList).findElements(routeDiv)) {
            // 3 h 34 min 6 sec 330.3 km 6 meters

            String duration = e.findElement(routeDuration).getText();
            String distance = e.findElement(routeDistance).getText();

            rrs.add(new RouteResult(
                    hourOfDay,
                    extractDistanceKM(distance),
                    extractDurationMinutes(duration)));
        }

        return rrs;

    }

    private int extractDurationMinutes(String duration) {
        int total = 0;
        Pattern minP = Pattern.compile("(\\d+) min");
        Pattern hourP = Pattern.compile("(\\d+) h");
        Matcher min = minP.matcher(duration);
        Matcher hour = hourP.matcher(duration);
        if (min.find())
            total += Integer.parseInt(min.group(1));
        if (hour.find())
            total += Integer.parseInt(hour.group(1)) * 60;
        return total;
    }

    private float extractDistanceKM(String distance) {
        float total = 0.0f;
        Pattern kmP = Pattern.compile("(\\d+.?\\d?) km");
        Pattern metersP = Pattern.compile("(\\d+) meters");
        Matcher km = kmP.matcher(distance);
        Matcher meters = metersP.matcher(distance);
        if (meters.find())
            total += Integer.parseInt(meters.group(1)) / 1000;
        if (km.find())
            total += Float.parseFloat(km.group(1));
        return total;
    }
}
