package algorithms;

import model.SudokuGrid;

import static service.SudokuGridObserver.*;

public class AdjacentEliminationPlusComparison extends SudokuAlgorithm {

    /**
     * @param grid
     * @return
     */
    public static int[] aSquareCanBeSolvedByAdjacentEliminationPlusComparison(SudokuGrid grid) {
        int[] squares = grid.getSquares();
        for (int q = 0; q < squares.length; q++) {
            for (int n = 1; n < 10; n++) {
                // See if the corresponding row column and box contain a
                // given number
                int[] combined = lookThreeWays(grid, q);
                if (combined[n] == 0) {
                    if (!checkVerticallyAdjacentColumnsForNumber(grid, q, n)) {
                        continue;
                    }
                    if (otherOpenSquaresInInnerColumnHaveNumberInRow(grid, q,
                            n)) {
                        int[] qv = new int[2];
                        qv[0] = q;
                        qv[1] = n;
                        return qv;
                    }
                }
            }
        }
        return null;
    }

    private static boolean otherOpenSquaresInInnerColumnHaveNumberInRow(SudokuGrid grid, int q, int n) {
        int bn = getBoxNumForSquare(q);
        int[] box = grid.getBoxes()[bn];
        int p = getBoxPositionFromSquare(q);
        int cn = getInnerColumnNumFromPosition(p);
        int[] innerColumn = getInnerColumnFromBox(box, cn);
        for (int j : innerColumn) {
            if (j == 0) {
                //TODO - find the row of the value from the inner column
                int[] combined = lookTwoWays(grid, q);
                if (combined[n] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkVerticallyAdjacentColumnsForNumber(SudokuGrid grid, int q, int n) {
        // if not, get the box num
        int boxNumber = getBoxNumForSquare(q);
        // Then get the rows and columns that intersect the same box
        int[][] columns = grid.getColumnsForBoxNum(boxNumber);
        // See if a given number is present twice in the adjacent columns
        int[] rCount = countNumberAppearancesByDimension(columns);
        return rCount[n] == 2;
    }

    @Override
    public int[] search(SudokuGrid grid) {
        return aSquareCanBeSolvedByAdjacentEliminationPlusComparison(grid);
    }

    @Override
    public String explanation() {
        return """
                Adjacent elimination.
                This algorithm looks at every square and checks to see for every number,
                1 through 9, if the number is present in the box and vertically adjacent
                columns that intersect the box, and then compares the value in any other
                open squares in the same inner column of the same box with values in the
                same row.
                """;
    }

}
