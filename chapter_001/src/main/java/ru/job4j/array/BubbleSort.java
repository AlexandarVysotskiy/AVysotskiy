package ru.job4j.array;

public class BubbleSort {
    /**
     * Класс служит для сортировки массива используя алгоритм сортировки пузырьком
     *
     * @param inputArray - сортируемый массив
     * @return - возвращает отсортированный массив
     * @author Alexandar Vysotskiy
     * @version 1.0
     */
    public int[] sort(int[] inputArray) {
        int[] result = inputArray;
        for (int i = result.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (result[j] > result[j + 1]) {
                    int temp = result[j + 1];
                    result[j + 1] = result[j];
                    result[j] = temp;
                }
            }
        }
        return result;
    }
}