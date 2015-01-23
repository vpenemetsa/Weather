package org.vpenemetsa.oneplusweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
abstract class Precipitation {

    @SerializedName("3h")
    public String threeHours;

    public String getThreeHours() {
        return threeHours;
    }

    public void setThreeHours(String threeHours) {
        this.threeHours = threeHours;
    }
}
