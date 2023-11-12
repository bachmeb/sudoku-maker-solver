package player;

import algorithms.AdjacentElimination;
import algorithms.OneSquareLeft;
import algorithms.SudokuGridSolverAlgorithm;
import model.SudokuGrid;

public class SolveByAdjacentElimination implements PlayerAction {
    SudokuGridSolverAlgorithm algorithm;
    SudokuGrid grid;
    int q;
    int v;

    public SolveByAdjacentElimination(SudokuGrid grid,int q,int v) {
        algorithm = new AdjacentElimination();
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
        return algorithm.solve(grid,q,v);
    }
}
