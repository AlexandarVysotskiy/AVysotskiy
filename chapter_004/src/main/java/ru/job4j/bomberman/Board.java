package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final ReentrantLock[][] board;
    private final int width;
    private final int height;

    Board(int width, int height) {
        this.width = width;
        this.height = height;

        board = new ReentrantLock[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    /**
     * @return This is method return locked cell by coordinates.
     */
    public ReentrantLock getCell(int x, int y) {
        return this.board[x][y];
    }

    /**
     * The method is blocking a cell of field, where must not going.
     */
    public void cellLock(int x, int y) {
        getCell(x, y).lock();
    }
}
