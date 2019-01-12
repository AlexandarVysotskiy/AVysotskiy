package cinema;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AccountServlet extends HttpServlet {

    private final CinemaControllerIntarface storage = CinemaController.getInstance();

    private String place;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p = req.getParameter("place");
        if (p != null) {
            this.place = p;
            resp.sendRedirect("/chapter_006/payment.html");
        }
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pr = resp.getWriter();
        StringBuilder sb = new StringBuilder();
        sb.append("Вы выбрали ряд ").append(place.charAt(0)).append(" место ").append(place.charAt(1)).append(", Сумма : 2$");
        pr.append(new ObjectMapper().writeValueAsString(sb));
        pr.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isFree = true;
        if (place != null) {
            if (!storage.getPlace().isEmpty()) {
                for (Place p : storage.getPlace()) {
                    if ((String.valueOf(place.charAt(1))).equals(p.getColumn()) & (String.valueOf(place.charAt(0))).equals(p.getRow())) {
                        System.out.println("error");
                        PrintWriter writer = resp.getWriter();
                        String error = "Place isn't free, please select other place!";
                        writer.append(new ObjectMapper().writeValueAsString(error));
                        writer.flush();
                        isFree = false;
                        break;
                    }
                }
            }
            if (isFree) {
                resp.setContentType("text/json");
                resp.setCharacterEncoding("UTF-8");
                BufferedReader reader = req.getReader();
                StringBuilder sb = new StringBuilder();
                String s;
                while ((s = reader.readLine()) != null) {
                    sb.append(s);
                }
                reader.close();
                Account account = new Gson().fromJson(sb.toString(), Account.class);
                account.setPlace(new Place(String.valueOf(place.charAt(0)), String.valueOf(place.charAt(1))));
                storage.addAccount(account);
                System.out.println("success");
                PrintWriter writer = resp.getWriter();
                String success = "You ticket has added!";
                writer.append(new ObjectMapper().writeValueAsString(success));
                writer.flush();
            }
        }
    }
}