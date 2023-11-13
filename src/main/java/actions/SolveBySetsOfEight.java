package actions;

import algorithms.SetsOfEight;
import algorithms.SudokuAlgorithm;
import model.SudokuGrid;

public class SolveBySetsOfEight implements PlayerAction {

    SudokuAlgorithm algorithm;
    SudokuGrid grid;
    int q;
    int v;

    public SolveBySetsOfEight(SudokuGrid grid, int q, int v) {
        algorithm = new SetsOfEight();
        this.grid = grid;
        this.q = q;
        this.v = v;
    }

    @Override
    public String explanation() {
        return algorithm.explanation() + "Square: " + q + "\n" + "Value: " + v + "\n" ;
    }

    @Override
    public SudokuGrid move() {
        return algorithm.solve(grid, q, v);
    }
}
