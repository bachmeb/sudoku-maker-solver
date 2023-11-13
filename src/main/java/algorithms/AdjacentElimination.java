package algorithms;

import model.SudokuGrid;

import static service.SudokuGridChecker.checkSetForNumber;
import static service.SudokuGridObserver.*;

public class AdjacentElimination extends SudokuAlgorithm {

    /**
     * @param grid
     * @return
     */
    public static int[] aSquareCanBeSolvedByAdjacentElimination(SudokuGrid grid) {
        int[] squares = grid.getSquares();
        for (int q = 0; q < squares.length; q++) {
            if(squares[q]==0){
            for (int n = 1; n < 10; n++) {
                if (checkBoxAndAdjacentRowsAndColumnsForNumber(grid, q, n)) {
                    int[] qv = new int[2];
                    qv[0] = q;
                    qv[1] = n;
                    return qv;
                }
            }}
        }
        return null;
    }

    public static boolean checkBoxAndAdjacentRowsAndColumnsForNumber(SudokuGrid grid, int q, int n) {
        // See if the corresponding box contains the number
        int[] b = grid.getBoxForSquare(q);
        if (checkSetForNumber(n, b)) {
            return false;
        }
        // See if the corresponding row column and box contain a given number
        int[] combined = lookTwoWays(grid, q);
        if (combined[n] == 0) {
            // if not, get the box num
            int boxNumber = getBoxNumForSquare(q);
            // Then get the rows and columns that intersect the same box
            int[][] columns = grid.getColumnsForBoxNum(boxNumber);
            int[][] rows = grid.getRowsForBoxNum(boxNumber);
            // See if a given number is present twice in the adjacent
            // rows and columns
            int[] rCount = countNumberAppearancesByDimension(columns);
            int[] cCount = countNumberAppearancesByDimension(rows);
            return rCount[n] == 2 && cCount[n] == 2;
        }
        return false;
    }

    @Override
    public int[] search(SudokuGrid grid) {
        return aSquareCanBeSolvedByAdjacentElimination(grid);
    }

    @Override
    public String explanation() {
        return """
                Adjacent elimination.
                This algorithm looks at every square and checks to see for every number,
                1 through 9, if the number is present in the box and the adjacent rows
                and columns that intersect the box.
                """;
    }

}
