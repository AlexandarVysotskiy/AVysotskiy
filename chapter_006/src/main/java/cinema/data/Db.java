package cinema.data;

import cinema.models.Account;
import cinema.models.Place;

import java.util.List;

public interface Db {
    Account addNewAccount(Account account);

    List<Place> getPlace();
}
