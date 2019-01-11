package cinema;

import java.util.List;

public class CinemaController implements CinemaControllerIntarface {

    private static final CinemaController INSTANCE = new CinemaController();

    private CinemaController() {
    }

    public static CinemaController getInstance() {
        return INSTANCE;
    }

    private static final Db STORAGE = CinemaDB.getInstance();

    public void addAccount(Account account) {
        STORAGE.addNewAccount(account);
    }

    public List<Place> getPlace() {
        return STORAGE.getPlace();
    }
}
