package ru.job4j.loop;

public class Factorial {
    public int calc(int n) {
        int result = 1 * n;
        for (n = 0; n <= 5; n++) {
            if (n <= 0) {
                return 1;
            }
        }
        return result;
    }
}
