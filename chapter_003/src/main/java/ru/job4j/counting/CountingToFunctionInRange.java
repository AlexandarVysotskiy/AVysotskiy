package ru.job4j.counting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CountingToFunctionInRange {
    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        ArrayList<Double> result = new ArrayList<Double>();
        for (int i = start; i <= end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }
}