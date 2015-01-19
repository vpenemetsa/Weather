package org.vpenemetsa.oneplusweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class Wind {

    private double speed;

    @SerializedName("deg")
    private long direction;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public long getDirection() {
        return direction;
    }

    public void setDirection(long direction) {
        this.direction = direction;
    }
}
