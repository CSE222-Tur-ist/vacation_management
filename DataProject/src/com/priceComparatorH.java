package com;
import java.util.*;
/**
 * price comparing for sorting hotels
 */
public class priceComparatorH implements Comparator<Hotel>{
    // Overriding compare()method of Comparator
    // for descending order of price
    public int compare(Hotel s1, Hotel s2) {
        return Double.compare(s1.price, s2.price);
    }
}
