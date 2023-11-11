package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGridObserverTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getRowNumForSquare() {
        int beginningOfRow0 = 0;
        int beginningOfRow1 = beginningOfRow0 + 9;

        int endOfRow0 = (1 * 9) - 1;
        int endOfRow1 = endOfRow0 + 9;

        assertEquals(0, SudokuGridObserver.getRowNumForSquare(beginningOfRow0));
        assertEquals(1, SudokuGridObserver.getRowNumForSquare(beginningOfRow1));
        assertEquals(0, SudokuGridObserver.getRowNumForSquare(endOfRow0));
        assertEquals(1, SudokuGridObserver.getRowNumForSquare(endOfRow1));

    }

    @Test
    void getColumnNumForSquare() {
        int beginningOfRow0 = 0;
        int beginningOfRow1 = beginningOfRow0 + 9;

        int endOfRow0 = (1 * 9) - 1;
        int endOfRow1 = endOfRow0 + 9;

        assertEquals(0,
                SudokuGridObserver.getColumnNumForSquare(beginningOfRow0));
        assertEquals(0,
                SudokuGridObserver.getColumnNumForSquare(beginningOfRow1));
        assertEquals(8, SudokuGridObserver.getColumnNumForSquare(endOfRow0));
        assertEquals(8, SudokuGridObserver.getColumnNumForSquare(endOfRow1));

    }

    @Test
    void getBoxNumForSquare() {
        int rowLength = 9;
        int beginningOfRow0 = 0;
        int beginningOfRow1 = beginningOfRow0 + rowLength;
        int beginningOfRow2 = beginningOfRow1 + rowLength;
        int beginningOfRow3 = beginningOfRow2 + rowLength;
        int beginningOfRow4 = beginningOfRow3 + rowLength;
        int beginningOfRow5 = beginningOfRow4 + rowLength;
        int beginningOfRow6 = beginningOfRow5 + rowLength;
        int beginningOfRow7 = beginningOfRow6 + rowLength;
        int beginningOfRow8 = beginningOfRow7 + rowLength;

        int endOfRow0 = (1 * rowLength) - 1;
        int endOfRow1 = endOfRow0 + rowLength;
        int endOfRow2 = endOfRow1 + rowLength;
        int endOfRow3 = endOfRow2 + rowLength;
        int endOfRow4 = endOfRow3 + rowLength;
        int endOfRow5 = endOfRow4 + rowLength;
        int endOfRow6 = endOfRow5 + rowLength;
        int endOfRow7 = endOfRow6 + rowLength;
        int endOfRow8 = endOfRow7 + rowLength;

        assertEquals(0, SudokuGridObserver.getBoxNumForSquare(beginningOfRow0));
        assertEquals(2, SudokuGridObserver.getBoxNumForSquare(endOfRow0));
        assertEquals(0, SudokuGridObserver.getBoxNumForSquare(beginningOfRow1));
        assertEquals(2, SudokuGridObserver.getBoxNumForSquare(endOfRow1));
        assertEquals(0, SudokuGridObserver.getBoxNumForSquare(beginningOfRow2));
        assertEquals(2, SudokuGridObserver.getBoxNumForSquare(endOfRow2));
        assertEquals(3, SudokuGridObserver.getBoxNumForSquare(beginningOfRow3));
        assertEquals(5, SudokuGridObserver.getBoxNumForSquare(endOfRow3));
        assertEquals(3, SudokuGridObserver.getBoxNumForSquare(beginningOfRow4));
        assertEquals(5, SudokuGridObserver.getBoxNumForSquare(endOfRow4));
        assertEquals(3, SudokuGridObserver.getBoxNumForSquare(beginningOfRow5));
        assertEquals(5, SudokuGridObserver.getBoxNumForSquare(endOfRow5));
        assertEquals(6, SudokuGridObserver.getBoxNumForSquare(beginningOfRow6));
        assertEquals(8, SudokuGridObserver.getBoxNumForSquare(endOfRow6));
        assertEquals(6, SudokuGridObserver.getBoxNumForSquare(beginningOfRow7));
        assertEquals(8, SudokuGridObserver.getBoxNumForSquare(endOfRow7));
        assertEquals(6, SudokuGridObserver.getBoxNumForSquare(beginningOfRow8));
        assertEquals(8, SudokuGridObserver.getBoxNumForSquare(endOfRow8));
    }
}