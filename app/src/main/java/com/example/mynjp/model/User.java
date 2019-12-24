package com.example.mynjp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String nama;
    private String alamat;
    private String username;
    private String password;

    public User(String nama, String alamat, String username, String password) {
        this.nama = nama;
        this.alamat = alamat;
        this.username = username;
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.alamat);
        dest.writeString(this.username);
        dest.writeString(this.password);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.nama = in.readString();
        this.alamat = in.readString();
        this.username = in.readString();
        this.password = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
