package actions;

import algorithms.OneSquareColumn;
import algorithms.SudokuAlgorithm;
import model.SudokuGrid;

public class SolveByOneSquareColumn implements PlayerAction {
    SudokuAlgorithm algorithm;
    SudokuGrid grid;
    int q;
    int v;

    public SolveByOneSquareColumn(SudokuGrid grid, int q, int v) {
        this.algorithm = new OneSquareColumn();
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
