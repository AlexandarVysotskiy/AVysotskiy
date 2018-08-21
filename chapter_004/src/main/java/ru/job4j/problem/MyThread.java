package ru.job4j.problem;

public class MyThread extends Thread {
    private Fueling fueling;

    public MyThread(Fueling fueling) {
        this.fueling = fueling;
    }

    @Override
    public void run() {
        fueling.add();
    }
}