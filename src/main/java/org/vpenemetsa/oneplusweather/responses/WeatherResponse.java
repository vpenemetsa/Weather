package org.vpenemetsa.oneplusweather.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.vpenemetsa.oneplusweather.model.AdditionalData;
import org.vpenemetsa.oneplusweather.model.Clouds;
import org.vpenemetsa.oneplusweather.model.MainData;
import org.vpenemetsa.oneplusweather.model.Rain;
import org.vpenemetsa.oneplusweather.model.Snow;
import org.vpenemetsa.oneplusweather.model.Weather;
import org.vpenemetsa.oneplusweather.model.Wind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class WeatherResponse implements Parcelable {

    private List<Weather> weather;

    @SerializedName("sys")
    private AdditionalData additionalData;

    private MainData main;

    private Wind wind;

    private Rain rain;

    private Snow snow;

    private Clouds clouds;

    @SerializedName("dt")
    private String date;

    private String name;

    private long cod;

    private String id;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }

    public MainData getMain() {
        return main;
    }

    public void setMain(MainData main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WeatherResponse(){};

    protected WeatherResponse(Parcel in) {
        weather = new ArrayList<>();
        in.readList(weather, Weather.class.getClassLoader());
        additionalData = in.readParcelable(AdditionalData.class.getClassLoader());
        main = in.readParcelable(MainData.class.getClassLoader());
        wind = in.readParcelable(Wind.class.getClassLoader());
        rain = in.readParcelable(Rain.class.getClassLoader());
        snow = in.readParcelable(Snow.class.getClassLoader());
        clouds = in.readParcelable(Clouds.class.getClassLoader());
        date = in.readString();
        name = in.readString();
        cod = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(weather);
        parcel.writeParcelable(additionalData, i);
        parcel.writeParcelable(main, i);
        parcel.writeParcelable(wind, i);
        parcel.writeParcelable(rain, i);
        parcel.writeParcelable(snow, i);
        parcel.writeParcelable(clouds, i);
        parcel.writeString(date);
        parcel.writeString(name);
        parcel.writeLong(cod);
    }

    public static final Parcelable.Creator<WeatherResponse> CREATOR = new Parcelable.Creator<WeatherResponse>() {
        public WeatherResponse createFromParcel(Parcel in) {
            return new WeatherResponse(in);
        }

        public WeatherResponse[] newArray(int size) {
            return new WeatherResponse[size];
        }
    };
}
