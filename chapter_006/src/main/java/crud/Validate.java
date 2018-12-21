package crud;

import java.util.List;


public interface Validate {
    boolean add(User user);

    int getId(User user);

    boolean update(int id, User user);

    boolean delete(Integer id);

    List<User> findAll();

    User findById(Integer id);
}