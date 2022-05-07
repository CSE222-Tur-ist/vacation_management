package com.entity;


/**
 * kullanıcı için otel rezervasyon bilgileri
 */
public class Reservation implements Comparable<Reservation> {

    private String startDate;
    private String endDate;

    private String[] participants;
    private String payment; // this price will be calculated according to the number of participants and dates

    //TODO
    @Override
    public int compareTo(Reservation o) {
        // Tarihlere göre compare edilicek.
        return 0;
    }
}
