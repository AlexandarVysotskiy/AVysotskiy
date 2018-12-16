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
 *
 * Класс открывает форму для создания нового пользователя.
 */
@WebServlet("/UserCreateServlet")
public class UserCreateServlet extends HttpServlet {
    private Validate storage = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder("<table>");
        try {
            for (User login : storage.findAll()) {
                sb.append("<tr><td>" + "User: " + login.getLogin() + " has add " + "</td></tr>");
            }
        } catch (UserError u) {
            writer.append("User list is empty");
            u.printStackTrace();
        }

        sb.append("</table>");
        writer.append(
                "<!DOCTYPE html>" +
                        "<html lang=\"en\">" +
                        "<head>" +
                        "    <meta charset=\"UTF-8\">" +
                        "    <title>Add a new user</title>" +
                        "</head>" +
                        "<body>" +
                        "<form action = ' " + req.getContextPath() + "/UserCreateServlet' method='post'>" +
                        "Name : <input type = 'text' name='name'/>" +
                        "Login : <input type = 'text' name='login'/>" +
                        "Email : <input type = 'text' name='email'/>" +
                        "<input type = 'submit'>" +
                        "</form>" +
                        "<br/>" +
                        "<a href=" + req.getContextPath() + "/UserServlet>User list</a>" +
                        sb.toString() +
                        "</body>" +
                        "</html>");
        writer.flush();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try {
            storage.add(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email")));
        }catch (UserError u){
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append("Email isn't correct");
            u.printStackTrace();
            writer.flush();
        }finally {
            doGet(req, resp);
        }
    }
}