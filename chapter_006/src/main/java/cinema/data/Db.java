package cinema.data;

import cinema.models.Account;

import java.util.List;

public interface Db {
    Account addNewAccount(Account account);

    List<Account> getAccounts();
}
