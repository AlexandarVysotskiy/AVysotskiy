package ru.job4j.blocking;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCash {

    private ConcurrentHashMap<Integer, Model> storage = new ConcurrentHashMap<>();

    public void add(Integer key, Model model) {
        storage.putIfAbsent(key, model);
    }

    public void update(Integer key, Model model) {
        this.storage.computeIfPresent(key, (k, v) -> {
            if (v.getVersion() != model.getVersion()) {
                throw new OptimisticException("OptimisticException");
            }
            return model;
        });
    }

    public void delete(Integer key) {
        this.storage.remove(key);
    }

    public Model get(Integer key) throws CloneNotSupportedException {
        return this.storage.get(key).clone();
    }
}
