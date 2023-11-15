package actions;

import algorithms.SetsOfEight;
import algorithms.SudokuAlgorithm;
import algorithms.TwoOfThree;
import model.SudokuGrid;

public class SolveByTwoOfThree implements PlayerAction {

    SudokuAlgorithm algorithm;
    SudokuGrid grid;
    int q;
    int v;

    public SolveByTwoOfThree(SudokuGrid grid, int q, int v) {
        algorithm = new TwoOfThree();
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
