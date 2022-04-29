package com;

import java.util.*;
/**
 * rate comparing for sorting tours
 */
public class rateComparatorT implements Comparator<Tour> {
    // Overriding compare()method of Comparator
    // for descending order of rate
    public int compare(Tour s1, Tour s2) {
        if (s1.aveRate < s2.aveRate)
            return 1;
        else if (s1.aveRate > s2.aveRate)
            return -1;
        return 0;
    }
}
