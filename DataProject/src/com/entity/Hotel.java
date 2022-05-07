package com.entity;

import java.util.Comparator;
import java.util.Locale;

import com.dataStructures.BinarySearchTree;

/**
 * Otel bilgileri
 */

public class Hotel implements Comparator<Hotel> {

    public enum compareType {
        NAME, PRICE, RATE
    }

    compareType type = compareType.NAME;

    private static final BinarySearchTree<Reservation> reservations = new BinarySearchTree<Reservation>();
    protected String name;
    protected String location;

    protected int numberofRooms;

    protected String features; // denize sıfır, özel plaj, aquapark, wifi falan

    protected double price;
    protected String[] comments;
    protected int[] rates;

    protected int rateSize = 0;

    protected double aveRate = 0.0;

    public Hotel(String name, String location, int numberofRooms, String features, double price) {
        this.name = name;
        this.location = location;
        this.numberofRooms = numberofRooms;
        this.features = features;
        this.price = price;
    }

    public Hotel() {
    }

    public Hotel(Hotel.compareType type) {
        this.type = type;
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
        aveRate = aveRate / rateSize;
        str.append("Rate : ").append(aveRate).append("\n");
        return str.toString();
    }

    // Overriding compare()method of Comparator
    @Override
    public int compare(Hotel hotel1, Hotel hotel2) {

        // ---------------------------------------
        final double ratePriority = 0.60;
        final double NumOfPeoplePriority = 0.40;
        final double result1 = (hotel1.aveRate * ratePriority) + (hotel1.rateSize * NumOfPeoplePriority);
        final double result2 = (hotel2.aveRate * ratePriority) + (hotel2.rateSize * NumOfPeoplePriority);

        if (type == compareType.RATE)
            return Double.compare(result1, result2);

        // ---------------------------------------
        if (type == compareType.PRICE)
            return Double.compare(hotel1.price, hotel2.price);
        else
            return hotel1.name.compareTo(hotel2.name);

    }
}
