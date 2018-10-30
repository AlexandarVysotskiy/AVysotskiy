package ru.job4j.bomberman;

import org.junit.Test;

public class GameTest {
    @Test
    public void gameTest() {
        Game game = new Game(10, 9);
        game.createPersonage("Hero", 1, 1);
        game.createPersonage("Monster 1", 8, 5);
        game.createPersonage("Monster 2", 1, 5);
        game.createBlockCell(2, 1);
        game.createBlockCell(3, 5);
        game.startGame();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}