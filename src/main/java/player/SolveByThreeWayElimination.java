package player;

import algorithms.SudokuGridSolverAlgorithm;
import algorithms.ThreeWayElimination;
import model.SudokuGrid;

public class SolveByThreeWayElimination implements PlayerAction {
    SudokuGridSolverAlgorithm algorithm;
    SudokuGrid grid;
    int q;
    int v;

    public SolveByThreeWayElimination(SudokuGrid grid,int q,int v) {
        algorithm = new ThreeWayElimination();
        this.grid = grid;
        this.q = q;
        this.v = v;
    }

    @Override
    public String explanation() {
        return algorithm.explanation();
    }

    @Override
    public SudokuGrid move( ) {
        return algorithm.solve(grid,q,v);
    }
}
