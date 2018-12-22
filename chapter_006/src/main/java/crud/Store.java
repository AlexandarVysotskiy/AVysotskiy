package crud;

import java.util.List;

public interface Store {
    User add(User model);

    int getId(String login);

    void update(int id, User user);

    void delete(Integer id);

    List<User> findAll();

    User findById(Integer id);
}