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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Integer id = Integer.valueOf(req.getParameter("id"));
        storage.delete(id);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}