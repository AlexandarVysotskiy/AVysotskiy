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
    private volatile Integer id = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder("<table>");
        this.id = Integer.valueOf(req.getParameter("id"));
        User user = storage.findById(id);
        sb.append("<tr><td>" + "Parameter's  user: " + user.getLogin() +
                "<body>" +
                "<form action = ' " + req.getContextPath() + "/UserUpdateServlet' method='post'>" +
                "Name : <input type = 'text' value=" + user.getName() + " name='name'/>" +
                "Login : <input type = 'text' value=" + user.getLogin() + " name='login'/>" +
                "Email : <input type = 'text' value=" + user.getEmail() + " name='email'/>" +
                "<input type = 'submit'>" +
                "</form>" +
                "</td></tr>");
        sb.append("</table>");
        writer.append(
                "<!DOCTYPE html>" +
                        "<html lang=\"en\">" +
                        "<head>" +
                        "    <meta charset=\"UTF-8\">" +
                        "    <title>Update of user</title>" +
                        "</head>" +
                        "<br/>" +
                        "<a href=" + req.getContextPath() + "/UserServlet>Return to list of user</a>" +
                        sb.toString() +
                        "</body>" +
                        "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        user.setId(id);
        storage.update(id, user);
        resp.sendRedirect(req.getContextPath() + "/UserServlet");
    }
}