package crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilterServlet extends HttpServlet {
    private final Validate storage = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/authFilter.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Role role = storage.getRole(login, password);
        String enter = req.getParameter("enterHowGuest");
        if (role != null) {
            HttpSession session = req.getSession();
            session.setAttribute("role", role);
            session.setAttribute("login", login);
            resp.sendRedirect(req.getContextPath() + "/controller");
        } else if (enter != null) {
            HttpSession session = req.getSession();
            session.setAttribute("role", Role.guest);
            resp.sendRedirect(req.getContextPath() + "/controller");
        } else {
            req.setAttribute("error", "User isn't exist");
            doGet(req, resp);
        }
    }
}
