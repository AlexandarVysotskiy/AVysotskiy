package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private ArrayList<User> storage = new ArrayList();

    public synchronized ArrayList<User> getStorage() {
        return this.storage;
    }

    public synchronized boolean add(User user) {
        return storage.add(user);
    }

    public synchronized boolean delete(User user) {
        return storage.remove(user);
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        int count = 0;
        for (User index : storage) {
            if (index != null && index.getId() == user.getId()) {
                storage.set(count, user);
                result = true;
                break;
            }
            count++;
        }
        return result;
    }

    /**
     * Вспомогательный метод исчет пользователя по Id.
     */
    private User findById(int id) {
        User result = null;
        for (User index : storage) {
            if (index.getId() == id) {
                result = index;
                break;
            }
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (findById(fromId) == null && findById(toId) == null) {
            System.out.println("Несуществующий акаунт");
        } else if (findById(fromId).getAmount() < amount) {
            System.out.println("Недостаточно средств для перевода");
        } else {
            update(new User(fromId, findById(fromId).getAmount() - amount));
            update(new User(toId, findById(toId).getAmount() + amount));
            result = true;
        }
        return result;
    }
}
