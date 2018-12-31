package crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Класс содержит заполненную форму для редактирования пользовтеля.
 */
public class UserUpdateServlet extends HttpServlet {
    private final Validate storage = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userId = req.getParameter("id");
        User user = storage.findById(Integer.valueOf(userId));
        req.setAttribute("loginUpdate", user.getLogin());
        req.setAttribute("nameUpdate", user.getName());
        req.setAttribute("emailUpdate", user.getEmail());
        req.setAttribute("passwordUpdate", user.getPassword());
        req.setAttribute("role", session.getAttribute("role"));
        req.setAttribute("id", userId);
        req.getRequestDispatcher("/WEB-INF/views/UpdateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"),
                req.getParameter("password"), Role.valueOf(req.getParameter("role")));
        int id = Integer.valueOf(req.getParameter("id"));
        try {
            storage.update(id, user);
            resp.sendRedirect(req.getContextPath() + "/controller");
        } catch (UserError u) {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append("This login is exist already");
            writer.append("<br><a href=" + req.getContextPath() + "/UpdateUser.jsp?id=" + id + ">Try again</a></br>");
            writer.append("<br><a href=" + req.getContextPath() + "/>Show list of users</a></br>");
            resp.sendRedirect(req.getContextPath() + "/controller");
            u.printStackTrace();
            writer.flush();
            u.printStackTrace();
        }
    }
}