package com;

import java.util.Comparator;

public class rateComparatorH implements Comparator<Hotel> {

    // Overriding compare()method of Comparator
    @Override
    public int compare(Hotel hotel1, Hotel hotel2) {

        final double ratePriority = 0.60;
        final double NumOfPeoplePriority = 0.40;
        final double result1 = (hotel1.aveRate * ratePriority) + (hotel1.rateSize * NumOfPeoplePriority);
        final double result2 = (hotel2.aveRate * ratePriority) + (hotel2.rateSize * NumOfPeoplePriority);

        return Double.compare(result1, result2);
    }
}
