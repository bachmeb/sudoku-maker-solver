import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

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
    void solveUnsolveableGrid() {
        SudokuGridMaker maker = new SudokuGridMaker();
        SudokuGrid grid = maker.makeOnesThruNinesGrid();
        Assertions.assertNotNull(grid);

        String result = grid.toString();
        logger.info(result);

        SudokuGrid solvedGrid = fixture.solve(grid);
        Assertions.assertNotNull(solvedGrid);

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
}