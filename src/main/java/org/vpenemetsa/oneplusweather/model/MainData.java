package org.vpenemetsa.oneplusweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class MainData implements Parcelable {

    private String temp;

    @SerializedName("temp_min")
    private String minTemp;

    @SerializedName("temp_max")
    private String maxTemp;

    private String pressure;

    private long humidity;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(temp);
        parcel.writeString(minTemp);
        parcel.writeString(maxTemp);
        parcel.writeString(pressure);
        parcel.writeLong(humidity);
    }

    public MainData() {
    }

    public MainData(Parcel in) {
        temp = in.readString();
        minTemp = in.readString();
        maxTemp = in.readString();
        pressure = in.readString();
        humidity = in.readLong();
    }

    public static final Parcelable.Creator<MainData> CREATOR = new Parcelable.Creator<MainData>() {
        public MainData createFromParcel(Parcel in) {
            return new MainData(in);
        }

        public MainData[] newArray(int size) {
            return new MainData[size];
        }
    };
}
