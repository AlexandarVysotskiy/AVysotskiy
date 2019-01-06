package crud;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    @JsonProperty("name")
    private String name;
    @JsonProperty("login")
    private String login;
    @JsonProperty("email")
    private String email;
    @JsonProperty("createDate")
    private LocalDate createDate;
    @JsonProperty("password")
    private String password;
    @JsonProperty("role")
    private Role role;
    @JsonProperty("country")
    private String country;
    @JsonProperty("city")
    private String city;

    private int id;

    public User(String name, String login, String email, String password, String role, String city, String country) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = LocalDate.now();
        this.password = password;
        this.role = Role.valueOf(role);
        this.city = city;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}