package ru.job4j.problem;

public class Main {
    public static void main(String[] args) {
        Fueling fueling = new Fueling(100);
        MyThread one = new MyThread(fueling);
        MyThread two = new MyThread(fueling);
        MyThread three = new MyThread(fueling);

        one.start();
        two.start();
        three.start();

        System.out.println(fueling.getLiter());
    }
}