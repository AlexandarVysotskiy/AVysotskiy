package ru.job4j.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {

    /**
     * Метод считает кол-во изменений в коллекции.
     * <p>
     * Метод работает следующим образом:
     * Если кол-во ключей из прошлой коллекции больше чем у новой растет счетчик добавления элементов.
     * Если кол-во ключей из прошлой коллекции меньше чем у новой растет счетчик удаления элементов.
     * Если ключи не эквивалентные то растет счетчик изменений.
     *
     * @param previous - прежняя коллекция.
     * @param current  - новоя коллекция.
     * @return карту с счетчиками.
     */
    public HashMap<String, Integer> isChange(List<User> previous, List<User> current) {
        int newAdd = 0;
        int newChange = 0;
        int newDelete = 0;

        Map<Integer, String> mapOfPrevious = new HashMap<>();
        Map<Integer, String> mapOfCurrent = new HashMap<>();
        HashMap<String, Integer> result = new HashMap<>();

        for (User indexPrevious : previous) {
            mapOfPrevious.put(indexPrevious.getId(), indexPrevious.getName());
        }

        for (User indexCurrent : current) {
            mapOfCurrent.put(indexCurrent.getId(), indexCurrent.getName());
        }

        for (Integer key : mapOfPrevious.keySet()) {
            if (!mapOfCurrent.containsKey(key)) {
                newDelete++;
            } else if (!mapOfCurrent.get(key).equals(mapOfPrevious.get(key))) {
                newChange++;
            }
        }

        for (Integer key : mapOfCurrent.keySet()) {
            if (!mapOfPrevious.containsKey(key)) {
                newAdd++;
            }
        }

        result.put("Amount new add: ", newAdd);
        result.put("Amount new change: ", newChange);
        result.put("Amount new delete: ", newDelete);

        return result;
    }

    static class User {
        private int id;

        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }
    }
}