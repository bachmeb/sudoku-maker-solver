package algorithms;

import model.SudokuGrid;

import static service.SudokuGridChecker.checkGridForErrors;

public abstract class SudokuAlgorithm {
    public abstract int[] search(SudokuGrid grid);

    public abstract String explanation();

    public SudokuGrid solve(SudokuGrid grid, int q, int v) {
        int[] squares = grid.getSquares();
        squares[q] = v;
        if (checkGridForErrors(grid)) {
            throw new RuntimeException("Grid has errors");
        }
        return grid;
    }
}
