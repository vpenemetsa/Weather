package org.vpenemetsa.oneplusweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class MainData {

    private double temp;

    @SerializedName("temp_min")
    private double minTemp;

    @SerializedName("temp_max")
    private double maxTemp;

    private double pressure;

    private long humidity;


}
