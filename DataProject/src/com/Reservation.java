package com;


import java.util.*;

/**
 * kullanıcı için otel rezervasyon bilgileri
 */
public class Reservation implements Comparable<Reservation> {

    protected String startDate;
    protected String endDate;
    protected int startDay;
    protected int startMonth;
    protected int endDay;
    protected int endMonth;
    protected String reservationName; //hotel name

    protected Stack<String> participants = new Stack<String>();
    protected double payment; // this price will be calculated according to the number of participants and dates

    //TODO
    @Override
    public int compareTo(Reservation o) {
        // Tarihlere göre compare edilicek.
        return 0;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append(reservationName.toUpperCase(Locale.ROOT)).append("\n");
        str.append("-".repeat(reservationName.length()));

        for(int i=0;i<participants.size();i++){
            str.append("   - "+participants.get(i)+"\n");
        }
        str.append("From "+startDate+" to "+endDate);
        str.append("Payment : ").append(payment).append(" ₺\n");
        return str.toString();
    }
}
