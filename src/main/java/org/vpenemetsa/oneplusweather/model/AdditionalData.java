package org.vpenemetsa.oneplusweather.model;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class AdditionalData {

    private String country;

    private long sunrise;

    private long sunset;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
}
