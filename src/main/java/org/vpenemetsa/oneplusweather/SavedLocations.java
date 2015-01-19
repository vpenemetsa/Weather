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

    private List<WeatherResponse> mWeatherResponses = new ArrayList<>();

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

    }

    public void saveLocation(WeatherResponse weatherResponse) {
        String weatherResponseJson = mGson.toJson(weatherResponse);
        Set<String> storedLocations = getStoredLocationsSet();
        storedLocations.add(weatherResponseJson);
        mSharedPrefs.edit().putStringSet(WEATHER_RESPONSES_JSON, storedLocations).commit();
    }

    public void saveLocations(List<WeatherResponse> weatherResponses) {
        Set<String> storedLocations = new HashSet<>();
        for (WeatherResponse weatherResponse : weatherResponses) {
            storedLocations.add(mGson.toJson(weatherResponse));
        }
        mSharedPrefs.edit().putStringSet(WEATHER_RESPONSES_JSON, storedLocations).commit();
    }

    private Set<String> getStoredLocationsSet() {
        return mSharedPrefs.getStringSet(WEATHER_RESPONSES_JSON, null);
    }

    public List<WeatherResponse> getStoredWeatherResponses() {
       return mWeatherResponses;
    }
}
