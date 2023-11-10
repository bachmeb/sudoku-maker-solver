package player;

import algorithms.SetsOfEight;
import algorithms.SudokuGridSolverAlgorithm;
import model.SudokuGrid;

public class SolveBySetsOfEight implements PlayerAction {

    SudokuGridSolverAlgorithm algorithm = new SetsOfEight();
    @Override
    public String explanation() {
        return algorithm.explanation();
    }

    @Override
    public SudokuGrid move(SudokuGrid grid) {
        SudokuGridSolverAlgorithm algorithm = new SetsOfEight();
        return algorithm.solve(grid);
    }
}
