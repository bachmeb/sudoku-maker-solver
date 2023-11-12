package player;

import algorithms.SetsOfEight;
import algorithms.SudokuGridSolverAlgorithm;
import model.SudokuGrid;

public class SolveBySetsOfEight implements PlayerAction {

    SudokuGridSolverAlgorithm algorithm;

    public SolveBySetsOfEight() {
        algorithm = new SetsOfEight();
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
