package ru.job4j.store;

import java.util.HashMap;
import java.util.List;

public class Store {

    /**
     * Метод считает кол-во изменений в коллекции.
     *
     * @param previous - прежняя коллекция.
     * @param current  - новоя коллекция.
     * @return карту с счетчиками.
     */
    public HashMap<String, Integer> isChange(List<User> previous, List<User> current) {
        int deleteCount = 0;
        int changeCount = 0;
        int beforeSize = previous.size();

        HashMap<Integer, String> allUsers = new HashMap<>();

        for (User index : current) {
            allUsers.put(index.getId(), index.getName());
        }

        for (User index : previous) {
            if (!allUsers.containsKey(index.getId())) {
                deleteCount++;
            } else if (!allUsers.get(index.getId()).equals(index.getName())) {
                changeCount++;
            }
            allUsers.put(index.getId(), index.getName());
        }

        int newListSize = allUsers.size();
        HashMap<String, Integer> result = new HashMap<>();


        result.put("Amount new add: ", newListSize - beforeSize);
        result.put("Amount new change: ", changeCount);
        result.put("Amount new delete: ", deleteCount);

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