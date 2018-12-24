package crud;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Класс содержит методы для проверки данных
 */
public class ValidateService implements Validate {

    private static final ValidateService INSTANCE = new ValidateService();

    private ValidateService() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    private static final Store STORAGE = DbStore.getInstance();

    /**
     * Проверяет существует ли уже такой пользователь и корректный ли email.
     *
     * @return true если все данные корректные.
     */
    @Override
    public boolean add(User user) {
        boolean result = false;
        if (isUserLoginIsExist(user) && validateEmail(user.getEmail())) {
            STORAGE.add(user);
            result = true;
        }
        return result;
    }

    @Override
    public int getId(String login) {
        int result = STORAGE.getId(login);
        if (STORAGE.findAll().isEmpty() && result == 0) {
            throw new UserError("There isn't user");
        }
        return result;
    }

    /**
     * Проверяет существует ли заменяющий пользователь.
     *
     * @return true если не существует.
     */
    @Override
    public boolean update(int id, User user) {
        boolean result = false;
        if (isUserLoginIsExist(user)) {
            STORAGE.update(id, user);
            result = true;
        }
        return result;
    }

    /**
     * Удаляет если существует удаляемый пользоваетль.
     *
     * @return true если существует.
     */
    @Override
    public boolean delete(Integer id) {
        boolean result = false;
        if (userIsNotExist(STORAGE.findById(id))) {
            STORAGE.delete(id);
            result = true;
        }
        return result;
    }

    /**
     * @return Возращает список всех пользователей если хранилище не пустое.
     */
    @Override
    public List<User> findAll() {
        if (STORAGE.findAll().isEmpty()) {
            throw new UserError("In this storage isn't users");
        }
        return STORAGE.findAll();
    }


    /**
     * @return Возвращает пользователя по id если он существует.
     */
    @Override
    public User findById(Integer id) {
        User result = null;
        if (userIsNotExist(STORAGE.findById(id))) {
            result = STORAGE.findById(id);
        }
        return result;
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
        if (STORAGE.findAll().isEmpty() && user.getLogin() == null && user.getEmail().equals(null) && user.getName().equals(null)) {
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
        if (!user.equals(null) && !STORAGE.findAll().isEmpty() && STORAGE.findAll().iterator().next().getLogin().equals(user.getLogin())) {
            result = false;
            throw new UserError("User login isn't exist");
        }
        return result;
    }

    /**
     * Метод проверяет существует ли логин пользоваетель с таким паролем и логином.
     *
     * @return возвращает роль пользоваетля (user, admin).
     */
    @Override
    public Role getRole(String login, String password) {
        Role result = null;
        for (User user : findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user.getRole();
                break;
            }
        }
        return result;
    }
}