package org.vpenemetsa.oneplusweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class Wind {

    private double speed;

    @SerializedName("deg")
    private long direction;
}
