package crud;

import java.util.List;


public interface Validate {
    boolean add(User user);

    boolean update(int Id, User user);

    boolean delete(Integer id);

    List<User> findAll();

    User findById(Integer id);
}