package actions;

import algorithms.SudokuAlgorithm;
import algorithms.TwoOfThreeVertically;
import model.SudokuGrid;

public class SolveByTwoOfThreeVertically implements PlayerAction {

    SudokuAlgorithm algorithm;
    SudokuGrid grid;
    int q;
    int v;

    public SolveByTwoOfThreeVertically(SudokuGrid grid, int q, int v) {
        algorithm = new TwoOfThreeVertically();
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
