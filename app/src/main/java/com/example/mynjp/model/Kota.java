package com.example.mynjp.model;

public class Kota {
    String nama;
    String provinsi;
    int jarak;

    public Kota() {
    }

    public Kota(String nama, String provinsi, int jarak) {
        this.nama = nama;
        this.provinsi = provinsi;
        this.jarak = jarak;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
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
