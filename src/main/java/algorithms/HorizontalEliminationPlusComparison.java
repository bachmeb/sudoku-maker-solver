package algorithms;

import model.SudokuGrid;

import static service.SudokuGridObserver.*;

public class HorizontalEliminationPlusComparison extends SudokuAlgorithm {

    /**
     * @param grid
     * @return
     */
    public static int[] aSquareCanBeSolvedByHorizontalEliminationPlusComparison(SudokuGrid grid) {
        int[] squares = grid.getSquares();
        for (int q = 0; q < squares.length; q++) {
            if (squares[q] == 0) {
                // See if the corresponding row column and box contain a
                // given number
                int[] combined = lookThreeWays(grid, q);
                for (int n = 1; n < 10; n++) {
                    if (combined[n] == 0) {
                        if (!checkHorizontallyAdjacentColumnsForNumber(grid, q,
                                n)) {
                            continue;
                        }
                        if (otherOpenSquaresInInnerRowHaveNumberInColumn(grid
                                , q, n)) {
                            int[] qv = new int[2];
                            qv[0] = q;
                            qv[1] = n;
                            return qv;
                        }
                    }
                }
            }
        }
        return null;
    }

    private static boolean otherOpenSquaresInInnerRowHaveNumberInColumn(SudokuGrid grid, int q, int n) {
        int bn = getBoxNumForSquare(q);
        int[] box = grid.getBoxes()[bn];
        int p = getBoxPositionFromSquare(q);
        int irn = getInnerRowNumFromPosition(p);
        int[] innerRow = getInnerRowFromBox(box, irn);
        int countOtherOpen = 0;
        int countColumnContainsNumber = 0;
        for (int irp = 0; irp < innerRow.length; irp++) {
            if (innerRow[irp] == 0) {
                int oq =
                        getSquareFromBoxInnerRowNumAndInnerRowPosition(bn, irn, irp);
                if (oq == q) {
                    continue;
                }
                countOtherOpen++;
                int[] combined = lookTwoWays(grid, oq);
                if (combined[n] == 1) {
                    countColumnContainsNumber++;
                }
            }
        }
        return countOtherOpen == countColumnContainsNumber;
    }

    public static boolean checkHorizontallyAdjacentColumnsForNumber(SudokuGrid grid, int q, int n) {
        // if not, get the box num
        int boxNumber = getBoxNumForSquare(q);
        // Then get the rows and columns that intersect the same box
        int[][] rows = grid.getRowsForBoxNum(boxNumber);
        // See if a given number is present twice in the adjacent columns
        int[] rCount = countNumberAppearancesByDimension(rows);
        return rCount[n] == 2;
    }

    @Override
    public int[] search(SudokuGrid grid) {
        return aSquareCanBeSolvedByHorizontalEliminationPlusComparison(grid);
    }

    @Override
    public String explanation() {
        return """
                Horizontal elimination plus comparison.
                This algorithm looks at every square and checks to see for every number,
                1 through 9, if the number is present in the box and horizontally adjacent
                rows that intersect the box, and then compares the value in any other
                open squares in the same inner row of the same box with values in other
                boxes in the same columns.
                """;
    }

}
