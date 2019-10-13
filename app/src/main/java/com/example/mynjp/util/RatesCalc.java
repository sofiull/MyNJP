package com.example.mynjp.util;

public class RatesCalc{

    private int origin;
    private int destination;
    private double weight;
    private int jarak;
    private double ratesYES;
    private double ratesREG;

    public RatesCalc(int origin, int destination, double weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
        this.ratesYES = calculateYES();
        this.ratesREG = calculateREG();
    }

    public int getRatesYES() { return (int) ratesYES; }

    public int getRatesREG() { return (int) ratesREG; }

    private double calculateYES(){
        if (hitungJarak()*weight*7000 < 9000){
            return 9000;    // harga YES termurah
        }else{
            return hitungJarak()*weight*9000;
        }
    }

    private double calculateREG(){
        if (hitungJarak()*weight*7000 < 7000){
            return 7000;    // harga reguler termurah
        }else{
            return hitungJarak()*weight*7000;
        }
    }

    private double hitungJarak(){
        jarak = origin - destination;
        if(jarak == 0){         // jika satu kota
            jarak = 1;
        }else if(jarak < 0) {   //  minus ke positif
            jarak *= -1;
        }
        return jarak;
    }

}