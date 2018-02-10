package ru.job4j.array;

public class WordInWord {
    boolean contains(String origin, String sub) {
        boolean result = false;
        char[] in = origin.toCharArray();
        char[] out = sub.toCharArray();
        for (int i = 0; i <= in.length - out.length && !result; i++) {
            if (in[i] == out[0]) {
                int a = 1;
                while (a < out.length && in[i + a] == out[a]) {
                    a++;
                }
                result = a == out.length;
            }
        }
        return result;
    }
}