import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGridMakerTest {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridMakerTest.class);

    SudokuGridMaker fixture;

    @BeforeEach
    void setUp() {
        logger.info("setting up test");
        fixture = new SudokuGridMaker();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void make() {
        fixture.make();
    }

}