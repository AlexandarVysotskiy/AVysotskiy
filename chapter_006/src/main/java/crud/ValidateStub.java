package crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidateStub implements Validate {
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;

    @Override
    public User add(User user) {
        user.setId(this.ids++);
        this.store.put(user.getId(), user);
        return user;
    }

    @Override
    public int getId(String login) {
        return 0;
    }

    @Override
    public boolean update(int id, User user) {
        boolean result = false;
        if (isUserLoginIsExist(user)) {
            store.replace(id, user);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) {
        boolean result = false;
        if (store.containsKey(id)) {
            store.remove(id);
            result = true;
        }
        return result;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(this.store.values());
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public Role getRole(String login, String password) {
        return null;
    }


    /**
     * Проверяет строку на соотвествие симловов шаблону;
     *
     * @param email - проверяемая строка
     * @return true если соотвествует.
     */
    private boolean validateEmail(String email) {
        boolean result = false;
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (pattern.matcher(email).matches()) {
            result = true;
        } else {
            throw new UserError("Email isn't correct");
        }
        return result;
    }

    /**
     * Метод проверяет существует ли пользователь.
     *
     * @param user - проверяемый пользователь.
     * @return true если нет.
     */
    private boolean userIsNotExist(User user) {
        if (store.isEmpty() && user.getLogin() == null && user.getEmail().equals(null) && user.getName().equals(null)) {
            throw new UserError("User isn't exist");
        }
        return true;
    }

    /**
     * Метод проверяет существует ли логин.
     *
     * @param user - проверяемый логин.
     * @return true если нет.
     */
    private boolean isUserLoginIsExist(User user) {
        boolean result = true;
        if (!user.equals(null) && !store.isEmpty() && store.containsKey(user)) {
            result = false;
            throw new UserError("User login isn't exist");
        }
        return result;
    }
}
