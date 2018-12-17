package crud;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс содержит методы для оперирования над пользователями.
 */
public class MemoryStore implements Store {
    private int id = 0;

    private ConcurrentHashMap<Integer, User> store = new ConcurrentHashMap<>();

    private static MemoryStore instance = new MemoryStore();

    public static MemoryStore getInstance() {
        return instance;
    }

    @Override
    public void add(User user) {
        store.put(id++, user);
    }

    @Override
    public int getId(User user) {
        int result = 0;
        for (int key : store.keySet()) {
            if (store.get(key).equals(user)) {
                result = key;
            }
        }
        return result;
    }

    @Override
    public void update(int id, User user) {
        store.replace(id, user);
    }

    @Override
    public void delete(Integer id) {
        store.remove(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public User findById(Integer id) {
        return store.get(id);
    }
}