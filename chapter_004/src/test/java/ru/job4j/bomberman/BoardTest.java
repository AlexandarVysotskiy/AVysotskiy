package ru.job4j.bomberman;

import org.junit.Test;

public class BoardTest {
    @Test
    public void startGame() {
        Board board = new Board(10, 10);
        Hero hero = new Hero(board, 1, 1);
        Thread thread = new Thread(hero);
        thread.setName("Hero Thread");
        thread.start();

        try {
            thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        hero.doStop();
    }
}