package crud;

import java.util.List;
import java.util.UUID;


public interface Validate {
    boolean add(User user);

    boolean update(User user);

    boolean delete(UUID id);

    List<User> findAll();

    User findById(UUID id);
}