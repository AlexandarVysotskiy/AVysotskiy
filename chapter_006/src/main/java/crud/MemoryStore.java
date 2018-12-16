package crud;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс содержит методы для оперирования над пользователями.
 */
public class MemoryStore implements Store {
    private ConcurrentHashMap<Integer, User> store = new ConcurrentHashMap<>();

    private static MemoryStore instance = new MemoryStore();

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return instance;
    }

    @Override
    public void add(User user) {
        Integer id = user.getId();
        store.put(id, user);
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