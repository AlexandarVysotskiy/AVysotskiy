package ru.job4j.bank;

import java.util.Objects;

public class User {

    private String name;

    private String passport;

    public User() {
    }

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + passport.hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }
}