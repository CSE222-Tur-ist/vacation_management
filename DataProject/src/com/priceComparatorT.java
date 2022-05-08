package com;
import java.util.*;
/**
 * price comparing for sorting tours
 */
public class priceComparatorT implements Comparator<Tour>{
    // Overriding compare()method of Comparator
    // for descending order of price
    public int compare(Tour s1, Tour s2) {
        return Double.compare(s1.price, s2.price);
    }
}
