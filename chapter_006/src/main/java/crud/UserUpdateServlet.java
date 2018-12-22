package crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Класс содержит заполненную форму для редактирования пользовтеля.
 */
public class UserUpdateServlet extends HttpServlet {
    private final Validate storage = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = storage.getId(req.getParameter("login"));
        User user = storage.findById(userId);
        req.setAttribute("loginUpdate", user.getLogin());
        req.setAttribute("nameUpdate", user.getName());
        req.setAttribute("emailUpdate", user.getEmail());
        req.setAttribute("id", userId);
        req.getRequestDispatcher("/WEB-INF/views/UpdateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        int id = Integer.valueOf(req.getParameter("id"));
        try {
            storage.update(id, user);
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (UserError u) {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append("This login is exist already");
            writer.append("<br><a href=" + req.getContextPath() + "/UpdateUser.jsp?id=" + id + ">Try again</a></br>");
            writer.append("<br><a href=" + req.getContextPath() + "/>Show list of users</a></br>");
            resp.sendRedirect(req.getContextPath() + "/");
            u.printStackTrace();
            writer.flush();
            u.printStackTrace();
        }
    }
}