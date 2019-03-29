package ru.job4j.test;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TestTaskTest {

    private TestTask ts = new TestTask();

    @Test
    public void whenNum24AndThenTrueNumbersFromTask() {
        int[] one = {4, 1, 8, 7};
        assertThat(ts.canBeEqualTo24(one, 24), is(true));
    }

    @Test
    public void whenNum24AndThenTrueTestFirst() {
        int[] two = {3, 9, 4, 1};
        assertThat(ts.canBeEqualTo24(two, 24), is(true));
    }

    @Test
    public void whenNum24AndThenTrueTestSecond() {
        int[] two = {9, 9, 6, 1};
        assertThat(ts.canBeEqualTo24(two, 24), is(true));
    }

    @Test
    public void whenNum12AndThenTrue() {
        int[] three = {1, 9, 4, 2};
        assertThat(ts.canBeEqualTo24(three, 12), is(true));
    }

    @Test
    public void whenNum24AndThenFalseNumbersFromTask() {
        int[] three = {1, 2, 1, 2};
        assertThat(ts.canBeEqualTo24(three, 24), is(false));
    }

    @Test
    public void whenNum24AndThenFalse() {
        int[] three = {6, 7, 8, 9};
        assertThat(ts.canBeEqualTo24(three, 24), is(false));
    }
}