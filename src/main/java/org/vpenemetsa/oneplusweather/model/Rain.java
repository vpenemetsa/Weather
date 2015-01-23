package org.vpenemetsa.oneplusweather.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class Rain extends Precipitation implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(threeHours);
    }

    public Rain() {
    }

    public Rain(Parcel in) {
        threeHours = in.readString();
    }

    public static final Parcelable.Creator<Rain> CREATOR = new Parcelable.Creator<Rain>() {
        public Rain createFromParcel(Parcel in) {
            return new Rain(in);
        }

        public Rain[] newArray(int size) {
            return new Rain[size];
        }
    };

}
