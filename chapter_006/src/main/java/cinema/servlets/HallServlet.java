package cinema.servlets;

import cinema.models.Account;
import cinema.models.Place;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class HallServlet extends HttpServlet {

    private final CinemaControllerIntarface storage = CinemaController.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Place> listOfPlaces = new ArrayList<>();
        for (Account a : storage.getAccounts()) {
            listOfPlaces.add(new Place(a.getRow(), a.getBlockcolumn()));
        }
        PrintWriter pr = resp.getWriter();
        pr.append(new ObjectMapper().writeValueAsString(listOfPlaces));
        pr.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }
}