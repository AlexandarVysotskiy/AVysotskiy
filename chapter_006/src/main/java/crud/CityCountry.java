package crud;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityCountry {
    @JsonProperty("country")
    private String country;

    @JsonProperty("city")
    private String city;

    public CityCountry(String country, String city) {
        this.country = country;
        this.city = city;
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
