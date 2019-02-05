package cinema.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Account {

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("fullName")
    private String fullName;


    private Place place;

    public Account(String phone, String fullName, Place place) {
        this.phone = phone;
        this.fullName = fullName;
        this.place = place;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return phone.equals(account.phone)
                && fullName.equals(account.fullName)
                && place.equals(account.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, fullName, place);
    }
}