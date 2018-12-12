package crud;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet("/crud")
public class UserServlet extends HttpServlet {

    private final Validate storage = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(storage.findAll().toString());
            writer.flush();
        } catch (UserError e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String id = req.getParameter("id");
        if (action != null) {
            switch (action) {
                case "add":
                    storage.add(new User(name, login, email));
                    break;
                case "update":
                    storage.update(new User(name, login, email));
                    break;
                case "delete":
                    storage.delete(UUID.fromString(req.getParameter(id)));
                    break;
                case "all":
                    storage.findAll();
                    break;
                case "find":
                    storage.findById(UUID.fromString(req.getParameter(id)));
                    break;
            }
        }
    }
}