package cinema.servlets;

import cinema.models.Account;
import cinema.models.Place;

import java.util.List;

public interface CinemaControllerIntarface {
    boolean addAccount(Account account);

    List<Place> getPlace();
}
