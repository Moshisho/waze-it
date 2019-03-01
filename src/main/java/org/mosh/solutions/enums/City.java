package org.mosh.solutions.enums;

public enum City {

    TEL_AVIV("Tel Aviv-Yafo, Israel"),
    YEHUD("Yehud, Israel");

    private String cityWithCountry;

    City(String cityWithCountry) {
        this.cityWithCountry = cityWithCountry;
    }


    public String getCityWithCountry() {
        return cityWithCountry;
    }
}
