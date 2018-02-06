package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        Turn Even = new Turn();
        int p[]={2, 6, 1, 4};
        int []result = Even.back(p);
        int n[]={4,1,6,2};
        assertThat(result, is(n));
    }

    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        Turn Odd = new Turn();
        int w[]={1, 2, 3, 4, 5};
        int []result = Odd.back(w);
        int a[]={5, 4, 3, 2, 1};
        assertThat(result, is(a));
    }
}