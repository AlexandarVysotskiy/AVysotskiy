package crud;

import net.jcip.annotations.GuardedBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс содержит методы для оперирования над пользователями.
 */
public class MemoryStore implements Store {

    @GuardedBy("this")
    private AtomicInteger id = new AtomicInteger(0);

    private ConcurrentHashMap<Integer, User> store = new ConcurrentHashMap<>();

    private static MemoryStore instance = new MemoryStore();

    public static MemoryStore getInstance() {
        return instance;
    }

    @Override
    public User add(User user) {
        store.put(id.incrementAndGet(), user);
        return user;
    }

    /**
     * This method is deprecate
     * @param login
     * @return
     */
    @Override
    public int getId(String login) {
        int result = 0;
//        for (int key : store.keySet()) {
//            if (store.get(key).equals(user)) {
//                result = key;
//            }
//        }
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