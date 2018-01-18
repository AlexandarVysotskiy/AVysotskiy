package ru.job4j.loop;

public class Counter {
    public int add(int start, int finish) {
        int result=0;
        if (start % 2 != 0) {
            start++;}
        while (start <= finish) {
            result += start;
            start = start + 2;
        }
        return result;
    }
}
