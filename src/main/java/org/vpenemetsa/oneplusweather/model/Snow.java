package org.vpenemetsa.oneplusweather.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class Snow extends Precipitation implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(threeHours);
    }

    public Snow() {
    }

    public Snow(Parcel in) {
        threeHours = in.readString();
    }

    public static final Parcelable.Creator<Snow> CREATOR = new Parcelable.Creator<Snow>() {
        public Snow createFromParcel(Parcel in) {
            return new Snow(in);
        }

        public Snow[] newArray(int size) {
            return new Snow[size];
        }
    };
}
