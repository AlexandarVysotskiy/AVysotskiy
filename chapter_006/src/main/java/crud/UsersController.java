package crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс открывает таблицу со всеми пользователями;
 */
public class UsersController extends HttpServlet {

    private final Validate storage = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (!storage.findAll().isEmpty()) {
                req.setAttribute("users", storage.findAll());
            }
        } catch (UserError u) {
            u.printStackTrace();
        } finally {
            req.getRequestDispatcher("/WEB-INF/views/listOfUser.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = (req.getParameter("login"));
        int id = storage.getId(login);
        storage.delete(id);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}