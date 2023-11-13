package actions;

import algorithms.AdjacentElimination;
import algorithms.AdjacentEliminationPlusComparison;
import algorithms.SudokuAlgorithm;
import model.SudokuGrid;

public class SolveByAdjacentEliminationPlusComparison implements PlayerAction {
    SudokuAlgorithm algorithm;
    SudokuGrid grid;
    int q;
    int v;

    public SolveByAdjacentEliminationPlusComparison(SudokuGrid grid, int q, int v) {
        algorithm = new AdjacentEliminationPlusComparison();
        this.grid = grid;
        this.q = q;
        this.v = v;
    }

    @Override
    public String explanation() {
        return algorithm.explanation();
    }

    @Override
    public SudokuGrid move() {
        return algorithm.solve(grid, q, v);
    }
}
