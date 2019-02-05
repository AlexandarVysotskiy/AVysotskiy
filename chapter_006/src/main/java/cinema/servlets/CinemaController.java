package cinema.servlets;

import cinema.data.CinemaDB;
import cinema.data.Db;
import cinema.models.Account;
import cinema.models.Place;

import java.util.List;

public class CinemaController implements CinemaControllerIntarface {

    private static final CinemaController INSTANCE = new CinemaController();

    private CinemaController() {
    }

    public static CinemaController getInstance() {
        return INSTANCE;
    }

    private static final Db STORAGE = CinemaDB.getInstance();

    public boolean addAccount(Account account) {
        STORAGE.addNewAccount(account);
        return true;
    }

    public List<Place> getPlace() {
        return STORAGE.getPlace();
    }
}
