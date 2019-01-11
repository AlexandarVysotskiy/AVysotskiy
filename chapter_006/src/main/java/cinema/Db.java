package cinema;

import java.util.List;

public interface Db {
    Account addNewAccount(Account account);

    List<Place> getPlace();
}
