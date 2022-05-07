package com;

import java.util.*;
/**
 * name comparing for sorting hotels
 */
public class nameComparatorH implements Comparator<Hotel>{

    // Overriding compare()method of Comparator
    // for descending order of name
    public int compare(Hotel s1, Hotel s2) {
        return s1.name.compareTo(s2.name);
    }
}
