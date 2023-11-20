package actions;

import algorithms.VerticalEliminationPlusComparison;
import algorithms.SudokuAlgorithm;
import model.SudokuGrid;

public class SolveByVerticalEliminationPlusComparison implements PlayerAction {
    SudokuAlgorithm algorithm;
    SudokuGrid grid;
    int q;
    int v;

    public SolveByVerticalEliminationPlusComparison(SudokuGrid grid, int q,
                                                    int v) {
        algorithm = new VerticalEliminationPlusComparison();
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
