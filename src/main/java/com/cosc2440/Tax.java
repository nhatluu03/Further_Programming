package com.cosc2440;

public class Tax {
    private double value;

    public static double getTaxRate(String s){
        if (s.equalsIgnoreCase("Normal")){
            return 0.1;
        }
        else if (s.equalsIgnoreCase("Luxury")){
            return 0.2;
        }
        return 0;
    }
}
