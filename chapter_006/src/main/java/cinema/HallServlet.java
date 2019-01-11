package cinema;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;


public class HallServlet extends HttpServlet {

    private final CinemaControllerIntarface storage = CinemaController.getInstance();

    private final AtomicInteger cont = new AtomicInteger(1);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pr = resp.getWriter();
        pr.append(new ObjectMapper().writeValueAsString(this.storage.getPlace()));
        pr.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }
}