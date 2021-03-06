package cinema.servlets;

import cinema.models.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AccountServlet extends HttpServlet {

    private final CinemaControllerIntarface storage = CinemaController.getInstance();

    private static Logger log = Logger.getLogger(AccountServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p = req.getParameter("place");
        if (p != null) {
            synchronized (getServletContext()) {
                getServletContext().setAttribute("place", p);
            }
            resp.sendRedirect("/chapter_006/payment.html");
        }
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pr = resp.getWriter();
        StringBuilder sb = new StringBuilder();
        sb.append("Вы выбрали ряд ").append(p.charAt(0)).append(" место ").append(p.charAt(1)).append(", Сумма : 2$");
        log.info(sb.toString());
        pr.append(new ObjectMapper().writeValueAsString(sb));
        pr.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isFree = true;
        String pl;
        synchronized (getServletContext()) {
            pl = (String) getServletContext().getAttribute("place");
        }
        if (pl != null) {
            if (!storage.getAccounts().isEmpty()) {
                for (Account a : storage.getAccounts()) {
                    if ((String.valueOf(pl.charAt(1))).equals(a.getBlockcolumn()) & (String.valueOf(pl.charAt(0))).equals(a.getRow())) {
                        log.info("Place isn't free");
                        PrintWriter writer = resp.getWriter();
                        String error = "Place isn't free, please select other place!";
                        log.info(error);
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
                    System.out.println(sb);
                }
                reader.close();
                Account account = new Gson().fromJson(sb.toString(), Account.class);
                account.setBlockcolumn(String.valueOf(pl.charAt(1)));
                account.setRow(String.valueOf(pl.charAt(0)));
                storage.addAccount(account);
                PrintWriter writer = resp.getWriter();
                String success = "You ticket has added!";
                log.info(success);
                writer.append(new ObjectMapper().writeValueAsString(success));
                writer.flush();
            }
        }
    }
}