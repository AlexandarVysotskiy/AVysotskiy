package cinema.servlets;

import cinema.data.CinemaDBThroughHibernate;
import cinema.data.Db;
import cinema.models.Account;

import java.util.List;

public class CinemaController implements CinemaControllerIntarface {

    private static final CinemaController INSTANCE = new CinemaController();

    private CinemaController() {
    }

    public static CinemaController getInstance() {
        return INSTANCE;
    }

    private static final Db STORAGE = CinemaDBThroughHibernate.getInstance();

    public boolean addAccount(Account account) {
        STORAGE.addNewAccount(account);
        return true;
    }

    public List<Account> getAccounts() {
        return STORAGE.getAccounts();
    }
}
