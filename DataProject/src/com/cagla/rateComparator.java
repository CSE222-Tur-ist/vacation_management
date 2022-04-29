package com;

import java.util.*;
/**
 * rate comparing for sorting hotels
 */
public class rateComparator implements Comparator<Hotel> {
    // Overriding compare()method of Comparator
    // for descending order of rate
    public int compare(Hotel s1, Hotel s2) {
        if (s1.aveRate < s2.aveRate)
            return 1;
        else if (s1.aveRate > s2.aveRate)
            return -1;
        return 0;
    }
}
