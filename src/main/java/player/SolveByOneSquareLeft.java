package player;

import algorithms.OneSquareLeft;
import algorithms.SudokuGridSolverAlgorithm;
import model.SudokuGrid;

public class SolveByOneSquareLeft implements PlayerAction{
    SudokuGridSolverAlgorithm algorithm;

    public SolveByOneSquareLeft() {
        this.algorithm = new OneSquareLeft();
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
