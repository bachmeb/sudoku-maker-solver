package service;

import algorithms.AdjacentElimination;
import algorithms.SolveByCrossChecking;
import algorithms.SolveSetsOfEight;
import algorithms.SudokuGridSolverAlgorithms;
import model.SudokuGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.SudokuGridView;

public class SudokuGridSolver {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridSolver.class);

    SudokuGrid grid;
    SudokuGridObserver observer;
    SudokuGridChecker checker;
    boolean solved;
    int loop;

    public SudokuGrid solve(SudokuGrid grid) {
        this.grid = grid;
        int loop = 0;

        checker = new SudokuGridChecker();

        solved = checker.checkGridSolved(grid);

        if (solved) {
            logger.info("This grid is solved after " + loop + " loops!!!");
        }

        logger.info("loop number: " + loop++ + " - not yet solved");

        // does the grid have any errors?
        boolean hasError = checker.checkGridForErrors(grid);

        if (hasError) {
            logger.info("this grid has more than one of the same number in a box, row, or column");
            return grid;
        }

        grid = trySomeAlgorithms(grid);

        if (solved) {
            logger.info("This grid is solved after " + loop + " loops!!!");
        } else {
            logger.info("I give up after " + loop + " loops! I've tried everything I know how to do...");
        }

        logger.info(grid.toString());
        return grid;

    }


    private SudokuGrid trySomeAlgorithms(SudokuGrid grid) {
        loop++;

        SudokuGridSolverAlgorithms[] algorithms = new SudokuGridSolverAlgorithms[2];

        algorithms[1] = new AdjacentElimination();
        //algorithms[1] = new SolveByCrossChecking();
        algorithms[0] = new SolveSetsOfEight();

        observer = new SudokuGridObserver(grid);
        int filledSquaresBefore = observer.countAllFilledSquares();

        for (int i = 0; i < algorithms.length; i++) {
            SudokuGridSolverAlgorithms algorithm = algorithms[i];
            grid = algorithm.solve(grid);
            solved = checker.checkGridSolved(grid);
            if (solved) {
                break;
            }
            loop++;
            observer = new SudokuGridObserver(grid);
            int filledSquaresNow = observer.countAllFilledSquares();
            if (filledSquaresNow > filledSquaresBefore) {
                i = 0;
            }
        }

        return grid;

    }


}
