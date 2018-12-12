package crud;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Класс содержит методы для проверки данных
 */
public class ValidateService implements Validate {

    private static ValidateService instance = new ValidateService();

    private ValidateService(){};

    public static Validate getInstance(){
        return instance;
    }

    private static Store storage = MemoryStore.getInstance();

    /**
     * Проверяет существует ли уже такой пользователь и корректный ли email.
     *
     * @return true если все данные корректные.
     */
    @Override
    public boolean add(User user) {
        boolean result = false;
        if (userIsNotExist(user) && validateEmail(user.getEmail())) {
            storage.add(user);
            result = true;
        }
        return result;
    }

    /**
     * Проверяет существует ли заменяющий пользователь.
     *
     * @return true если не существует.
     */
    @Override
    public boolean update(User user) {
        boolean result = false;
        if (userIsNotExist(user)) {
            storage.update(user);
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
    public boolean delete(UUID id) {
        boolean result = false;
        if (userIsNotExist(storage.findById(id))) {
            storage.delete(id);
            result = true;
        }
        return result;
    }

    /**
     * @return Возращает список всех пользователей если хранилище не пустое.
     */
    @Override
    public List<User> findAll() {
        if (storage.findAll().isEmpty()) {
            throw new UserError("In this storage isn't users");
        }
        return storage.findAll();
    }


    /**
     * @return Возвращает пользователя по id если он существует.
     */
    @Override
    public User findById(UUID id) {
        User result = null;
        if (userIsNotExist(storage.findById(id))) {
            result = storage.findById(id);
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
     * @return true если есть.
     */
    private boolean userIsNotExist(User user) {
        boolean result = true;
        if (storage.findById(user.getId()) == null) {
            result = false;
        } else {
            throw new UserError("User isn't exist");
        }
        return result;
    }
}
