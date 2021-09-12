import model.SudokuGrid;
import service.SudokuGridChecker;
import service.SudokuGridMaker;
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
    void check_Solved() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeSolvedGrid();
        boolean solved = fixture.checkGridSolved(grid);
        Assertions.assertTrue(solved);
    }

    @Test
    void check_NotSolved() {
        SudokuGridMaker maker = new SudokuGridMaker();
        boolean solved = fixture.checkGridSolved(maker.makeOnesThruNinesGrid());
        Assertions.assertFalse(solved);
    }
}