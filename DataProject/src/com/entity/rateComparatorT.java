package com;

import java.util.Comparator;

public class rateComparatorT implements Comparator<Tour> {

    // Overriding compare()method of Comparator
    @Override
    public int compare(Tour tour1, Tour tour2) {

        final double ratePriority = 0.60;
        final double NumOfPeoplePriority = 0.40;
        final double result1 = (tour1.aveRate * ratePriority) + (tour1.rateSize * NumOfPeoplePriority);
        final double result2 = (tour2.aveRate * ratePriority) + (tour2.rateSize * NumOfPeoplePriority);

        return Double.compare(result1, result2);
    }
}
