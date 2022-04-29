package com;
import java.util.*;
/**
 * price comparing for sorting hotels
 */
public class priceComparator implements Comparator<Hotel>{
    // Overriding compare()method of Comparator
    // for descending order of price
    public int compare(Hotel s1, Hotel s2) {
        if (s1.price < s2.price)
            return 1;
        else if (s1.price > s2.price)
            return -1;
        return 0;
    }
}
