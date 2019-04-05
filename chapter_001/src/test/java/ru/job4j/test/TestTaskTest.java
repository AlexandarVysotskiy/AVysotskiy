package ru.job4j.test;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TestTaskTest {

    private TestTask ts = new TestTask();

    @Test
    public void whenTwoTwoFiveFiveNumThenTrue() {
        int[] one = {2, 2, 5, 5};
        assertThat(ts.canBeEqualTo24(one, 24), is(true));
    }

    @Test
    public void whenThreeThreeFiveFiveNumThenTrue() {
        int[] one = {3, 3, 5, 5};
        assertThat(ts.canBeEqualTo24(one, 24), is(true));
    }

    @Test
    public void whenThreeThreeSevenSevenNumThenTrue() {
        int[] one = {3, 3, 7, 7};
        assertThat(ts.canBeEqualTo24(one, 24), is(true));
    }

    @Test
    public void whenNumOneThreeEightEightThenTrue() {
        int[] one = {1, 3, 8, 8};
        assertThat(ts.canBeEqualTo24(one, 24), is(true));
    }

    @Test
    public void whenNumThreeThreeEightEightThenTrue() {
        int[] one = {3, 3, 8, 8};
        assertThat(ts.canBeEqualTo24(one, 24), is(true));
    }

    @Test
    public void whenNumFourSixEightNineThenTrue() {
        int[] one = {4, 6, 8, 9};
        assertThat(ts.canBeEqualTo24(one, 24), is(true));
    }

    @Test
    public void whenNumOneThreeEightNineThenTrue() {
        int[] one = {1, 3, 8, 9};
        assertThat(ts.canBeEqualTo24(one, 24), is(true));
    }

    @Test
    public void whenNumSixSixSixSixThenTrue() {
        int[] one = {6, 6, 6, 6};
        assertThat(ts.canBeEqualTo24(one, 24), is(true));
    }

    @Test
    public void whenNumThreeEightNineNineThenTrue() {
        int[] one = {3, 8, 9, 9};
        assertThat(ts.canBeEqualTo24(one, 24), is(true));
    }

    @Test
    public void whenNum24AndThenTrueNumbersFromTask() {
        int[] one = {3, 3, 7, 7};
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

    @Test
    public void whenNumEightEightSevenSevenThenFalse() {
        int[] three = {8, 8, 7, 7};
        assertThat(ts.canBeEqualTo24(three, 24), is(false));
    }

    @Test
    public void whenNumOneTwoThreeOneThenFalse() {
        int[] three = {1, 2, 3, 1};
        assertThat(ts.canBeEqualTo24(three, 24), is(false));
    }

    @Test
    public void whenNumEightEightOneOneThenFalse() {
        int[] three = {8, 8, 1, 1};
        assertThat(ts.canBeEqualTo24(three, 24), is(false));
    }

    @Test
    public void whenNumOneThreeTwoThreeThenFalse() {
        int[] one = {1, 3, 2, 3};
        assertThat(ts.canBeEqualTo24(one, 24), is(false));
    }
}