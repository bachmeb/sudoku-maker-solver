package player;

import algorithms.SudokuGridSolverAlgorithm;
import algorithms.ThreeWayElimination;
import model.SudokuGrid;

public class SolveByThreeWayElimination implements PlayerAction {
    SudokuGridSolverAlgorithm algorithm = new ThreeWayElimination();

    @Override
    public String explanation() {
        return algorithm.explanation();
    }

    @Override
    public SudokuGrid move(SudokuGrid grid) {
        return algorithm.solve(grid);
    }
}
