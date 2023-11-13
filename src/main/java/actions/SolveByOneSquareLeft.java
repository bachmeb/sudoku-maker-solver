package actions;

import algorithms.OneSquareLeft;
import algorithms.SudokuAlgorithm;
import model.SudokuGrid;

public class SolveByOneSquareLeft implements PlayerAction {
    SudokuAlgorithm algorithm;
    SudokuGrid grid;
    int q;
    int v;

    public SolveByOneSquareLeft(SudokuGrid grid, int q, int v) {
        this.algorithm = new OneSquareLeft();
        this.grid = grid;
        this.q = q;
        this.v = v;
    }

    @Override
    public String explanation() {
        return algorithm.explanation() + "Square: " + q + "\n" + "Value: " + v + "\n";
    }

    @Override
    public SudokuGrid move() {
        return algorithm.solve(grid, q, v);
    }
}
