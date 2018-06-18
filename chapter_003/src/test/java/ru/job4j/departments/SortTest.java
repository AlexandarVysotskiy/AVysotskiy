package ru.job4j.departments;

import org.junit.Before;
import org.junit.Test;

import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortTest {
    TreeSet<String> input = new TreeSet<>();
    Sort sorter = new Sort();

    @Before
    public void addToInput() {
        input.add("K1\\SK1\\SSK2");
        input.add("K1\\SK1");
        input.add("K1\\SK2");
        input.add("K2\\SK1\\SSK2");
        input.add("K1\\SK1\\SSK1");
        input.add("K2");
        input.add("K2\\SK1\\SSK1");
    }

    @Test
    public void sortAscendingTest() {
        TreeSet<String> output = sorter.sortAscending(input);
        System.out.println(output);
        assertThat(output.toString(), is("[K1, K1\\SK1, K1\\SK1\\SSK1, K1\\SK1\\SSK2, K1\\SK2, K2, K2\\SK1, K2\\SK1\\SSK1, K2\\SK1\\SSK2]"));
    }

    @Test
    public void whenSortDecrease() {
        TreeSet<String> output = sorter.diminution(input);
        System.out.println(output);
        assertThat(output.toString(), is("[K2, K2\\SK1, K2\\SK1\\SSK2, K2\\SK1\\SSK1, K1, K1\\SK2, K1\\SK1, K1\\SK1\\SSK2, K1\\SK1\\SSK1]"));
    }
}