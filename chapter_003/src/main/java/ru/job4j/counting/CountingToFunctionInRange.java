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

    List<Double> linear(int start, int end) {
        return diapason(start, end, f -> f);
    }

    List<Double> quadratic(int start, int end) {
        return diapason(start, end, f -> Math.pow(f, 2));
    }

    List<Double> logarithmic(int start, int end) {
        return diapason(start, end, Math::log);
    }
}