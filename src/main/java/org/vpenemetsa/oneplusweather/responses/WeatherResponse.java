package org.vpenemetsa.oneplusweather.responses;

import com.google.gson.annotations.SerializedName;

import org.vpenemetsa.oneplusweather.model.AdditionalData;
import org.vpenemetsa.oneplusweather.model.Clouds;
import org.vpenemetsa.oneplusweather.model.MainData;
import org.vpenemetsa.oneplusweather.model.Rain;
import org.vpenemetsa.oneplusweather.model.Snow;
import org.vpenemetsa.oneplusweather.model.Weather;
import org.vpenemetsa.oneplusweather.model.Wind;

import java.util.List;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class WeatherResponse {

    private List<Weather> weather;

    @SerializedName("sys")
    private AdditionalData additionalData;

    private MainData main;

    private Wind wind;

    private Rain rain;

    private Snow snow;

    private Clouds clouds;

    @SerializedName("dt")
    private String date;

    private String name;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }

    public MainData getMain() {
        return main;
    }

    public void setMain(MainData main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
