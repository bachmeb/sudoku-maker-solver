package service;

import model.SudokuGrid;

import static service.SudokuGridChecker.checkBoxAndAdjacentRowsAndColumnsForNumber;
import static service.SudokuGridChecker.checkSetForNumber;

public class SudokuGridObserver {

    /**
     * @param grid a SudokuGrid object
     * @return an integer representing the number of squares filled with a
     * value other than 0
     */
    public static int countAllFilledSquares(SudokuGrid grid) {
        int count = 0;
        for (int square : grid.getSquares()) {
            if (square > 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * @param things an array of integer arrays
     * @return an array of integers each representing the number of squares
     * in each array that are filled with a value other than 0
     */
    private static int[] countFilledSquaresPerDimension(int[][] things) {
        int[] countOfFilledSquares = new int[things.length];
        int index = 0;
        for (int[] thing : things) {
            countOfFilledSquares[index] = 0;
            for (int value : thing) {
                if (value > 0) {
                    countOfFilledSquares[index]++;
                }
            }
            index++;
        }
        return countOfFilledSquares;
    }

    /**
     * count the number of times each number appears in any slice of the
     * given dimension
     *
     * @param slices an array of integer arrays
     * @return an array with 10 values each representing a count of each
     * occurrence of the number represented by the index of the value in the
     * array
     */
    public static int[] countNumberAppearancesByDimension(int[][] slices) {

        int[] summary = new int[10];
        for (int num = 1; num < 10; num++) {
            summary[num] = 0;
            for (int[] slice : slices) {
                for (int i = 0; i < 9; i++) {
                    if (num == slice[i]) {
                        summary[num]++;
                    }
                }
            }
        }
        return summary;
    }

    public static int[] countValuesInBoxes(SudokuGrid grid) {
        return countFilledSquaresPerDimension(grid.getBoxes());
    }

    public static int[] countValuesInColumns(SudokuGrid grid) {
        return countFilledSquaresPerDimension(grid.getColumns());
    }

    public static int[] countValuesInRows(SudokuGrid grid) {
        return countFilledSquaresPerDimension(grid.getRows());
    }

    public static int[] getCountOfBoxesWithNumberIndexedByNumber(SudokuGrid grid) {
        return countNumberAppearancesByDimension(grid.getBoxes());
    }

    /**
     * @param grid
     * @return
     */
    public static boolean aSquareCanBeSolvedByOnlyOne(SudokuGrid grid) {
        int[] boxes = countNumberAppearancesByDimension(grid.getBoxes());
        int[] columns = countNumberAppearancesByDimension(grid.getColumns());
        int[] rows = countNumberAppearancesByDimension(grid.getRows());
        for (int i = 1; i < boxes.length; i++) {
            if ((boxes[i] == 8)) {
                return true;
            }
            if ((columns[i] == 8)) {
                return true;
            }
            if ((rows[i] == 8)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param grid
     * @return
     */
    public static boolean aDimensionHasEight(SudokuGrid grid) {
        // Check columns for sets of eight
        for (int j : countValuesInColumns(grid)) {
            if (j == 8) {
                return true;
            }
        }
        // Check rows for sets of eight
        for (int j : countValuesInRows(grid)) {
            if (j == 8) {
                return true;
            }
        }
        // Check boxes for sets of eight
        for (int j : countValuesInBoxes(grid)) {
            if (j == 8) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param grid
     * @return
     */
    public static boolean aSquareCanBeSolvedByAdjacentElimination(SudokuGrid grid) {
        int[] squares = grid.getSquares();
        for (int q = 0; q < squares.length; q++) {
            for (int n = 1; n < 10; n++) {
                if (checkBoxAndAdjacentRowsAndColumnsForNumber(grid, q, n)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean aBoxOnlyHasOneSquareLeftForANumber(SudokuGrid grid) {
        int[][] boxes = grid.getBoxes();
        for (int b = 0; b < boxes.length; b++) {
            int[] box0 = boxes[b];
            int[] verticallyAdjacentBoxes = getVerticallyAdjacentBoxNumbers(b);
            int[] box1 = boxes[verticallyAdjacentBoxes[0]];
            int[] box2 = boxes[verticallyAdjacentBoxes[1]];
            for (int p = 0; p < 9; p++) {
                if (box0[p] == 0) {
                    if (theOtherTwoBoxesHaveTheSameNumberInTheAdjacentColumns(box1, box2, p)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean theOtherTwoBoxesHaveTheSameNumberInTheAdjacentColumns(int[] box1, int[] box2, int position) {
        for (int n = 1; n < 10; n++) {
            int[] vAdjacent1 = getVerticallyAdjacentSquaresInBox(box1,
                    position);
            int[] vAdjacent2 = getVerticallyAdjacentSquaresInBox(box2,
                    position);
            if (checkSetForNumber(n, vAdjacent1) && checkSetForNumber(n,
                    vAdjacent2)) {
                return true;
            }
        }
        return false;
    }

    public static int[] getVerticallyAdjacentSquaresInBox(int[] box, int position) {
        int innerColumn = getInnerColumnNumFromPosition(position);
        int[] adjacentSquares = new int[6];
        int index = 0;
        for (int i = 0; i < 9; i++) {
            if (innerColumn == getInnerColumnNumFromPosition(i)) {
                continue;
            }
            adjacentSquares[index++] = box[i];
        }
        return adjacentSquares;
    }

    private static int getInnerColumnNumFromPosition(int position) {
        int innerRowNum = position / 3;
        int minus = innerRowNum * 3;
        return position - minus;
    }

    public static int[] getVerticallyAdjacentBoxNumbers(int b) {
        int columnNum = getBoxColumnNumForBoxNum(b);
        int[] adjacent = new int[2];
        int index = 0;
        for (int i = columnNum; i < 9; i += 3) {
            if (i == b) {
                continue;
            }
            adjacent[index++] = i;
        }
        return adjacent;
    }

    public static int getBoxColumnNumForBoxNum(int b) {
        int boxRow = b / 3;
        int minus = boxRow * 3;
        return b - minus;
    }

    public static int[] lookThreeWays(SudokuGrid grid, int q) {
        int[] row = grid.getRowForSquare(q);
        int[] column = grid.getColumnForSquare(q);
        int[] box = grid.getBoxForSquare(q);
        int[] whatIs = new int[10];
        for (int j = 0; j < row.length; j++) {
            whatIs[row[j]] = whatIs[row[j]] + 1;
            whatIs[column[j]] = whatIs[column[j]] + 1;
            whatIs[box[j]] = whatIs[box[j]] + 1;
        }
        return whatIs;
    }

    public static int[] lookTwoWays(SudokuGrid grid, int q) {
        int[] row = grid.getRowForSquare(q);
        int[] column = grid.getColumnForSquare(q);
        int[] whatIs = new int[10];
        for (int j = 0; j < row.length; j++) {
            whatIs[row[j]] = whatIs[row[j]] + 1;
            whatIs[column[j]] = whatIs[column[j]] + 1;
        }
        return whatIs;
    }

    public static int[] whatCouldBeHere(SudokuGrid grid, int q) {
        int[] whatIs = lookThreeWays(grid, q);
        int[] couldBe = new int[0];
        for (int i = 0; i < whatIs.length; i++) {
            if (whatIs[i] == 0) {
                int[] newCouldBe = new int[couldBe.length + 1];
                System.arraycopy(couldBe, 0, newCouldBe, 0, couldBe.length);
                couldBe = newCouldBe;
                couldBe[couldBe.length - 1] = i;
            }
        }
        return couldBe;
    }

    /**
     * This method returns the box number for a given square in a sudoku grid.
     * <br/>
     * <br/>
     * This method gets the row number and column number for a square and
     * with then calculates the box row and box column by dividing each by 3
     * (using Java integer division which removes the fractional part after
     * the whole number). The box number is then calculated by taking the box
     * column number and adding the product of the box row number times 3.
     *
     * @param square integer representing the position of a square in a
     *               sudoku grid
     * @return integer representing the position of the box corresponding to
     * the square in a sudoku grid.
     */
    public static int getBoxNumForSquare(int square) {
        int row = getRowNumForSquare(square);
        int boxRow = row / 3;
        int column = getColumnNumForSquare(square);
        int boxColumn = column / 3;
        return boxColumn + (3 * boxRow);
    }

    /**
     * This method returns the column number for a given square number in a
     * sudoku grid by identifying the row of the square and then subtracting
     * the row number times 9 from the square number. Basically this removes
     * every whole row
     * from the grid prior to the position of the square, and then counts the
     * position of the square from the first position in the final row.
     *
     * @param square integer representing the position of a square in a
     *               sudoku grid.
     * @return integer representing the column number corresponding to a
     * square in a sudoku grid.
     */
    public static int getColumnNumForSquare(int square) {
        int row = getRowNumForSquare(square);
        int minus = row * 9;
        return square - minus;
    }

    /**
     * This method uses integer division in Java to return a value
     * without the fractional part after the whole number. This simple method
     * works to identify the row for a square in a sudoku grid because rows
     * and squares are numbered beginning with 0, and division of a number by
     * a number greater than that number will result in a fraction of that
     * number, such as 0.333333, so every numbered square in the first row
     * will return 0 when divided by 9, because every number in the first row
     * is less than 9.
     *
     * @param square an integer representing the position of a square in a
     *               sudoku grid
     * @return an integer representing the row corresponding to the square in
     * a sudoku grid
     */
    public static int getRowNumForSquare(int square) {
        return (square / 9);
    }

}
