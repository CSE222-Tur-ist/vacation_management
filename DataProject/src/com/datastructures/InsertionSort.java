package com.datastructures;

import java.util.ArrayList;

public class InsertionSort {
    public static <T extends Comparable<T>> void sort(ArrayList<T> table) {
        for (int nextPos = 1; nextPos < table.size(); nextPos++) {
            insert(table, nextPos);
        }
    }

    private static <T extends Comparable<T>> void insert(ArrayList<T> table,int nextPos) {
        T nextVal = table.get(nextPos);
        while (nextPos > 0 && nextVal.compareTo(table.get(nextPos - 1)) < 0) {
            table.set(nextPos, table.get(nextPos - 1));
            nextPos--;
            table.set(nextPos,nextVal);
        }
    }
}
