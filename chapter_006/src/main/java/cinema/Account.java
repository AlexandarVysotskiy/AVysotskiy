package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

    @JsonProperty("phone")
    String phone;

    @JsonProperty("fullName")
    String fullName;


    Place place;

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
}
