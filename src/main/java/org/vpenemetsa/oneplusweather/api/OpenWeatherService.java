package org.vpenemetsa.oneplusweather.api;

import org.vpenemetsa.oneplusweather.Constants;
import org.vpenemetsa.oneplusweather.responses.ListWeatherResponse;
import org.vpenemetsa.oneplusweather.responses.WeatherResponse;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.QueryMap;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class OpenWeatherService {

    public interface CurrentWeather {
        @Headers({
                "x-api-key:" + Constants.OPEN_WEATHER_API_KEY
        })
        @GET("/data/2.5/weather")
        void getCurrentWeather(@QueryMap Map<String, String> headers, Callback<WeatherResponse> callback);
    }

    public interface WeatherForGroup {
        @GET("/data/2.5/group")
        void getWeatherForGroup(@QueryMap Map<String, String> headers, Callback<ListWeatherResponse> callback);
    }
}
