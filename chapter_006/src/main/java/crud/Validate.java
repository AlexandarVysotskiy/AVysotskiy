package crud;

import java.util.List;


public interface Validate {
    User add(User user);

    int getId(String login);

    boolean update(int id, User user);

    boolean delete(Integer id);

    List<User> getAll();

    User findById(Integer id);

    Role getRole(String login, String password);
}