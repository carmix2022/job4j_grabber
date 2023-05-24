package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    private final MaxMin maxMin = new MaxMin();

    @Test
    void whenListIsNotEmptyThenMax() {
        List<Integer> list = List.of(8, 4, 6, 2, 0, 9);
        Integer output = maxMin.max(list, Comparator.comparingInt(x -> x));
        Integer expected = 9;
        assertThat(output).isEqualTo(expected);
    }

    @Test
    void whenListIsEmptyThenMaxIsNull() {
        List<Integer> list = new ArrayList<>();
        Integer output = maxMin.max(list, Comparator.comparingInt(x -> x));
        Integer expected = null;
        assertThat(output).isEqualTo(expected);
    }

    @Test
    void whenListIsNotEmptyThenMin() {
        List<Integer> list = List.of(8, 4, 6, 2, 0, 9);
        Integer output = maxMin.min(list, Comparator.comparingInt(x -> x));
        Integer expected = 0;
        assertThat(output).isEqualTo(expected);
    }

    @Test
    void whenListIsEmptyThenMinIsNull() {
        List<Integer> list = new ArrayList<>();
        Integer output = maxMin.min(list, Comparator.comparingInt(x -> x));
        Integer expected = null;
        assertThat(output).isEqualTo(expected);
    }
}