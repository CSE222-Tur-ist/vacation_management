package com;
import java.util.*;
/**
 * price comparing for sorting tours
 */
public class priceComparatorT implements Comparator<Tour>{
    // Overriding compare()method of Comparator
    // for descending order of price
    public int compare(Tour s1, Tour s2) {
        if (s1.price < s2.price)
            return 1;
        else if (s1.price > s2.price)
            return -1;
        return 0;
    }
}
