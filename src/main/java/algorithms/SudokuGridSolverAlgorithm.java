package algorithms;

import model.SudokuGrid;

public interface SudokuGridSolverAlgorithm {

    SudokuGrid solve(SudokuGrid grid, int q, int v);

    int[] search(SudokuGrid grid);

    String explanation();
}
