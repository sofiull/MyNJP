package com.example.mynjp.model;

public class Kota {
    String nama;
    int jarak;

    public Kota() {
    }

    public Kota(String nama, int jarak) {
        this.nama = nama;
        this.jarak = jarak;
    }

    public String getNama() {
        return nama;
    }

    public void nama(String nama) {
        this.nama = nama;
    }

    public int getJarak() {
        return jarak;
    }

    public void setJarak(int jarak) {
        this.jarak = jarak;
    }
}
