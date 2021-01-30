import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGridTest {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridTest.class);

    SudokuGrid fixture;

    @BeforeEach
    void setUp() {
        logger.info("setting up test");
        fixture = new SudokuGrid();
    }

    @Test
    void testToString() {
        logger.info("testToString()");
        String output = fixture.toString();
        Assertions.assertNotNull(output);
    }
}