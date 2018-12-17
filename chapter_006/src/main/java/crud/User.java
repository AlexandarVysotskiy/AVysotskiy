package crud;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private String name;
    private String login;
    private String email;
    private LocalDate createDate;

    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }
}