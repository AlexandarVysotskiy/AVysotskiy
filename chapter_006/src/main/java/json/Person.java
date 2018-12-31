package json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("description")
    private String description;

    @JsonProperty("sex")
    private String sex;

    public Person(String id, String name, String surname, String desk, String sex) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.description = desk;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDescription() {
        return description;
    }

    public String getSex() {
        return sex;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
