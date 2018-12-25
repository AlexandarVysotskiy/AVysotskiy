package crud;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private String name;
    private String login;
    private String email;
    private LocalDate createDate;
    private String password;
    private Role role;
    private int id;

    public User(String name, String login, String email, String password, Role role) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = LocalDate.now();
        this.password = password;
        this.role = role;
    }

    public User(int id, String name, String login, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = LocalDate.now();
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}