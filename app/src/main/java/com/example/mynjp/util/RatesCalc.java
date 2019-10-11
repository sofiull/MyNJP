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
        jarak = origin - destination;
        if(jarak == 0){
            jarak = 1;
        }else if(jarak < 0){
            //  minus ke positif
            jarak*=-1;
        }
        return jarak*weight*9000;
    }

    private double calculateREG(){
        jarak = origin - destination;
        if(jarak == 0){
            jarak = 1;
        }else if(jarak < 0){
            //  minus ke positif
            jarak*=-1;
        }
        return jarak*weight*7000;
    }


}