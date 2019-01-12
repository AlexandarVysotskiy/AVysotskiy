package crud;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Задание: реализовать веб приложение для управления пользователями.
 * <p>
 * Класс открывает форму для создания нового пользователя.
 */
public class UserCreateServlet extends HttpServlet {
    private final Validate storage = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (!storage.getAll().isEmpty()) {
                req.setAttribute("users", storage.getAll());
            }
        } catch (UserError u) {
            u.printStackTrace();
        } finally {
            req.getRequestDispatcher("/WEB-INF/views/CreateNewUser.jsp").forward(req, resp);
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = reader.readLine()) != null) {
            System.out.println(s);
            sb.append(s);
        }
        reader.close();
        User user = new Gson().fromJson(sb.toString(), User.class);
        System.out.println(user.getLogin());
        System.out.println(user.getCity());
        System.out.println(user.getCountry());
        try {
            resp.setContentType("text/html");
            storage.add(user);
            resp.sendRedirect(req.getContextPath() + "/controller");
        } catch (UserError u) {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append("Email isn't correct or user with this login has already");
            writer.append("<br><a href=" + req.getContextPath() + "/UserCreateServlet>Try again</a></br>");
            u.printStackTrace();
            writer.flush();
        }
    }
}