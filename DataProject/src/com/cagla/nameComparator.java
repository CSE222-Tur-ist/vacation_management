package com;

import java.util.*;
/**
 * name comparing for sorting hotels
 */
public class nameComparator implements Comparator<Hotel>{
        // Overriding compare()method of Comparator
        // for descending order of name
        public int compare(Hotel s1, Hotel s2) {
            if(s1.name.compareTo(s2.name)<0)
                return 1;
            else if (s1.name.compareTo(s2.name)>0)
                return -1;
            return 0;
        }
}
