package com;

import com.datastructures.BinarySearchTree;

import java.util.*;

/**
 * Otel bilgileri
 */

public class Hotel implements Comparator<Hotel>{


    public enum compareType {
        NAME, PRICE, RATE, DISTANCE
    }
    compareType type = compareType.NAME;

    private static final BinarySearchTree<Reservation> reservations = new BinarySearchTree<Reservation>();
    protected String name;
    protected String location;

    protected month[] calendar = new month[12]; // uygun tarihleri kontrol etmek için

    protected String[] months = {
            "January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December"
    };
    protected int numberofRooms;

    protected String features; // denize sıfır, özel plaj, aquapark, wifi falan

    protected double price;
    protected Queue<String> comments;
    protected Queue<Integer> rates;

    protected int rateSize = 0;

    protected double aveRate= 0.0;

    public Hotel(String name, String location, int numberofRooms, String features, double price) {
        this.name = name;
        this.location = location;
        this.numberofRooms = numberofRooms;
        this.features = features;
        this.price = price;
        initializeCalendar();

    }

    public Hotel(Hotel.compareType type) {
        this.type = type;
    }

    public Hotel() {
        initializeCalendar();
    }

    private void initializeCalendar(){
        int day = 1;
        for(int k=0;k<calendar.length;k++){
            day=1;
            calendar[k] = new month(k);
            for(int i=0;i<calendar[k].days.length;i++){ //row
                    calendar[k].days[i] = new day(day,false);
                    day++;
            }
        }
    }

    public void printCalendar(){ // shows available dates for reservation
        int spaces = 0;
        int lengthofMonth=calendar[0].days.length;

        for (int M = 0; M <= 11; M++) {
            // print calendar header
            // Display the month and year
            System.out.println("\n\n          "+ months[M]);

            // Display the lines
            System.out.println("_____________________________________");
            System.out.println("  Sun  Mon Tue   Wed Thu   Fri  Sat");

            // spaces required
            spaces = (lengthofMonth+ spaces)%7;

            // print the calendar
            for (int k = 0; k < spaces; k++)
                System.out.print("     ");
            for(int i=0;i<calendar[M].days.length;i++) {
                    if(!calendar[M].days[i].isFull) System.out.printf(" %3d ",calendar[M].days[i].day);
                    else System.out.printf("  ");
                    if((i+1)%7==0)System.out.println();
            }
        }
        System.out.println();
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

        // ---------------------------------------
        else
            return hotel1.name.compareTo(hotel2.name);
    }
}
class month {
    protected int month;
    protected day[] days = new day[28]; // her ayın 28 gününe randevu alınabilir, son günler temizlik vs

    public month(){

    }
    public month(int k){
        month = k;
    }
}
class day {
    protected int day;
    protected boolean isFull;
    public day(){

    }
    public day(int d,boolean f){
        day = d;
        isFull = f;
    }
}
