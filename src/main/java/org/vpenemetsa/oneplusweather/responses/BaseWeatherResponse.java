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
public class BaseWeatherResponse {

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


}
