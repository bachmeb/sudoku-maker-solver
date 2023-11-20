package algorithms;

import model.SudokuGrid;

import static service.SudokuGridChecker.checkSetForNumber;
import static service.SudokuGridObserver.*;

public class TwoOfThreeVertically extends SudokuAlgorithm {

    /**
     * @param grid
     * @return
     */
    public static int[] aSetOfSixHasTwoSquaresThatCouldBeTwoNumbers(SudokuGrid grid) {
        int[][] columns = grid.getColumns();
        for (int cn = 0; cn < columns.length; cn++) {
            if (setHasThisMany(columns[cn], 6)) {
                int boxWithTwo = whichBoxHasTwoEmptySquares(columns[cn]);
                if (boxWithTwo > -1) {
                    int[][] couldBes = new int[9][];
                    for (int p = 0; p < columns[cn].length; p++) {
                        int v = columns[cn][p];
                        if (v == 0) {
                            int q = getSquareForColumnNumberAndPosition(cn, p);
                            couldBes[p] = whatCouldBeHere(grid, q);
                        }
                    }
                    return qvForOneOfThree(grid, cn, couldBes, boxWithTwo);

                }
            }
        }
        return null;
    }

    private static int[] qvForOneOfThree(SudokuGrid grid, int cn,
                                         int[][] couldBes, int boxWithTwo) {
        int[][] couldBeByBox = new int[3][];
        int cp = -1;
        for (int p = 0; p < 9; p++) {
            if (couldBes[p] != null) {
                couldBeByBox[p / 3] = mergeIntArrays(couldBeByBox[p / 3],
                        couldBes[p]);
                if (p / 3 != boxWithTwo) {
                    cp = p;
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
            qv[0] = getSquareForColumnNumberAndPosition(cn, cp);
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

    private static int whichBoxHasTwoEmptySquares(int[] column) {
        int[] boxes = new int[3];
        for (int q = 0; q < column.length; q++) {
            if (column[q] == 0) {
                boxes[q / 3] = boxes[q / 3] + 1;
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
        return aSetOfSixHasTwoSquaresThatCouldBeTwoNumbers(grid);
    }

    @Override
    public String explanation() {
        return """
                Two of three vertically.
                This algorithm checks each column to see if six boxes have already
                been filled, and if one box of the three boxes containing the column
                has two unfilled squares. If so, the algorithm checks to see if
                the square in the box with one empty square has two numbers which
                could be added to the square, and if only one of those numbers is
                shared with the numbers that could be added to the two unfilled
                squares in the other box.
                """;
    }

}
