package crud;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Класс содержит заполненную форму для редактирования пользовтеля.
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
    private Validate storage = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        int id = Integer.valueOf(req.getParameter("id"));
        try {
            storage.update(id, user);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } catch (UserError u) {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append("This login is exist already");
            writer.append("<br><a href=" + req.getContextPath() + "/UpdateUser.jsp?id=" + id + ">Try again</a></br>");
            writer.append("<br><a href=" + req.getContextPath() + "/index.jsp>Show list of users</a></br>");
            u.printStackTrace();
            writer.flush();
            u.printStackTrace();
        }
    }
}