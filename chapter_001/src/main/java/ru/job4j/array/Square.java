package ru.job4j.array;

public class Square {
    public int[] calculate(int bound) {

        int[] rst = new int[bound];
        for (int x=1; x<= bound; x++){
            rst[x-1] = x*x;}
        return rst;
    }
}