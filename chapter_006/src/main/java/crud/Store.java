package crud;

import java.util.List;
import java.util.UUID;

public interface Store {
    void add (User user);

    void update(User user);

    void delete(UUID id);

    List<User> findAll();

    User findById(UUID id);
}
