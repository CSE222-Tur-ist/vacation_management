package com;

import java.util.LinkedList;
import java.util.Locale;

public class Tour {
    protected String startDate;
    protected String endDate;
    protected String name;
    protected int numberofTickets;

    // rota için linked list kullanılabilir.
    protected LinkedList<String> route = new LinkedList<>();

    protected double price;
    protected String[] comments;
    protected int[] rates;

    protected double aveRate;

    @Override
    public String toString() {
        String str = new String();
        str += name.toUpperCase(Locale.ROOT) +"\n";
        for(int i=0;i<name.length();i++) str+="-";
        str += "Route : ";
        for(String nextStr : route) str += "  "+nextStr;
        str += "\nStart Date : "+startDate+"\n";
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
