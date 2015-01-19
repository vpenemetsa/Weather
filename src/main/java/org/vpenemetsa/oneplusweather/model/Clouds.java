package org.vpenemetsa.oneplusweather.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vijaypenemetsa on 1/19/15.
 */
public class Clouds implements Parcelable {

    private long all;

    public long getAll() {
        return all;
    }

    public void setAll(long all) {
        this.all = all;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(all);
    }

    public Clouds() {
    }

    public Clouds(Parcel in) {
        all = in.readLong();
    }

    public static final Parcelable.Creator<Clouds> CREATOR = new Parcelable.Creator<Clouds>() {
        public Clouds createFromParcel(Parcel in) {
            return new Clouds(in);
        }

        public Clouds[] newArray(int size) {
            return new Clouds[size];
        }
    };
}
