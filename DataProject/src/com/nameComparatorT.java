package com;

import java.util.*;
/**
 * name comparing for sorting tours
 */
public class nameComparatorT implements Comparator<Tour>{

    // Overriding compare()method of Comparator
    // for descending order of name
    public int compare(Tour s1, Tour s2) {
        return s1.name.compareTo(s2.name);
    }
}
