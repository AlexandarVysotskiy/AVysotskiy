package crud;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet("/UserCreateServlet")
public class UserCreateServlet extends HttpServlet {
    private final Validate storage = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try {
            storage.add(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email")));
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } catch (UserError u) {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append("Email isn't correct or user with this login has already");
            writer.append("<br><a href=" + req.getContextPath() + "/CreateNewUser.jsp>Try again</a></br>");
            u.printStackTrace();
            writer.flush();
        }
    }
}