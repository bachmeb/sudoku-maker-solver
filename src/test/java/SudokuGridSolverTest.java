import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGridSolverTest {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridSolverTest.class);

    SudokuGridSolver fixture;
    @Mock
    SudokuGrid grid;

    @BeforeEach
    void setUp() {
        logger.info("setting up test");
        fixture = new SudokuGridSolver();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void solve() {
        fixture.solve(grid);
    }
}