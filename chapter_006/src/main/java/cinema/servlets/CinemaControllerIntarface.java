package cinema.servlets;

import cinema.models.Account;

import java.util.List;

public interface CinemaControllerIntarface {
    boolean addAccount(Account account);

    List<Account> getAccounts();
}
