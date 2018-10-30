package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Version 2.0
 */
public  class Personage implements Runnable {
    Board board;

    int x;

    int y;

    private String namePersonage;

    ReentrantLock cell;

    volatile boolean stopped = false;

    public Personage(Board board, String namePersonage, int x, int y) {
        this.x = x;
        this.y = y;
        this.namePersonage = namePersonage;
        this.board = board;
        this.cell = this.board.getCell(x, y);
    }

    /**
     * Move:
     * case 0 - move to right
     * case 1 - move to left
     * case 2 - move to down
     * case 3 - move to up
     *
     * @return random select way.
     */
    byte[] getDirection() {
        Random random = new Random();
        byte[] move = new byte[2];
        switch (random.nextInt(4)) {
            case 0:
                move[0] = 1;
                move[1] = 0;
                break;
            case 1:
                move[0] = -1;
                move[1] = 0;
                break;
            case 2:
                move[0] = 0;
                move[1] = 1;
                break;
            case 3:
                move[0] = 0;
                move[1] = -1;
                break;
            default:
                break;

        }
        return move;
    }

    /**
     * This is Method move hero
     *
     * @throws InterruptedException
     */
    void movePersonage(int xMove, int yMove) throws InterruptedException {
        ReentrantLock moveCell = this.board.getCell(xMove, yMove);
        boolean move = moveCell.tryLock(500, TimeUnit.MILLISECONDS);

        if (move) {
            this.x = xMove;
            this.y = yMove;
            this.cell.unlock();
            this.cell = moveCell;

            System.out.println(namePersonage + " coordinates x:" + x + " y:" + y);

            Thread.sleep(1000);
        }
    }

    public String getNamePersonage() {
        return this.namePersonage;
    }

    public void run() {
        this.cell.lock();
        while (!stopped) {
            byte[] direction = getDirection();
            int moveX = this.x + direction[0];
            int moveY = this.y + direction[1];

            if (moveX >= 0 && moveX < board.getWidth() && moveY >= 0 && moveY < board.getHeight()) {
                try {
                    movePersonage(moveX, moveY);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}