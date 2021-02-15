import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeEmptyGrid();
        Assertions.assertNotNull(grid);

        String result = grid.toString();
        logger.info(result);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

    }


    @Test
    void solveSolvedGrid() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeSolvedGrid();
        Assertions.assertNotNull(grid);

        String result = grid.toString();
        logger.info(result);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

    }


    @Test
    void solveGridWithDuplicates() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeOnesThruNinesGrid();
        Assertions.assertNotNull(grid);

        String result = grid.toString();
        logger.info(result);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checker.checkGridSolved(solvedGrid);
        Assertions.assertFalse(solved);

        boolean hasError = checker.checkGridForErrors(solvedGrid);
        Assertions.assertTrue(hasError);

    }


    @Test
    void addTheLastNumberToTheSet_MissingR0C3() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeAlmostSolvedGridWithOneMissingNumber();
        Assertions.assertNotNull(grid);

        String result = grid.toString();
        logger.info(result);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checker.checkGridSolved(solvedGrid);
        Assertions.assertTrue(solved);

    }

    @Test
    void addTheLastNumberToTheSet_MissingRow8() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeAlmostSolvedGridMissingLastRow();
        Assertions.assertNotNull(grid);

        String result = grid.toString();
        logger.info(result);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checker.checkGridSolved(solvedGrid);
        Assertions.assertTrue(solved);

    }


    @Test
    void addTheLastNumberToTheSet_MissingColumn8() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeAlmostSolvedGridMissingLastColumn();
        Assertions.assertNotNull(grid);

        String result = grid.toString();
        logger.info(result);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checker.checkGridSolved(grid);
        Assertions.assertTrue(solved);

    }

    @Test
    void addTheLastNumberToTheSet_MissingOneInEveryBox() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeAlmostSolvedGridMissingOneInEveryBox();
        Assertions.assertNotNull(grid);

        String result = grid.toString();
        logger.info(result);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checker.checkGridSolved(grid);
        Assertions.assertTrue(solved);
    }

    @Test
    void solveHalfSolvedGrid() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeHalfSolvedGrid();
        Assertions.assertNotNull(grid);

        String result = grid.toString();
        logger.info(result);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

    }


    @Test
    void solveGridMissingOneBox() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeAlmostSolvedGridMissingOneRowInThreeBoxes();
        Assertions.assertNotNull(grid);

        String result = grid.toString();
        logger.info(result);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

        SudokuGridChecker checker = new SudokuGridChecker();
        boolean solved = checker.checkGridSolved(grid);
        Assertions.assertTrue(solved);

    }

}