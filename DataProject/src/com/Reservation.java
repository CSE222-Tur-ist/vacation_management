package com;


import java.util.*;

/**
 * kullanıcı için otel rezervasyon bilgileri
 */
public class Reservation {

    private String startDate;
    private String endDate;

    private String[] participants;
    private String payment; // this price will be calculated according to the number of participants and dates

