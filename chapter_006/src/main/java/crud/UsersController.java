package crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Класс открывает таблицу со всеми пользователями;
 */
public class UsersController extends HttpServlet {

    private final Validate storage = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
            try {
                if (!storage.getAll().isEmpty()) {
                    req.setAttribute("users", storage.getAll());
                    req.setAttribute("login", session.getAttribute("login"));
                    req.setAttribute("role", session.getAttribute("role"));
                    req.getRequestDispatcher("/WEB-INF/views/listOfUser.jsp").forward(req, resp);
                }
            } catch (UserError u) {
                u.printStackTrace();
            }
            finally {
                req.getRequestDispatcher("/WEB-INF/views/listOfUser.jsp").forward(req, resp);
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String exist = req.getParameter("exist");
        String id = (req.getParameter("id"));
        if (exist != null) {
            session.invalidate();
        } else if(id != null) {
            storage.delete(Integer.valueOf(id));
        }
        resp.setContentType("text/html");
        resp.sendRedirect(req.getContextPath() + "/");
    }
}