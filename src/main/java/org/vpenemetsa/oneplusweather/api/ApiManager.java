package org.vpenemetsa.oneplusweather.api;

import android.util.Log;

import com.google.gson.Gson;

import org.vpenemetsa.oneplusweather.Constants;
import org.vpenemetsa.oneplusweather.responses.ListWeatherResponse;
import org.vpenemetsa.oneplusweather.responses.WeatherResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class ApiManager {

    public void getCurrentWeather(String id, Callback<WeatherResponse> callback) {

        Map<String, String> params = new HashMap<>();
        params.put("q", id);

        OpenWeatherService.CurrentWeather weatherService =
                getAdapter().create(OpenWeatherService.CurrentWeather.class);
        weatherService.getCurrentWeather(params, callback);
    }

    public void getCurrentWeather(String lat, String lon, Callback<WeatherResponse> callback) {
        Map<String, String> params = new HashMap<>();
        params.put("lat", lat);
        params.put("lon", lon);

        OpenWeatherService.CurrentWeather weatherService =
                getAdapter().create(OpenWeatherService.CurrentWeather.class);
        weatherService.getCurrentWeather(params, callback);
    }

    public void getCurrentWeather(List<String> ids, Callback<ListWeatherResponse> callback) {
        StringBuilder builder = new StringBuilder();
        for (String id : ids) {
            builder.append(ids + ",");
        }
        builder.delete(builder.length() - 2, builder.length());

        Map<String, String> params = new HashMap<>();
        params.put("id", builder.toString());

        OpenWeatherService.WeatherForGroup weatherService =
                getAdapter().create(OpenWeatherService.WeatherForGroup.class);
        weatherService.getWeatherForGroup(params, callback);
    }

    private RestAdapter getAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(Constants.OPEN_WEATHER_API_ENDPOINT)
                .setConverter(new GsonConverter(new Gson()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }
}
