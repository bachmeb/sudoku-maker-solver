package algorithms;

import model.SudokuGrid;

import static service.SudokuGridChecker.checkGridForErrors;
import static service.SudokuGridObserver.whatCouldBeHere;

public class ThreeWayElimination implements SudokuGridSolverAlgorithm {
    @Override
    public SudokuGrid solve(SudokuGrid grid) {
        int[] squares = grid.getSquares();
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] == 0) {
                int[] range = whatCouldBeHere(grid, i);
                if (range.length == 1) {
                    squares[i] = range[0];
                    break;
                }
            }
        }
        if (checkGridForErrors(grid)) {
            throw new RuntimeException("Grid has errors");
        }
        return grid;
    }

    @Override
    public String explanation() {
        return """
                Three-Way Elimination.
                This algorithm looks at each square and determines if the square can be
                filled by only one number given the values present in the box, row, and
                column corresponding to the square""";
    }
}
