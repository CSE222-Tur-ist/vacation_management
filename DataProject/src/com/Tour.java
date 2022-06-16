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
    protected Queue<String> comments = new PriorityQueue<>();;
    protected Queue<Integer> rates = new PriorityQueue<>();


    protected int rateSize=0;
    protected double aveRate = 0.0;

    enum compareType {
        NAME, PRICE, RATE
    }
    public static compareType type = compareType.NAME;

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
        str.append(" ");
        str.append(name.toUpperCase(Locale.ROOT)).append("\n");
        str.append("\u2500".repeat(name.length()+3));
        str.append("\nRoute : ");
        for(String nextStr : route)
            str.append("  ").append(nextStr);
        str.append("\nStart Date : ").append(startDate).append("\n");
        str.append("End Date : ").append(endDate).append("\n");
        str.append("Price : ").append(price).append(" ₺\n");

        if(comments.size()!=0){
            str.append("Comments\n");
            for (String comment : comments)
                str.append(comment).append("\n");
        }
        else if(comments.size()==0) str.append("Comments: -\n");
        if (rates.size()!=0){
            str.append("Rate : ").append(String.format("%.2f",calculateAve())).append("\n");
        }
        else if(rates.size()==0)str.append("Rate : -\n");
        return str.toString();
    }
    public double calculateAve(){
        aveRate=0;
        if (rates.size() != 0) {
            for (int rate : rates) {
                aveRate += rate;
            }
            aveRate = aveRate / rates.size();
        }
        return aveRate;
    }
    public double getAve(){
        return calculateAve();
    }
    // Overriding compare()method of Comparator
    @Override
    public int compare(Tour tour1, Tour tour2) {

        if (type == compareType.RATE)
            return Double.compare(tour1.getAve(),tour2.getAve());

        // ---------------------------------------
        if (type == compareType.PRICE)
            return Double.compare(tour1.price, tour2.price);
        else
            return tour1.name.compareTo(tour2.name);

    }
}
