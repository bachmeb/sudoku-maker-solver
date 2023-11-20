package algorithms;

import actions.PlayerAction;
import model.SudokuGrid;

public class SolveByTwoOfThreeHorizontally implements PlayerAction {

    SudokuAlgorithm algorithm;
    SudokuGrid grid;
    int q;
    int v;

    public SolveByTwoOfThreeHorizontally(SudokuGrid grid, int q, int v) {
        algorithm = new TwoOfThreeHorizontally();
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
