package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] twoArray(List<Integer> list, int rows) {
        int[][] array = new int[rows][rows];
        int cells = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (cells >= list.size()) {
                    cells++;
                } else {
                    array[i][j] = list.get(cells++);
                }
            }
        }
        return array;
    }
}
