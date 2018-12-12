package crud;

import java.time.LocalDate;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String login;
    private String email;
    private LocalDate createDate;

    public User(String name, String login, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = LocalDate.now();
    }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }
}
