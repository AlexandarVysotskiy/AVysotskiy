package ru.job4j.problem;

public class Fueling {
    private int liter;

    public Fueling(int liter) {
        this.liter = liter;
    }

    public void add() {
        this.liter++;
    }

    public int getLiter() {
        return this.liter;
    }
}