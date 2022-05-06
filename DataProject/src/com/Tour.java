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

    protected int rateSize = 0;

    protected double aveRate = 0.0;

    public Tour(){

    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append(name.toUpperCase(Locale.ROOT)).append("\n");
        str.append("-".repeat(name.length()));
        str.append("Route : ");
        for(String nextStr : route)
            str.append("  ").append(nextStr);
        str.append("\nStart Date : ").append(startDate).append("\n");
        str.append("End Date : ").append(endDate).append("\n");
        str.append("Price : ").append(price).append(" ₺\n");
        str.append("Comments\n");
        for (String comment : comments)
            str.append(comment).append("\n");

        // ortalama hespalama değişebilir
        for (int rate : rates)
            aveRate += rate;
        aveRate = aveRate/rateSize;
        str.append("Rate : ").append(aveRate).append("\n");
        return str.toString();
    }
}
