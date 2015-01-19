package org.vpenemetsa.oneplusweather.api;

import org.vpenemetsa.oneplusweather.Constants;
import org.vpenemetsa.oneplusweather.responses.BaseWeatherResponse;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.QueryMap;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public interface OpenWeatherService {

    @Headers({
            "x-api-key:" + Constants.OPEN_WEATHER_API_KEY
    })
    @GET("/data/2.5/weather")
    void getCurrentWeather(@QueryMap Map<String, String> headers, Callback<BaseWeatherResponse> callback);


}
