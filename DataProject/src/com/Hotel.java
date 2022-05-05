package com;

import java.util.*;

/**
 * Otel bilgileri
 */
public class Hotel {

    protected String name;
    protected String location;

    protected int numberofRooms;

    protected String features; // denize sıfır, özel plaj, aquapark, wifi falan

    protected String startDate;
    protected String endDate;

    protected double price;
    protected String[] comments;
    protected int[] rates;

    protected double aveRate=0;

    @Override
    public String toString() {
        String str = new String();
        str += name.toUpperCase(Locale.ROOT) +"\n";
        for(int i=0;i<name.length();i++) str+="-";
        str += "\nLocation : "+location+"\n";
        str += "Features : "+features+"\n";
        str += "Start Date : "+startDate+"\n";
        str += "End Date : "+endDate+"\n";
        str += "Price : "+price+" ₺\n";
        str += "Comments\n";
        for(int i=0;i<comments.length;i++) str+=comments[i]+"\n";

        // ortalama hespalama değişebilir
        for(int i=0;i<rates.length;i++) aveRate+=rates[i];
        aveRate = aveRate/rates.length;
        str += "Rate : "+aveRate+"\n";
        return str;
    }
}
