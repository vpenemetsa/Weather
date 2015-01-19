package org.vpenemetsa.oneplusweather.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.vpenemetsa.oneplusweather.Constants;
import org.vpenemetsa.oneplusweather.responses.BaseWeatherResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class ApiManager {

    public void getCurrentWeather(String id, Callback<BaseWeatherResponse> callback) {

        Map<String, String> params = new HashMap<>();
        params.put("q", id);

        getWeatherService().getCurrentWeather(params, callback);
    }

    public void getCurrentWeather(String lat, String lon, Callback<BaseWeatherResponse> callback) {
        Map<String, String> params = new HashMap<>();
        params.put("lat", lat);
        params.put("lon", lon);

        getWeatherService().getCurrentWeather(params, callback);
    }

    private OpenWeatherService getWeatherService() {
        return getAdapter().create(OpenWeatherService.class);
    }

    private RestAdapter getAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(Constants.OPEN_WEATHER_API_ENDPOINT)
                .setConverter(new GsonConverter(new Gson()))
                .build();
    }
}
