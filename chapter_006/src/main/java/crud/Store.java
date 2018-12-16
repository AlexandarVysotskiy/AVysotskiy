package crud;

import java.util.List;

public interface Store {
    void add (User user);

    void update(int Id, User user);

    void delete(Integer id);

    List<User> findAll();

    User findById(Integer id);
}