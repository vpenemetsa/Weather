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

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }
}
