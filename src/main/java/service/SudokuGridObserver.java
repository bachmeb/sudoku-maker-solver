package service;

import model.SudokuGrid;

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
    public static int[] countFilledSquaresPerDimension(int[][] things) {
        int[] countOfFilledSquares = new int[things.length];
        int index = 0;
        for (int[] thing : things) {
            countOfFilledSquares[index] = countFilledSquaresInSet(thing);
            index++;
        }
        return countOfFilledSquares;
    }

    public static int countFilledSquaresInSet(int[] set) {
        int c = 0;
        for (int value : set) {
            if (value > 0) {
                c++;
            }
        }
        return c;
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

    public static int[] getCountOfBoxesWithNumberIndexedByNumber(SudokuGrid grid) {
        return countNumberAppearancesByDimension(grid.getBoxes());
    }

    public static boolean theInnerColumnHasMoreThanOneZero(int[] box0, int p) {
        int cn = getInnerColumnNumFromPosition(p);
        int[] c = getInnerColumnFromBox(box0, cn);
        int zeros = 0;
        for (int q : c) {
            if (q == 0) {
                zeros++;
            }
        }
        return zeros > 1;
    }

    public static int getSquareFromBoxInnerColumnNumAndInnerColumnPosition(int bn, int icn, int icp ){
        int innerRowPad = (icp /3)*3;
        int bp = (innerRowPad*3)+icn;
        int rowPad = (bn/3)*3;
        int cn = (bn - rowPad)*3;
        return rowPad + cn;
    }

    public static int[] getInnerColumnFromBox(int[] box, int cn) {
        int[][] columns = new int[3][3];
        int ci = 0;
        int ri = 0;
        for (int q : box) {
            columns[ci++][ri++ / 3] = q;
            if (ci > 2) {
                ci = 0;
            }
        }
        return columns[cn];
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

    public static int[] getVerticallyAdjacentSquaresInBox(int[] box,
                                                          int position) {
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

    public static int getInnerColumnNumFromPosition(int position) {
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
                if (couldBe.length > 0) {
                    System.arraycopy(couldBe, 0, newCouldBe, 0, couldBe.length);
                }
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

    public static int getBoxPositionFromSquare(int q) {
        int bn = getBoxNumForSquare(q);
        int rowPad = (bn / 3) * 3;
        int rn = getRowNumForSquare(q);
        int innerRow = rn - rowPad;
        int cn = getColumnNumForSquare(q);
        int columnPad = (cn / 3) * 3;
        int innerColumn = cn - columnPad;
        return (innerRow * 3) + innerColumn;
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

    public static int getSquareForBoxNumAndPosNum(int bn, int p) {
        int columnPad = getBoxColumnNumForBoxNum(bn) * 3;
        int innerColNum = getInnerColumnNumFromPosition(p);
        int columnNum = columnPad + innerColNum;
        int rowPad = getBoxRowNumForBoxNum(bn) * 3;
        int innerRowNum = getInnerRowNumFromPosition(p);
        int rowNum = rowPad + innerRowNum;
        return (rowNum * 9) + columnNum;
    }

    public static int getInnerRowNumFromPosition(int p) {
        return p / 3;
    }

    public static int getBoxRowNumForBoxNum(int bn) {
        return bn / 3;
    }

    public static int findRowNumForBoxNumAndPosNum(int boxNum, int posNum) {

        int[][] rowNumbersByBoxNumAndPosNum = new int[9][9];

        rowNumbersByBoxNumAndPosNum[0][0] = 0;
        rowNumbersByBoxNumAndPosNum[0][1] = 0;
        rowNumbersByBoxNumAndPosNum[0][2] = 0;
        rowNumbersByBoxNumAndPosNum[0][3] = 1;
        rowNumbersByBoxNumAndPosNum[0][4] = 1;
        rowNumbersByBoxNumAndPosNum[0][5] = 1;
        rowNumbersByBoxNumAndPosNum[0][6] = 2;
        rowNumbersByBoxNumAndPosNum[0][7] = 2;
        rowNumbersByBoxNumAndPosNum[0][8] = 2;

        rowNumbersByBoxNumAndPosNum[1][0] = 0;
        rowNumbersByBoxNumAndPosNum[1][1] = 0;
        rowNumbersByBoxNumAndPosNum[1][2] = 0;
        rowNumbersByBoxNumAndPosNum[1][3] = 1;
        rowNumbersByBoxNumAndPosNum[1][4] = 1;
        rowNumbersByBoxNumAndPosNum[1][5] = 1;
        rowNumbersByBoxNumAndPosNum[1][6] = 2;
        rowNumbersByBoxNumAndPosNum[1][7] = 2;
        rowNumbersByBoxNumAndPosNum[1][8] = 2;

        rowNumbersByBoxNumAndPosNum[2][0] = 0;
        rowNumbersByBoxNumAndPosNum[2][1] = 0;
        rowNumbersByBoxNumAndPosNum[2][2] = 0;
        rowNumbersByBoxNumAndPosNum[2][3] = 1;
        rowNumbersByBoxNumAndPosNum[2][4] = 1;
        rowNumbersByBoxNumAndPosNum[2][5] = 1;
        rowNumbersByBoxNumAndPosNum[2][6] = 2;
        rowNumbersByBoxNumAndPosNum[2][7] = 2;
        rowNumbersByBoxNumAndPosNum[2][8] = 2;

        ///

        rowNumbersByBoxNumAndPosNum[3][0] = 3;
        rowNumbersByBoxNumAndPosNum[3][1] = 3;
        rowNumbersByBoxNumAndPosNum[3][2] = 3;
        rowNumbersByBoxNumAndPosNum[3][3] = 4;
        rowNumbersByBoxNumAndPosNum[3][4] = 4;
        rowNumbersByBoxNumAndPosNum[3][5] = 4;
        rowNumbersByBoxNumAndPosNum[3][6] = 5;
        rowNumbersByBoxNumAndPosNum[3][7] = 5;
        rowNumbersByBoxNumAndPosNum[3][8] = 5;

        rowNumbersByBoxNumAndPosNum[4][0] = 3;
        rowNumbersByBoxNumAndPosNum[4][1] = 3;
        rowNumbersByBoxNumAndPosNum[4][2] = 3;
        rowNumbersByBoxNumAndPosNum[4][3] = 4;
        rowNumbersByBoxNumAndPosNum[4][4] = 4;
        rowNumbersByBoxNumAndPosNum[4][5] = 4;
        rowNumbersByBoxNumAndPosNum[4][6] = 5;
        rowNumbersByBoxNumAndPosNum[4][7] = 5;
        rowNumbersByBoxNumAndPosNum[4][8] = 5;

        rowNumbersByBoxNumAndPosNum[5][0] = 3;
        rowNumbersByBoxNumAndPosNum[5][1] = 3;
        rowNumbersByBoxNumAndPosNum[5][2] = 3;
        rowNumbersByBoxNumAndPosNum[5][3] = 4;
        rowNumbersByBoxNumAndPosNum[5][4] = 4;
        rowNumbersByBoxNumAndPosNum[5][5] = 4;
        rowNumbersByBoxNumAndPosNum[5][6] = 5;
        rowNumbersByBoxNumAndPosNum[5][7] = 5;
        rowNumbersByBoxNumAndPosNum[5][8] = 5;

        ///

        rowNumbersByBoxNumAndPosNum[6][0] = 6;
        rowNumbersByBoxNumAndPosNum[6][1] = 6;
        rowNumbersByBoxNumAndPosNum[6][2] = 6;
        rowNumbersByBoxNumAndPosNum[6][3] = 7;
        rowNumbersByBoxNumAndPosNum[6][4] = 7;
        rowNumbersByBoxNumAndPosNum[6][5] = 7;
        rowNumbersByBoxNumAndPosNum[6][6] = 8;
        rowNumbersByBoxNumAndPosNum[6][7] = 8;
        rowNumbersByBoxNumAndPosNum[6][8] = 8;

        rowNumbersByBoxNumAndPosNum[7][0] = 6;
        rowNumbersByBoxNumAndPosNum[7][1] = 6;
        rowNumbersByBoxNumAndPosNum[7][2] = 6;
        rowNumbersByBoxNumAndPosNum[7][3] = 7;
        rowNumbersByBoxNumAndPosNum[7][4] = 7;
        rowNumbersByBoxNumAndPosNum[7][5] = 7;
        rowNumbersByBoxNumAndPosNum[7][6] = 8;
        rowNumbersByBoxNumAndPosNum[7][7] = 8;
        rowNumbersByBoxNumAndPosNum[7][8] = 8;

        rowNumbersByBoxNumAndPosNum[8][0] = 6;
        rowNumbersByBoxNumAndPosNum[8][1] = 6;
        rowNumbersByBoxNumAndPosNum[8][2] = 6;
        rowNumbersByBoxNumAndPosNum[8][3] = 7;
        rowNumbersByBoxNumAndPosNum[8][4] = 7;
        rowNumbersByBoxNumAndPosNum[8][5] = 7;
        rowNumbersByBoxNumAndPosNum[8][6] = 8;
        rowNumbersByBoxNumAndPosNum[8][7] = 8;
        rowNumbersByBoxNumAndPosNum[8][8] = 8;

        return rowNumbersByBoxNumAndPosNum[boxNum][posNum];
    }

    public static int findColNumForBoxNumAndPosNum(int boxNum, int posNum) {

        int[][] columnNumbersByBoxNumAndPosNum = new int[9][9];

        columnNumbersByBoxNumAndPosNum[0][0] = 0;
        columnNumbersByBoxNumAndPosNum[0][1] = 1;
        columnNumbersByBoxNumAndPosNum[0][2] = 2;
        columnNumbersByBoxNumAndPosNum[0][3] = 0;
        columnNumbersByBoxNumAndPosNum[0][4] = 1;
        columnNumbersByBoxNumAndPosNum[0][5] = 2;
        columnNumbersByBoxNumAndPosNum[0][6] = 0;
        columnNumbersByBoxNumAndPosNum[0][7] = 1;
        columnNumbersByBoxNumAndPosNum[0][8] = 2;

        columnNumbersByBoxNumAndPosNum[1][0] = 3;
        columnNumbersByBoxNumAndPosNum[1][1] = 4;
        columnNumbersByBoxNumAndPosNum[1][2] = 5;
        columnNumbersByBoxNumAndPosNum[1][3] = 3;
        columnNumbersByBoxNumAndPosNum[1][4] = 4;
        columnNumbersByBoxNumAndPosNum[1][5] = 5;
        columnNumbersByBoxNumAndPosNum[1][6] = 3;
        columnNumbersByBoxNumAndPosNum[1][7] = 4;
        columnNumbersByBoxNumAndPosNum[1][8] = 5;

        columnNumbersByBoxNumAndPosNum[2][0] = 6;
        columnNumbersByBoxNumAndPosNum[2][1] = 7;
        columnNumbersByBoxNumAndPosNum[2][2] = 8;
        columnNumbersByBoxNumAndPosNum[2][3] = 6;
        columnNumbersByBoxNumAndPosNum[2][4] = 7;
        columnNumbersByBoxNumAndPosNum[2][5] = 8;
        columnNumbersByBoxNumAndPosNum[2][6] = 6;
        columnNumbersByBoxNumAndPosNum[2][7] = 7;
        columnNumbersByBoxNumAndPosNum[2][8] = 8;

        ///

        columnNumbersByBoxNumAndPosNum[3][0] = 0;
        columnNumbersByBoxNumAndPosNum[3][1] = 1;
        columnNumbersByBoxNumAndPosNum[3][2] = 2;
        columnNumbersByBoxNumAndPosNum[3][3] = 0;
        columnNumbersByBoxNumAndPosNum[3][4] = 1;
        columnNumbersByBoxNumAndPosNum[3][5] = 2;
        columnNumbersByBoxNumAndPosNum[3][6] = 0;
        columnNumbersByBoxNumAndPosNum[3][7] = 1;
        columnNumbersByBoxNumAndPosNum[3][8] = 2;

        columnNumbersByBoxNumAndPosNum[4][0] = 3;
        columnNumbersByBoxNumAndPosNum[4][1] = 4;
        columnNumbersByBoxNumAndPosNum[4][2] = 5;
        columnNumbersByBoxNumAndPosNum[4][3] = 3;
        columnNumbersByBoxNumAndPosNum[4][4] = 4;
        columnNumbersByBoxNumAndPosNum[4][5] = 5;
        columnNumbersByBoxNumAndPosNum[4][6] = 3;
        columnNumbersByBoxNumAndPosNum[4][7] = 4;
        columnNumbersByBoxNumAndPosNum[4][8] = 5;

        columnNumbersByBoxNumAndPosNum[5][0] = 6;
        columnNumbersByBoxNumAndPosNum[5][1] = 7;
        columnNumbersByBoxNumAndPosNum[5][2] = 8;
        columnNumbersByBoxNumAndPosNum[5][3] = 6;
        columnNumbersByBoxNumAndPosNum[5][4] = 7;
        columnNumbersByBoxNumAndPosNum[5][5] = 8;
        columnNumbersByBoxNumAndPosNum[5][6] = 6;
        columnNumbersByBoxNumAndPosNum[5][7] = 7;
        columnNumbersByBoxNumAndPosNum[5][8] = 8;

        ///

        columnNumbersByBoxNumAndPosNum[6][0] = 0;
        columnNumbersByBoxNumAndPosNum[6][1] = 1;
        columnNumbersByBoxNumAndPosNum[6][2] = 2;
        columnNumbersByBoxNumAndPosNum[6][3] = 0;
        columnNumbersByBoxNumAndPosNum[6][4] = 1;
        columnNumbersByBoxNumAndPosNum[6][5] = 2;
        columnNumbersByBoxNumAndPosNum[6][6] = 0;
        columnNumbersByBoxNumAndPosNum[6][7] = 1;
        columnNumbersByBoxNumAndPosNum[6][8] = 2;

        columnNumbersByBoxNumAndPosNum[7][0] = 3;
        columnNumbersByBoxNumAndPosNum[7][1] = 4;
        columnNumbersByBoxNumAndPosNum[7][2] = 5;
        columnNumbersByBoxNumAndPosNum[7][3] = 3;
        columnNumbersByBoxNumAndPosNum[7][4] = 4;
        columnNumbersByBoxNumAndPosNum[7][5] = 5;
        columnNumbersByBoxNumAndPosNum[7][6] = 3;
        columnNumbersByBoxNumAndPosNum[7][7] = 4;
        columnNumbersByBoxNumAndPosNum[7][8] = 5;

        columnNumbersByBoxNumAndPosNum[8][0] = 6;
        columnNumbersByBoxNumAndPosNum[8][1] = 7;
        columnNumbersByBoxNumAndPosNum[8][2] = 8;
        columnNumbersByBoxNumAndPosNum[8][3] = 6;
        columnNumbersByBoxNumAndPosNum[8][4] = 7;
        columnNumbersByBoxNumAndPosNum[8][5] = 8;
        columnNumbersByBoxNumAndPosNum[8][6] = 6;
        columnNumbersByBoxNumAndPosNum[8][7] = 7;
        columnNumbersByBoxNumAndPosNum[8][8] = 8;

        return columnNumbersByBoxNumAndPosNum[boxNum][posNum];
    }

    public static int getSquareForColumnNumberAndPosition(int cn, int p) {
        int rowPad = p * 9;
        return rowPad + cn;
    }

    public static int getSquareForRowNumberAndPosition(int rn, int p) {
        int rowPad = rn * 9;
        return rowPad + p;
    }

    public static int findMissingNumberInSet(int[] set) {
        int sumOfNumbersInSet = 0;
        for (int j : set) {
            sumOfNumbersInSet += j;
        }
        int sumOfAllNumbers = 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9;
        return sumOfAllNumbers - sumOfNumbersInSet;
    }

}
