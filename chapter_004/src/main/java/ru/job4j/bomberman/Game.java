package ru.job4j.bomberman;

import java.util.ArrayList;

public class Game {
    Board board;

    ArrayList<Personage> personages;

    Game(int width, int height) {
        board = new Board(width, height);
        personages = new ArrayList<>();
    }

    /**
     * The method creating personage (monster or hero);
     */
    public void createPersonage(String name, int x, int y) {
        personages.add(new Personage(board, name, x, y));
    }

    /**
     * Creating lock cell
     */
    public void createBlockCell(int x, int y) {
        this.board.cellLock(x, y);
        System.out.println(String.format("Cell is locking x%s, y%s", x, y));
    }

    public void startGame() {
        for (Personage personage : personages) {
            new Thread(personage, personage.getNamePersonage()).start();
        }
    }
}