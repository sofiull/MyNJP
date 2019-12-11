package com.example.mynjp.model;

public class Banner {
    String nama;
    String link;

    public Banner() {
    }

    public Banner(String nama, String link) {
        this.nama = nama;
        this.link = link;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
