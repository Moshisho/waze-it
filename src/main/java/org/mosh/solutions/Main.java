package org.mosh.solutions;

import org.mosh.solutions.enums.City;
import org.mosh.solutions.factories.WebDriverFactory;
import org.mosh.solutions.models.RouteResult;
import org.mosh.solutions.pages.LiveMapPage;
import org.mosh.solutions.pages.WazeHomePage;
import org.mosh.solutions.pages.WazeRoutesElement;
import org.mosh.solutions.utils.GoogleMail;
import org.openqa.selenium.WebDriver;

import java.util.Base64;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {

    private static String emailTo;
    private static WebDriver browser;
    private static TreeSet<RouteResult> results = new TreeSet();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException("required arguments - browser and email");
            }
            emailTo = args[1];

            WazeHomePage wazeHome = openWazeHome(args[0]);
            LiveMapPage liveMap = wazeHome.goToLiveMap();
            liveMap.searchForAddress("Yigal Alon Street 94", City.TEL_AVIV);
            liveMap.getDirections("lohamei hagetaot 7", City.YEHUD);

            fillWazeRoutesResultsForRoundHours(liveMap);

            //4:
            TreeSet<RouteResult> morning = results.stream()
                    .filter(r -> r.getHour() >= 7)
                    .filter(r -> r.getHour() <= 9)
                    .collect(Collectors.toCollection(TreeSet::new));

            LOGGER.info(morning.first().toString());

            //for 5d I would make it multithreaded but don't have enough time...
            LOGGER.warning(results.first().toString());
            LOGGER.warning(results.last().toString());

            //6:
            StringBuilder sb = new StringBuilder();
            for (RouteResult rr : results) {
                sb.append(rr.toString() + "\n");
            }
            GoogleMail.Send("miron.jengo",
                    new String(Base64.getDecoder().decode("cG8wOSFAUVc=".getBytes())),
                    emailTo,
                    "Waze Results", sb.toString());
            LOGGER.info("************* SUCCESS - Finished *************");
        } catch (Exception global) {
            LOGGER.severe(global.getMessage());
            LOGGER.severe(String.valueOf(global.getStackTrace()));
        } finally {
            browser.close();
        }
    }

    private static void fillWazeRoutesResultsForRoundHours(LiveMapPage liveMap) {
        for (int i = 0; i <= 23; i++) {
            WazeRoutesElement wazeRoutes = liveMap.leaveAt(i);
            List<RouteResult> routeResults = wazeRoutes.toRouteResults(i);
            results.addAll(routeResults);
        }
    }

    private static WazeHomePage openWazeHome(String browserName) {
        LOGGER.info("Started...");
        browser = WebDriverFactory.get(browserName);
        return new WazeHomePage(browser);
    }


}
