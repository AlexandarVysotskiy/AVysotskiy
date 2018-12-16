package crud;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class User {
    private Integer id;
    private String name;
    private String login;
    private String email;
    private LocalDate createDate;
    private static Random random = new Random();


    public User(String name, String login, String email) {
        this.id = Math.abs(random.nextInt() + hashCode());
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(createDate, user.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, email, createDate);
    }
}