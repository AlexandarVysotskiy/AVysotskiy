package ru.job4j.blocking;

public class Model {
    private String name;
    private String value;
    private int version;

    public Model(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public int getVersion() {
        return this.version;
    }

    public void change(String name) {
        this.name = name;
        this.version++;
    }
}
