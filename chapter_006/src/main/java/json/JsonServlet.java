package json;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

public class JsonServlet extends HttpServlet {

    private final Map<String, Person> storage = new ConcurrentHashMap<>();

    private final AtomicInteger cont = new AtomicInteger(1);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pr = resp.getWriter();
        pr.append(new ObjectMapper().writeValueAsString(this.storage));
        pr.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }
        reader.close();
        Person person = new Gson().fromJson(sb.toString(), Person.class);
        String id = String.valueOf(cont.getAndIncrement());
        storage.put(id, person);
        person.setId(id);
        PrintWriter writer = resp.getWriter();
        writer.append(new ObjectMapper().writeValueAsString(person));
        writer.flush();
    }
}