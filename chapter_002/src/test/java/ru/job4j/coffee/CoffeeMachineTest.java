package ru.job4j.coffee;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {
    @Test
    public void changesTest() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.changes(98, 30);
        int[] expected = {10, 10, 10, 10, 10, 10, 5, 2, 1};
        assertThat(expected, is(result));
    }
}
