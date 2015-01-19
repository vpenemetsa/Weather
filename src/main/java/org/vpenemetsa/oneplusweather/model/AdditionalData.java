package org.vpenemetsa.oneplusweather.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class AdditionalData implements Parcelable {

    private String country;

    private long sunrise;

    private long sunset;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(country);
        parcel.writeLong(sunrise);
        parcel.writeLong(sunset);
    }

    public AdditionalData() {
    }

    public AdditionalData(Parcel in) {
        country = in.readString();
        sunrise = in.readLong();
        sunset = in.readLong();
    }

    public static final Parcelable.Creator<AdditionalData> CREATOR = new Parcelable.Creator<AdditionalData>() {
        public AdditionalData createFromParcel(Parcel in) {
            return new AdditionalData(in);
        }

        public AdditionalData[] newArray(int size) {
            return new AdditionalData[size];
        }
    };
}
