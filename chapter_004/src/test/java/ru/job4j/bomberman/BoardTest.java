//package ru.job4j.bomberman;
//
//import org.junit.Test;
//
/**
 * This class was using for test in first version game;
 */
//public class BoardTest {
//    @Test
//    public void startGame() {
//        Board board = new Board(10, 10);
//        Personage personage = new Personage(board, 1, 1);
//        Thread thread = new Thread(personage);
//        thread.setName("Personage Thread");
//        thread.start();
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//        personage.doStop();
//    }
//}