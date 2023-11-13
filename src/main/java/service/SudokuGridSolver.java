package service;

import actions.SolveByAdjacentElimination;
import actions.SolveByOneSquareColumn;
import actions.SolveBySetsOfEight;
import actions.SolveByThreeWayElimination;
import algorithms.AdjacentElimination;
import algorithms.OneSquareColumn;
import algorithms.SetsOfEight;
import algorithms.ThreeWayElimination;
import model.SudokuGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static player.PlayerUtil.print;
import static service.SudokuGridChecker.checkGridSolved;

public class SudokuGridSolver {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridSolver.class);
    SudokuGrid grid;

    public SudokuGrid solve(SudokuGrid grid) {
        if (checkGridSolved(grid)) {
            return grid;
        }
        logger.info(grid.toString());
        this.grid = trySomeAlgorithms(grid);
        logger.info(grid.toString());
        return grid;
    }

    private SudokuGrid trySomeAlgorithms(SudokuGrid grid) {
        boolean solved = false;
        int tries = 0;
        while (!solved) {
            grid = trySolving(grid);
            tries++;
            solved = checkGridSolved(grid);
        }
        print("That took " + tries + " tries!");
        return grid;

    }

    private SudokuGrid trySolving(SudokuGrid grid) {

        int[] qv;
        // Check for sets of eight
        qv = new SetsOfEight().search(grid);
        if (qv != null) {
            return new SolveBySetsOfEight(grid, qv[0], qv[1]).move();
        }

        // Check if a square can be solved by adjacent elimination
        qv = new AdjacentElimination().search(grid);
        if (qv != null) {
            return new SolveByAdjacentElimination(grid, qv[0], qv[1]).move();
        }
        // Check if a square can be solved by only one number
        qv = new ThreeWayElimination().search(grid);
        if (qv != null) {
            return new SolveByThreeWayElimination(grid, qv[0], qv[1]).move();
        }
        // Check if a square can be solved by adding the one value that fits
        qv = new OneSquareColumn().search(grid);
        if (qv != null) {
            return new SolveByOneSquareColumn(grid, qv[0], qv[1]).move();
        }

        throw new RuntimeException("I give up!");
    }

}
