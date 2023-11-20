package actions;

import algorithms.HorizontalEliminationPlusComparison;
import algorithms.SudokuAlgorithm;
import algorithms.VerticalEliminationPlusComparison;
import model.SudokuGrid;

public class SolveByHorizontalEliminationPlusComparison implements PlayerAction {
    SudokuAlgorithm algorithm;
    SudokuGrid grid;
    int q;
    int v;

    public SolveByHorizontalEliminationPlusComparison(SudokuGrid grid, int q,
                                                      int v) {
        algorithm = new HorizontalEliminationPlusComparison();
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
