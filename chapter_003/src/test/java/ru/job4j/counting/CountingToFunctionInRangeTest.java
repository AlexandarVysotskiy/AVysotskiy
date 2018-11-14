package ru.job4j.counting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class CountingToFunctionInRangeTest {
    CountingToFunctionInRange cfr = new CountingToFunctionInRange();

    @Test
    public void linearTest() {
        List<Double> result = new ArrayList<>(Arrays.asList(1D, 2D, 3D, 4D, 5D));
        assertThat(result, is(cfr.linear(1, 5)));
    }

    @Test
    public void quadraticTest() {
        List<Double> result = new ArrayList<>(Arrays.asList(1D, 4D, 9D, 16D, 25D));
        assertThat(result, is(cfr.quadratic(1, 5)));
    }

    @Test
    public void logarithmic() {
        List<Double> result = new ArrayList<>(Arrays.asList(Math.log(1D), Math.log(2D), Math.log(3D)));
    }
}