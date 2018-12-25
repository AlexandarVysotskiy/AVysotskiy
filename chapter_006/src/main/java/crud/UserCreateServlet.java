package crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        resp.setContentType("text/html");
        try {
            storage.add(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"),
                    req.getParameter("password"),
                    Role.valueOf(req.getParameter("role"))));
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (UserError u) {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append("Email isn't correct or user with this login has already");
            writer.append("<br><a href=" + req.getContextPath() + "/UserCreateServlet>Try again</a></br>");
            u.printStackTrace();
            writer.flush();
        }
    }
}