package actions;

import algorithms.SudokuAlgorithm;
import algorithms.ThreeWayElimination;
import model.SudokuGrid;

public class SolveByThreeWayElimination implements PlayerAction {
    SudokuAlgorithm algorithm;
    SudokuGrid grid;
    int q;
    int v;

    public SolveByThreeWayElimination(SudokuGrid grid, int q, int v) {
        algorithm = new ThreeWayElimination();
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
