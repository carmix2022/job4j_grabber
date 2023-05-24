package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMin {
    private  <T> T findListExtremum(List<T> value, Comparator<T> comparator) {
        if (value.isEmpty()) {
            return null;
        }
        T listExtremum = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (comparator.compare(listExtremum, value.get(i)) < 0) {
                listExtremum = value.get(i);
            }
        }
        return listExtremum;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findListExtremum(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findListExtremum(value, comparator.reversed());
    }
}