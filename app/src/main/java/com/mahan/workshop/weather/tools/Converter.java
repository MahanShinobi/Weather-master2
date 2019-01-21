package com.mahan.workshop.weather.tools;

public class Converter {

    public static int convertToF(int value){
        return (int) ((value * 1.8) + 32);
    }

    public static int convertToC(int value){
        return (int) ((value - 32) / 1.8);
    }

}
