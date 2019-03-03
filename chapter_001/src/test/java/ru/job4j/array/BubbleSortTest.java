package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        BubbleSort bubbles = new BubbleSort();
        int[] q = {1, 2, 10, 3, 100, 50, 51, 53};
        int[] result;
        result = bubbles.sort(q);
        int[] e = {1, 2, 3, 10, 50, 51, 53, 100};
        assertThat(result, is(e));
    }
}