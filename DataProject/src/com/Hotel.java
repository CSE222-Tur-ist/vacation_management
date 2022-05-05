package com;

import java.util.*;

/**
 * Otel bilgileri
 */

public class Hotel {
    private static final BinarySearchTree<Reservation> reservations = new BinarySearchTree<Reservation>();
    protected String name;
    protected String location;

    protected int numberofRooms;

    protected String features; // denize sıfır, özel plaj, aquapark, wifi falan

    protected double price;
    protected String[] comments;
    protected int[] rates;

    protected int rateSize = 0;

    protected double aveRate= 0.0;

    public Hotel(String name, String location, int numberofRooms, String features, double price) {
        this.name = name;
        this.location = location;
        this.numberofRooms = numberofRooms;
        this.features = features;
        this.price = price;
    }

    public Hotel() {
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append(name.toUpperCase(Locale.ROOT)).append("\n");
        str.append("-".repeat(name.length()));
        str.append("\nLocation : ").append(location).append("\n");
        str.append("Features : ").append(features).append("\n");
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
