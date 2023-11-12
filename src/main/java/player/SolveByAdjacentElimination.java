package player;

import algorithms.AdjacentElimination;
import algorithms.SudokuGridSolverAlgorithm;
import model.SudokuGrid;

public class SolveByAdjacentElimination implements PlayerAction {
    SudokuGridSolverAlgorithm algorithm;

    public SolveByAdjacentElimination() {
        algorithm = new AdjacentElimination();
    }

    @Override
    public String explanation() {
        return algorithm.explanation();
    }

    @Override
    public SudokuGrid move(SudokuGrid grid) {
        return algorithm.solve(grid);
    }
}
