package algorithms;

import model.SudokuGrid;

import static service.SudokuGridChecker.checkGridForErrors;
import static service.SudokuGridObserver.whatCouldBeHere;

public class ThreeWayElimination implements SudokuGridSolverAlgorithm {

    @Override
    public SudokuGrid solve(SudokuGrid grid, int q, int v) {
        int[] squares = grid.getSquares();
        squares[q] = v;
        if (checkGridForErrors(grid)) {
            throw new RuntimeException("Grid has errors");
        }
        return grid;
    }

    @Override
    public int[] search(SudokuGrid grid) {
        return whatCouldBeInTheBoxColumnAndRow(grid);
    }

    int[] whatCouldBeInTheBoxColumnAndRow(SudokuGrid grid) {
        int[] squares = grid.getSquares();
        for (int q = 0; q < squares.length; q++) {
            if (squares[q] == 0) {
                int[] range = whatCouldBeHere(grid, q);
                if (range.length == 1) {
                    int[] qv = new int[2];
                    qv[0] = q;
                    qv[1] = range[0];
                    return qv;
                }
            }
        }
        return null;
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
