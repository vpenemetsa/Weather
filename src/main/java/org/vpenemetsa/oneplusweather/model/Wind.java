package org.vpenemetsa.oneplusweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class Wind implements Parcelable {

    private double speed;

    @SerializedName("deg")
    private String direction;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(speed);
        parcel.writeString(direction);
    }

    public Wind() {
    }

    public Wind(Parcel in) {
        speed = in.readDouble();
        direction = in.readString();
    }

    public static final Parcelable.Creator<Wind> CREATOR = new Parcelable.Creator<Wind>() {
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };
}
