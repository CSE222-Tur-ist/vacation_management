package com;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Stack;

public class Ticket {
    // TODO: TOUR , ALAN KISI ADI, isCanceled()
    //private String startDate;
    //private String endDate;

    protected String ticketName; //tour name
    protected Stack<String> participants = new Stack<String>();

    protected boolean isCanceled;
    protected double payment; // this price will be calculated according to the number of participants
    public Ticket(){

    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append(ticketName.toUpperCase(Locale.ROOT)).append("\n");
        str.append("-".repeat(ticketName.length()));

        for(int i=0;i<participants.size();i++){
            str.append("   - "+participants.get(i)+"\n");
        }
        str.append("Payment : ").append(payment).append(" ₺\n");
        return str.toString();
    }

}
