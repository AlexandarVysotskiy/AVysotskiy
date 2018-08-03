package ru.job4j.anagram;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
    /**
     * Метод работает следующим образом, если в хранилище уже есть ключ эквиваентный символу в слове, то его значение перезаписывается на ноль.
     *
     * @return true если все значения в репозитории 0.
     */
    public boolean checkAnagram(String first, String second) {
        boolean result = true;
        if (first == null || second == null) {
            result = false;
        }
        if (first.length() != second.length()) {
            result = false;
        }

        Map<Character, Integer> repository = new HashMap<>();

        for (int i = 0; i < first.length(); i++) {
            char charOfFirst = first.charAt(i);
            int valuesFromCharOfFirst = repository.containsKey(charOfFirst) ? repository.get(charOfFirst) : 0;
            repository.put(charOfFirst, ++valuesFromCharOfFirst);
            char charOfSecond = second.charAt(i);
            int valuesFromCharOfSecond = repository.containsKey(charOfSecond) ? repository.get(charOfSecond) : 0;
            repository.put(charOfSecond, --valuesFromCharOfSecond);
        }

        for (int index : repository.values()) {
            if (index != 0) {
                result = false;
            }
        }
        return result;
    }
}