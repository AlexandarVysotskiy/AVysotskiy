package ru.job4j.chess;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BioshopTest {
    /**
     * @version $Id$
     * @since 0.1
     */
    @Test
    public void whenFigureBishop() {
        Cell bishopStart = new Cell(6, 1);
        Cell bishopDest = new Cell(1, 6);
        Bishop bishop = new Bishop(bishopStart);
        bishop.way(bishopStart, bishopDest);
        Cell[] result = bishop.way(bishopStart, bishopDest);
        Cell[] array = {bishopDest};
        assertThat(result, is(array));
    }
}