import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SudokuGridCheckerTest {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridCheckerTest.class);

    SudokuGridChecker fixture;
    @Mock
    SudokuGrid grid;

    @BeforeEach
    void setUp() {
        logger.info("setting up test");
        fixture = new SudokuGridChecker();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void solve() {
        SudokuGridMaker maker = new SudokuGridMaker();
        boolean solved = fixture.solve(maker.make());
        Assertions.assertTrue(solved);
    }
}