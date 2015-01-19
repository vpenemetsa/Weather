package org.vpenemetsa.oneplusweather.model;

import org.vpenemetsa.oneplusweather.responses.WeatherResponse;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class Location {

    private String name;

    private String country;

    private long temperature;

    private WeatherResponse response;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getTemperature() {
        return temperature;
    }

    public void setTemperature(long temperature) {
        this.temperature = temperature;
    }

    public WeatherResponse getResponse() {
        return response;
    }

    public void setResponse(WeatherResponse response) {
        this.response = response;
    }
}
