package com;

import com.datastructures.Graph;

import java.util.*;

public class Tour implements Comparator<Tour> {
    protected String startDate;
    protected String endDate;
    protected String name;
    protected int numberofTickets;

    // rota için linked list kullanılabilir.(yeni versiyonda graph gelicek)
    protected LinkedList<String> route = new LinkedList<>();


    protected double price;
    protected Queue<String> comments;
    protected Queue<Integer> rates;

    protected int rateSize = 0;

    protected double aveRate = 0.0;

    enum compareType {
        NAME, PRICE, RATE
    }
    private compareType type = compareType.NAME;

    public Tour(Tour.compareType type) {
        this.type = type;
    }

    public Tour(){
    }
    public Tour(String startDate, String endDate, String name, int numberofTickets, LinkedList<String> route, double price, Queue<String> comments, Queue<Integer> rates) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.numberofTickets = numberofTickets;
        this.route = route;
        this.price = price;
        this.comments = comments;
        this.rates = rates;
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
        if(comments != null){
            for (String comment : comments)
                str.append(comment).append("\n");
        }
        if (rates != null){
            for (int rate : rates)
                aveRate += rate;
        }
        aveRate = aveRate/rateSize;
        str.append("Rate : ").append(aveRate).append("\n");
        return str.toString();
    }

    // Overriding compare()method of Comparator
    @Override
    public int compare(Tour tour1, Tour tour2) {

        // ---------------------------------------
        final double ratePriority = 0.60;
        final double NumOfPeoplePriority = 0.40;
        final double result1 = (tour1.aveRate * ratePriority) + (tour1.rateSize * NumOfPeoplePriority);
        final double result2 = (tour2.aveRate * ratePriority) + (tour2.rateSize * NumOfPeoplePriority);

        if (type == compareType.RATE)
            return Double.compare(result1, result2);

        // ---------------------------------------
        if (type == compareType.PRICE)
            return Double.compare(tour1.price, tour2.price);
        else
            return tour1.name.compareTo(tour2.name);

    }
}
