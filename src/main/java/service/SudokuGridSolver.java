package service;

import model.SudokuGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static service.SudokuGridChecker.checkGridForErrors;
import static service.SudokuGridChecker.checkGridSolved;

public class SudokuGridSolver {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridSolver.class);

    SudokuGrid grid;
    boolean solved;

    public SudokuGrid solve(SudokuGrid grid) {
        this.grid = grid;
        int loop = 0;

        solved = checkGridSolved(grid);

        if (solved) {
            logger.info("This grid is solved after " + loop + " loops!!!");
        }

        logger.info("loop number: " + loop++ + " - not yet solved");

        // does the grid have any errors?
        boolean hasError = checkGridForErrors(grid);

        if (hasError) {
            logger.info("this grid has more than one of the same number in a "
                    + "box, row, or column");
            return grid;
        }

        grid = trySomeAlgorithms(grid);

        if (solved) {
            logger.info("This grid is solved after " + loop + " loops!!!");
        } else {
            logger.info("I give up after " + loop + " loops! I've tried " +
                    "everything I know how to do...");
        }

        logger.info(grid.toString());
        return grid;

    }

    private SudokuGrid trySomeAlgorithms(SudokuGrid grid) {

        return grid;

    }

}
