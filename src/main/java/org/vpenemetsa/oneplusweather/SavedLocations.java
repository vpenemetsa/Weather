package org.vpenemetsa.oneplusweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import org.vpenemetsa.oneplusweather.responses.WeatherResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class SavedLocations {

    private static SavedLocations mSavedLocations;

    private static SharedPreferences mSharedPrefs;
    private Gson mGson;

    private static final String WEATHER_RESPONSES_JSON = "WEATHER_RESPONSES_JSON";
    private static final String CURRENT_WEATHER_RESPONSE_JSON = "CURRENT_WEATHER_RESPONSE_JSON";

    private List<WeatherResponse> mWeatherResponses = new ArrayList<>();

    private WeatherResponse mCurrentLocationWeather;

    public static SavedLocations getInstance(Context context) {
        if (mSavedLocations == null) {
            mSavedLocations = new SavedLocations(context);
        }

        return mSavedLocations;
    }

    public SavedLocations(Context context) {
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mGson = new Gson();
        loadData();
    }

    private void loadData() {
        Set<String> storedLocationsSet = getStoredLocationsSet();
        if (storedLocationsSet != null) {
            for (String storedLocation : storedLocationsSet) {
                WeatherResponse weatherResponse = mGson.fromJson(storedLocation, WeatherResponse.class);
                mWeatherResponses.add(weatherResponse);
            }
        }

        String weatherResponseJson = mSharedPrefs.getString(CURRENT_WEATHER_RESPONSE_JSON, null);
        if (weatherResponseJson != null) {
            mCurrentLocationWeather = mGson.fromJson(weatherResponseJson, WeatherResponse.class);
        }
    }

    public void saveCurrentLocation(WeatherResponse weatherResponse) {
        String weatherResponseJson = mGson.toJson(weatherResponse);
        mSharedPrefs.edit().putString(CURRENT_WEATHER_RESPONSE_JSON, weatherResponseJson);
        mCurrentLocationWeather = weatherResponse;
    }

    public WeatherResponse getCurrentLocationWeather() {
        return mCurrentLocationWeather;
    }

    public void saveLocation(WeatherResponse weatherResponse) {
        String weatherResponseJson = mGson.toJson(weatherResponse);
        Set<String> storedLocations = getStoredLocationsSet();
        clearStoredLocationSet();
        storedLocations.add(weatherResponseJson);
        mSharedPrefs.edit().putStringSet(WEATHER_RESPONSES_JSON, storedLocations).commit();
        loadData();
    }

    public void saveLocations(List<WeatherResponse> weatherResponses) {
        Set<String> storedLocations = new HashSet<>();
        for (WeatherResponse weatherResponse : weatherResponses) {
            storedLocations.add(mGson.toJson(weatherResponse));
        }
        clearStoredLocationSet();
        mSharedPrefs.edit().putStringSet(WEATHER_RESPONSES_JSON, storedLocations).commit();
        loadData();
    }

    public void saveLocationsFromAdapter(List<WeatherResponse> weatherResponses) {
        saveCurrentLocation(weatherResponses.get(0));
        List<WeatherResponse> storedWeatherResponses = new ArrayList<>();
        for (int i = 1; i < weatherResponses.size(); i++) {
            storedWeatherResponses.add(weatherResponses.get(i));
        }
        saveLocations(storedWeatherResponses);
    }

    private Set<String> getStoredLocationsSet() {
        return mSharedPrefs.getStringSet(WEATHER_RESPONSES_JSON, new HashSet<String>());
    }

    private void clearStoredLocationSet() {
        mSharedPrefs.edit().remove(WEATHER_RESPONSES_JSON).commit();
    }

    public List<WeatherResponse> getStoredWeatherResponses() {
        return mWeatherResponses;
    }

    public List<WeatherResponse> getAllWeatherResponses() {
        List<WeatherResponse> weatherResponses = new ArrayList<>();
        weatherResponses.add(getCurrentLocationWeather());
        weatherResponses.addAll(getStoredWeatherResponses());

        return weatherResponses;
    }
}
