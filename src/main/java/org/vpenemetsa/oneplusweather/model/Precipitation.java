package org.vpenemetsa.oneplusweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class Precipitation implements Parcelable {

    @SerializedName("3h")
    private String threeHours;

    public String getThreeHours() {
        return threeHours;
    }

    public void setThreeHours(String threeHours) {
        this.threeHours = threeHours;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(threeHours);
    }

    public Precipitation() {
    }

    public Precipitation(Parcel in) {
        threeHours = in.readString();
    }

    public static final Parcelable.Creator<Precipitation> CREATOR = new Parcelable.Creator<Precipitation>() {
        public Precipitation createFromParcel(Parcel in) {
            return new Precipitation(in);
        }

        public Precipitation[] newArray(int size) {
            return new Precipitation[size];
        }
    };
}
