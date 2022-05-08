package com;

import java.util.ArrayList;

/*
    oteller için konum bilgisi, findHotelbyLocation için kullanılacak
 */

public class Location implements Comparable<Location>{
    protected String locationName;
    protected int hotelCounter;
    protected ArrayList<Hotel> hotelsList = new ArrayList<Hotel>();

    public Location(String name) { this.locationName = name; }

    public String getCityName() { return locationName; }

    public int getHotelCounter() { return hotelCounter; }

    @Override
    public int compareTo(Location o) {
        return this.locationName.compareTo(o.locationName);
    }

    @Override
    public String toString() {
        return this.locationName;
    }
}
