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

    //    private final Map<String, Account> storage = new ConcurrentHashMap<>();
    private final CinemaControllerIntarface storage = CinemaController.getInstance();

//    private final AtomicInteger cont = new AtomicInteger(1);

    String place;

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
        sb.append("ряд ").append(place.charAt(0)).append(" место ").append(place.charAt(1));
        pr.append(new ObjectMapper().writeValueAsString(sb));
        pr.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    }
}
