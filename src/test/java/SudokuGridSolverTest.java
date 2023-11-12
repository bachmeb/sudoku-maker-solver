import model.SudokuGrid;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.SudokuGridChecker;
import service.SudokuGridMaker;
import service.SudokuGridSolver;

import static service.SudokuGridChecker.checkGridSolved;
import static service.SudokuGridMaker.makeEmptyGrid;
import static service.SudokuGridMaker.makeSolvedGrid;

class SudokuGridSolverTest {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridSolverTest.class);

    SudokuGridSolver fixture;

    @BeforeEach
    void setUp() {
        logger.info("setting up solver test");
        fixture = new SudokuGridSolver();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void solveEmptyGrid() {
        SudokuGrid grid = makeEmptyGrid();
        Assertions.assertNotNull(grid);
        try {
            fixture.solve(grid);
        } catch (RuntimeException e) {
            Assertions.assertEquals(RuntimeException.class, e.getClass());
        }
    }

    @Test
    void solveSolvedGrid() {
        SudokuGrid grid = makeSolvedGrid();
        Assertions.assertNotNull(grid);

        String result = grid.toString();
        logger.info(result);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);
        Assertions.assertTrue(checkGridSolved(solvedGrid));
    }

    @Test
    void solveGridWithDuplicates() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeOnesThruNinesGrid();
        Assertions.assertNotNull(grid);

        try {
            fixture.solve(grid);
        } catch (RuntimeException e) {
            Assertions.assertEquals(RuntimeException.class, e.getClass());
        }

    }

    @Test
    void addTheLastNumberToTheSet_MissingR0C3() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeAlmostSolvedGridWithOneMissingNumber();
        Assertions.assertNotNull(grid);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checkGridSolved(solvedGrid);
        Assertions.assertTrue(solved);
    }

    @Test
    void addTheLastNumberToTheSet_MissingRow8() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeAlmostSolvedGridMissingLastRow();
        Assertions.assertNotNull(grid);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checkGridSolved(solvedGrid);
        Assertions.assertTrue(solved);

    }

    @Test
    void addTheLastNumberToTheSet_MissingColumn8() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeAlmostSolvedGridMissingLastColumn();
        Assertions.assertNotNull(grid);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checkGridSolved(grid);
        Assertions.assertTrue(solved);

    }

    @Test
    void addTheLastNumberToTheSet_MissingOneInEveryBox() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeAlmostSolvedGridMissingOneInEveryBox();
        Assertions.assertNotNull(grid);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checkGridSolved(grid);
        Assertions.assertTrue(solved);
    }

    @Test
    void solveHalfSolvedGrid() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeHalfSolvedGrid();
        Assertions.assertNotNull(grid);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checkGridSolved(grid);
        Assertions.assertTrue(solved);

    }

    @Test
    void solveGridMissingOneBox() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeAlmostSolvedGridMissingOneBox();
        Assertions.assertNotNull(grid);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checkGridSolved(grid);
        Assertions.assertTrue(solved);

    }

    @Test
    void solveGridMissingTwoBoxes() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeAlmostSolvedGridMissingTwoBoxes();
        Assertions.assertNotNull(grid);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checkGridSolved(grid);
        Assertions.assertTrue(solved);

    }

    @Test
    void solveGridFromPCGame() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeGridFromPCGame();
        Assertions.assertNotNull(grid);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checkGridSolved(grid);
        Assertions.assertTrue(solved);

    }

}