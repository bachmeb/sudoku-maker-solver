package actions;

import algorithms.OneSquareBox;
import algorithms.OneSquareColumn;
import algorithms.SudokuAlgorithm;
import model.SudokuGrid;

public class SolveByOneSquareBox implements PlayerAction {
    SudokuAlgorithm algorithm;
    SudokuGrid grid;
    int q;
    int v;

    public SolveByOneSquareBox(SudokuGrid grid, int q, int v) {
        this.algorithm = new OneSquareBox();
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
