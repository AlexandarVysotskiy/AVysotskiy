package ru.job4j.store;

import java.util.ArrayList;
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
        int beforeSize = previous.size();
        int afterSize = current.size();

        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.addAll(previous);
        allUsers.addAll(current);

        HashMap<Integer, String> newList = new HashMap<>();
        for (User index : allUsers) {
            newList.put(index.getId(), index.getName());
        }

        int newListSize = newList.size();
        HashMap<String, Integer> result = new HashMap<>();


        result.put("Amount new add: ", newListSize - beforeSize);
        result.put("Amount new change: ", afterSize - (newListSize - beforeSize));
        result.put("Amount new delete: ", newListSize - (newListSize - beforeSize) - (afterSize - (newListSize - beforeSize)));

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