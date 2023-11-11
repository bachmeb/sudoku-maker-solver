package algorithms;

import model.SudokuGrid;

import static model.SudokuGrid.*;

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
        return grid;
    }

    private int[] whatCouldBeHere(SudokuGrid grid, int i) {
        int[] row = grid.getRowForSquare(i);
        int[] column = grid.getColumnForSquare(i);
        int[] box = grid.getBoxForSquare(i);
        int[] whatIs = new int[10];
        for (int j = 0; j < row.length; j++) {
            whatIs[row[j]] = whatIs[row[j]] + 1;
            whatIs[column[j]] = whatIs[column[j]] + 1;
            whatIs[box[j]] = whatIs[box[j]] + 1;
        }
        int[] couldBe = new int[0];
        for (int k = 0; k < row.length; k++) {
            if (whatIs[k] == 0) {
                int[] newCouldBe = new int[couldBe.length + 1];
                System.arraycopy(couldBe, 0, newCouldBe, 0, couldBe.length);
                couldBe = newCouldBe;
                couldBe[couldBe.length - 1] = k;
            }
        }
        return couldBe;
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
