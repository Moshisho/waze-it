package org.mosh.solutions.models;

import java.util.Comparator;
import java.util.Objects;

public class RouteResult implements Comparable<RouteResult> {

    private final int hour;
    private final float km;
    private final int durationInMinutes;

    public RouteResult(int hour, float km, int durationInMinutes) {
        this.hour = hour;
        this.km = km;
        this.durationInMinutes = durationInMinutes;
    }

    public int compareTo(RouteResult o) {
        return Comparator.comparingInt((RouteResult rr) -> rr.durationInMinutes)
                .thenComparingDouble((RouteResult rr) -> rr.km)
                .compare(this, o);
    }

    public int getHour() {
        return hour;
    }

    public float getKm() {
        return km;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteResult that = (RouteResult) o;
        return hour == that.hour &&
                Float.compare(that.km, km) == 0 &&
                durationInMinutes == that.durationInMinutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, km, durationInMinutes);
    }

    @Override
    public String toString() {
        return "RouteResult{" +
                "hour=" + hour +
                ", km=" + km +
                ", durationInMinutes=" + durationInMinutes +
                '}';
    }
}
