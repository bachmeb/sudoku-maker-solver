package player;

import actions.*;
import algorithms.*;
import model.SudokuGrid;

import static player.PlayerUtil.print;

public class MediumPlayer extends Player {

    @Override
    public void introduce() {

        print("""
                Hi! I'm Player Two. I use these algorithms to solve the sudoku grid:
                                
                * Sets of Eight
                * Three-Way Elimination
                * Adjacent Elimination
                * Adjacent Elimination Plus Comparison,
                * One Square Column
                * One Square Box
                """);

    }

    @Override
    public PlayerAction determineNextAction(SudokuGrid grid) {
        int[] qv;
        // Check for sets of eight
        qv = new SetsOfEight().search(grid);
        if (qv != null) {
            return new SolveBySetsOfEight(grid, qv[0], qv[1]);
        }
        // Check if a square can be solved by only one number
        qv = new ThreeWayElimination().search(grid);
        if (qv != null) {
            return new SolveByThreeWayElimination(grid, qv[0], qv[1]);
        }
        // Check if a square can be solved by adjacent elimination
        qv = new AdjacentElimination().search(grid);
        if (qv != null) {
            return new SolveByAdjacentElimination(grid, qv[0], qv[1]);
        }
        // Check if a square can be solved by adjacent elimination
        qv = new VerticalEliminationPlusComparison().search(grid);
        if (qv != null) {
            return new SolveByVerticalEliminationPlusComparison(grid, qv[0],
                    qv[1]);
        }
        // Check if a square can be solved by adding the one value that fits
        qv = new OneSquareColumn().search(grid);
        if (qv != null) {
            return new SolveByOneSquareColumn(grid, qv[0], qv[1]);
        }
        // Check if a square can be solved by adding the one value that fits
        qv = new OneSquareBox().search(grid);
        if (qv != null) {
            return new SolveByOneSquareBox(grid, qv[0], qv[1]);
        }
        return new DefaultAction(grid);
    }
}