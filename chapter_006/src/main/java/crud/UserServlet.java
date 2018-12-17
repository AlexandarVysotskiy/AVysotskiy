package crud;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Класс открывает таблицу со всеми пользователями;
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    private final Validate storage = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder("<table>");
        try {
            sb.append("<br>List of users</br>");
            for (User user : storage.findAll()) {

                sb.append("<tr><td>" + "User login: " + user.getLogin() + "</td>" +
                        "<body>" +
                        "<td>" +
                        "<form action=' " + req.getContextPath() + "/UserServlet' method='post'>" +
                        "<p><button name='id' type  = 'hidden' value=" + storage.getId(user) + ">Delete</button></p>" +
                        "</form>" +
                        "<td/>" +
                        "<td>" +
                        "<form action=" + req.getContextPath() + "/UserUpdateServlet/?id=" + storage.getId(user) + ">" +
                        "<a href=" + req.getContextPath() + "/UserUpdateServlet?id=" + storage.getId(user) + ">Update</a>" +
                        "<td/>" +
                        "</form>" +
                        "</tr>");
            }
            sb.append("</table>");

        } catch (UserError u) {
            writer.append("<br>User list is empty</br>");
            u.printStackTrace();
        } finally {
            writer.append(
                    "<!DOCTYPE html>" +
                            "<html lang=\"en\">" +
                            "<head>" +
                            "    <meta charset=\"UTF-8\">" +
                            "    <title>List of users</title>" +
                            "</head>" +
                            "<br/>" +
                            "<a href=" + req.getContextPath() + "/UserCreateServlet>Create new user</a>" +
                            sb.toString() +
                            "</body>" +
                            "</html>");
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Integer id = Integer.valueOf(req.getParameter("id"));
        storage.delete(id);
        doGet(req, resp);
    }
}