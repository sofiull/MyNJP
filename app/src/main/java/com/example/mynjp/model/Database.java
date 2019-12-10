package com.example.mynjp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Database implements Parcelable {
    private String banner;
    private String kota;

    public Database(String banner, String kota) {
        this.banner = banner;
        this.kota = kota;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.banner);
        dest.writeString(this.kota);
    }

    protected Database(Parcel in) {
        this.banner = in.readString();
        this.kota = in.readString();
    }

    public static final Parcelable.Creator<Database> CREATOR = new Parcelable.Creator<Database>() {
        @Override
        public Database createFromParcel(Parcel source) {
            return new Database(source);
        }

        @Override
        public Database[] newArray(int size) {
            return new Database[size];
        }
    };
}
