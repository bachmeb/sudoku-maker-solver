package algorithms;

import model.SudokuGrid;

import static service.SudokuGridChecker.checkSetForNumber;
import static service.SudokuGridObserver.*;

public class TwoOfThreeHorizontally extends SudokuAlgorithm {

    /**
     * @param grid
     * @return
     */
    public static int[] aSetOfSixInOneRowHasTwoSquaresThatCouldBeTwoNumbers(SudokuGrid grid) {
        int[][] rows = grid.getRows();
        for (int rn = 0; rn < rows.length; rn++) {
            if (setHasThisMany(rows[rn], 6)) {
                int boxWithTwo =
                        whichBoxHorizontallyHasTwoEmptySquares(rows[rn]);
                if (boxWithTwo > -1) {
                    int[][] couldBes = new int[9][];
                    for (int p = 0; p < rows[rn].length; p++) {
                        int v = rows[rn][p];
                        if (v == 0) {
                            int q = getSquareForColumnNumberAndPosition(rn, p);
                            couldBes[p] = whatCouldBeHere(grid, q);
                        }
                    }
                    return qvForOneOfThree(rn, couldBes, boxWithTwo);
                }
            }
        }
        return null;
    }

    private static int[] qvForOneOfThree(int rn,
                                         int[][] couldBes, int boxWithTwo) {
        int[][] couldBeByBox = new int[3][];
        int rp = -1;
        // Loop through the list of could bes
        for (int p = 0; p < 9; p++) {
            if (couldBes[p] != null) {
                // populate the array of 'could bes by box'
                couldBeByBox[p / 3] = mergeIntArrays(couldBeByBox[p / 3],
                        couldBes[p]);
                // if the loop index is not in the box with two
                if (p / 3 != boxWithTwo) {
                    // set the row position to the loop index
                    // based on logic applied before this point of execution
                    // there will only be square not in the box with two
                    rp = p;
                }
            }
        }
        int[] onlyForTwo = couldBeByBox[boxWithTwo];
        int onlyOne = 0;
        for (int bx = 0; bx < couldBeByBox.length; bx++) {
            if (couldBeByBox[bx] != null) {
                if (bx != boxWithTwo) {
                    onlyOne = removeTheseFromThat(onlyForTwo, couldBeByBox[bx]);
                }
            }
        }
        if (onlyOne > 0) {
            int[] qv = new int[2];
            qv[0] = getSquareForRowNumberAndPosition(rn, rp);
            qv[1] = onlyOne;
            return qv;
        }
        return null;
    }

    private static int[] mergeIntArrays(int[] a1, int[] a2) {
        if (a1 == null) {
            a1 = new int[0];
        }
        int[] merged = a1;
        for (int i : a2) {
            if (!checkSetForNumber(i, merged)) {
                int[] temp = new int[merged.length + 1];
                System.arraycopy(merged, 0, temp, 0, merged.length);
                temp[temp.length - 1] = i;
                merged = temp;
            }
        }
        return merged;
    }

    private static int removeTheseFromThat(int[] onlyForTwo, int[] cbb) {
        if (cbb.length == 2) {
            for (int j = 0; j < cbb.length; j++) {
                for (int oft : onlyForTwo) {
                    if (cbb[j] == oft) {
                        cbb[j] = 0;
                    }
                }
            }
            for (int i : cbb) {
                if (i > 0) {
                    return i;
                }
            }
        }
        return 0;
    }

    private static int whichBoxHorizontallyHasTwoEmptySquares(int[] row) {
        int[] boxes = new int[3];
        for (int rp = 0; rp < row.length; rp++) {
            if (row[rp] == 0) {
                boxes[rp / 3] = boxes[rp / 3] + 1;
            }
        }
        for (int bn = 0; bn < boxes.length; bn++) {
            if (boxes[bn] == 2) {
                return bn;
            }
        }
        return -1;
    }



    @Override
    public int[] search(SudokuGrid grid) {
        return aSetOfSixInOneRowHasTwoSquaresThatCouldBeTwoNumbers(grid);
    }

    @Override
    public String explanation() {
        return """
                Two of three horizontally.
                This algorithm checks each row to see if six boxes have already
                been filled, and if one box of the three boxes containing the row
                has two unfilled squares. If so, the algorithm checks to see if
                the square in the box with one empty square has two numbers which
                could be added to the square, and if only one of those numbers is
                shared with the numbers that could be added to the two unfilled
                squares in the other box.
                """;
    }

}
