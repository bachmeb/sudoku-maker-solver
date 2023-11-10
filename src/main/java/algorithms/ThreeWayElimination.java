package algorithms;

import model.SudokuGrid;

public class ThreeWayElimination implements SudokuGridSolverAlgorithm{
    @Override
    public SudokuGrid solve(SudokuGrid grid) {
        return null;
    }

    @Override
    public String explanation() {
        return "This algorithm looks at each square and determines if the " +
                "square can be filled by only one number given the values " +
                "present in the box, row, and column corresponding to the " +
                "square";
    }
}
