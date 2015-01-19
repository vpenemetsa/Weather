package org.vpenemetsa.oneplusweather.responses;

import java.util.List;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class ListWeatherResponse {

    private List<WeatherResponse> list;

    public List<WeatherResponse> getList() {
        return list;
    }

    public void setList(List<WeatherResponse> list) {
        this.list = list;
    }
}
