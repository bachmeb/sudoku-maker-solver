package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.SudokuGridMaker;

class SudokuGridTest {

    static final Logger logger = LoggerFactory.getLogger(SudokuGridTest.class);

    SudokuGrid fixture;
    SudokuGridMaker maker;

    @BeforeEach
    void setUp() {
        maker = new SudokuGridMaker();
        logger.info("setting up test");
    }

    @Test
    void testToString() {
        logger.info("testToString()");
        fixture = new SudokuGrid();
        String output = fixture.toString();
        Assertions.assertNotNull(output);
    }

    @Test
    void testToString_SolvedGrid() {
        logger.info("testToString()");
        fixture = maker.makeSolvedGrid();
        String output = fixture.toString();
        Assertions.assertNotNull(output);
    }
}