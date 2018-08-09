package ru.job4j.bank;

import java.util.Objects;

public class Account {

    private double value;

    private String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object instanceof Account) {
            Account account = (Account) object;
            result = this.requisites.equals(account.requisites);
        }
        return result;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, requisites);
    }
}