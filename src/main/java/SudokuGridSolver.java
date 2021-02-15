import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuGridSolver {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridSolver.class);

    SudokuGrid grid;

    int countOfBoxesWithNumberIndexedByNumber[] = new int[10];
    int countOfRowsWithNumberIndexedByNumber[] = new int[10];
    int countOfColumnsWithNumberIndexedByNumber[] = new int[10];

    int countOfFilledSquaresIndexedByBoxNumber[] = new int[9];
    int countOfFilledSquaresIndexedByRowNumber[] = new int[9];
    int countOfFilledSquaresIndexedByColumnNumber[] = new int[9];

    public SudokuGrid solve(SudokuGrid grid) {
        this.grid = grid;
        int loop = 0;
        boolean solved;

        SudokuGridChecker checker = new SudokuGridChecker();

        // does the grid have any errors?
        boolean hasError = checker.checkGridForErrors(grid);

        if(hasError){
            logger.info("this grid has more than one of the same number in a box, row, or column");
            return grid;
        }

        // is the grid solved?
        solverLoop:
        while (true) {

            solved = checker.checkGridSolved(grid);

            if (solved) {
                break solverLoop;
            }

            logger.info("loop number: " + loop++ + " - not yet solved");

            reCountNumbersInSquares();

            // if the number of numbers in a given box is 8 then add the last number
            for (int i = 0; i < countOfFilledSquaresIndexedByBoxNumber.length; i++) {
                if (countOfFilledSquaresIndexedByBoxNumber[i] == 8) {
                    logger.info("There are 8 squares filled in box number " + i);
                    int set[] = addTheLastNumberToTheSet(grid.getBoxes()[i]);
                    int[][] boxes = grid.getBoxes();
                    boxes[i] = set;
                    grid.setBoxes(boxes);
                    continue solverLoop;
                }
            }

            // if the number of numbers in a given row is 8 then add the last number
            for (int i = 0; i < countOfFilledSquaresIndexedByRowNumber.length; i++) {
                if (countOfFilledSquaresIndexedByRowNumber[i] == 8) {
                    logger.info("There are 8 squares filled in row number " + i);
                    int set[] = addTheLastNumberToTheSet(grid.getRows()[i]);
                    int[][] rows = grid.getRows();
                    rows[i] = set;
                    grid.setRows(rows);
                    continue solverLoop;
                }
            }

            // if the number of numbers in a given column is 8 then add the last number
            for (int i = 0; i < countOfFilledSquaresIndexedByColumnNumber.length; i++) {
                if (countOfFilledSquaresIndexedByColumnNumber[i] == 8) {
                    logger.info("There are 8 squares filled in column number " + i);
                    int set[] = addTheLastNumberToTheSet(grid.getColumns()[i]);
                    int[][] columns = grid.getColumns();
                    columns[i] = set;
                    grid.setColumns(columns);
                    continue solverLoop;
                }
            }

            // order numbers by most common
            int[] numbersInOrderOfMostCommon = new int[9];
            numbersInOrderOfMostCommon = sortUnsolvedNumbersByMostCommon(countOfBoxesWithNumberIndexedByNumber);

            for (int numberInMind : numbersInOrderOfMostCommon) {

                int boxNum = -1;
                // go to each box without that number
                for (int[] box : grid.getBoxes()) {
                    boxNum++;
                    boolean found = checkSetForNumber(numberInMind, box);
                    if (!found) {
                        // go to each empty square in that box
                        for (int j = 0; j < box.length; j++) {
                            squareWithZero:
                            if (box[j] == 0) {
                                // get the row of numbers for that square
                                int rowNum = grid.findRowNumForBoxNumAndPosNum(boxNum, j);
                                int[] row = grid.getRows()[rowNum];
                                logger.info(intArrayToString(row));

                                // get the column of numbers for that square
                                int colNum = grid.findColNumForBoxNumAndPosNum(boxNum, j);
                                int[] column = grid.getColumns()[colNum];
                                logger.info(intArrayToString(column));

                                // see if that number is in any other square in the same row
                                for (int r = 0; r < row.length; r++) {
                                    if (row[r] == numberInMind) {
                                        break squareWithZero;
                                    }
                                }

                                // see if that number is in any other square in the same column
                                for (int c = 0; c < column.length; c++) {
                                    if (column[c] == numberInMind) {
                                        break squareWithZero;
                                    }
                                }

                                // see if that number is in two other columns of the other two boxes above or below the current box
                                int[] otherTwoColumns = findTheNumbersOfTheTwoAdjacentColumnsOrRows(colNum);
                                int[] otherColOne = grid.getColumns()[otherTwoColumns[0]];
                                boolean inOtherColOne = checkSetForNumber(numberInMind, otherColOne);

                                int[] otherColTwo = grid.getColumns()[otherTwoColumns[1]];
                                boolean inOtherColTwo = checkSetForNumber(numberInMind, otherColTwo);

                                // see if that number is in two other rows of the two boxes horizontal to the current box
                                int[] otherTwoRows = findTheNumbersOfTheTwoAdjacentColumnsOrRows(rowNum);
                                int[] otherRowOne = grid.getRows()[otherTwoRows[0]];
                                boolean inOtherRowOne = checkSetForNumber(numberInMind, otherRowOne);

                                int[] otherRowTwo = grid.getRows()[otherTwoRows[1]];
                                boolean inOtherRowTwo = checkSetForNumber(numberInMind, otherRowTwo);

                                // determine if that number can be added to any other square in the same box

                                // if a number can be added to an empty square and cannot be added to any other square in the same box, row, or square
                                // then add that number to the empty square
                                if (inOtherColOne && inOtherColTwo && inOtherRowOne && inOtherRowTwo) {
                                    box[j] = numberInMind;
                                    int[][] boxes = grid.getBoxes();
                                    boxes[boxNum] = box;
                                    grid.setBoxes(boxes);
                                }
                            }
                        }
                    }
                }

            }

            // quit after x number of loops so as not to go on infinitely
            if (loop == 100) {
                logger.info("I give up after " + loop + " loops!");
                logger.info(grid.toString());
                return grid;
            }

        }

        if (solved) {
            logger.info("This grid is solved after " + loop + " loops!!!");
        }
        logger.info(grid.toString());

        return grid;

    }

    private int[] sortUnsolvedNumbersByMostCommon(int[] numbers) {
        int[] sorted = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        // TODO - sort the indexes
        return sorted;
    }

    private int[] findTheNumbersOfTheTwoAdjacentColumnsOrRows(int num) {
        int[] otherTwo = new int[2];
        switch (num) {
            case 0:
                otherTwo[0] = 1;
                otherTwo[1] = 2;
            case 1:
                otherTwo[0] = 0;
                otherTwo[1] = 2;
            case 2:
                otherTwo[0] = 0;
                otherTwo[1] = 1;
            case 3:
                otherTwo[0] = 4;
                otherTwo[1] = 5;
            case 4:
                otherTwo[0] = 3;
                otherTwo[1] = 5;
            case 5:
                otherTwo[0] = 3;
                otherTwo[1] = 4;
            case 6:
                otherTwo[0] = 7;
                otherTwo[1] = 8;
            case 7:
                otherTwo[0] = 6;
                otherTwo[1] = 8;
            case 8:
                otherTwo[0] = 6;
                otherTwo[1] = 7;
        }

        return otherTwo;
    }


    private int[] findTheNumbersOfTheTwoOtherHorizontallyAdjacentSquares(int num) {
        int[] otherTwo = new int[2];
        switch (num) {
            case 0:
                otherTwo[0] = 1;
                otherTwo[1] = 2;
            case 1:
                otherTwo[0] = 0;
                otherTwo[1] = 2;
            case 2:
                otherTwo[0] = 0;
                otherTwo[1] = 1;
            case 3:
                otherTwo[0] = 4;
                otherTwo[1] = 5;
            case 4:
                otherTwo[0] = 3;
                otherTwo[1] = 5;
            case 5:
                otherTwo[0] = 3;
                otherTwo[1] = 4;
            case 6:
                otherTwo[0] = 7;
                otherTwo[1] = 8;
            case 7:
                otherTwo[0] = 6;
                otherTwo[1] = 8;
            case 8:
                otherTwo[0] = 6;
                otherTwo[1] = 7;
        }

        return otherTwo;

    }

    private int[] findTheNumbersOfTheTwoOtherVerticallyAdjacentSquares(int num) {
        int[] otherTwo = new int[2];
        switch (num) {
            case 0:
                otherTwo[0] = 3;
                otherTwo[1] = 6;
            case 1:
                otherTwo[0] = 4;
                otherTwo[1] = 7;
            case 2:
                otherTwo[0] = 5;
                otherTwo[1] = 8;
            case 3:
                otherTwo[0] = 0;
                otherTwo[1] = 6;
            case 4:
                otherTwo[0] = 1;
                otherTwo[1] = 7;
            case 5:
                otherTwo[0] = 2;
                otherTwo[1] = 8;
            case 6:
                otherTwo[0] = 0;
                otherTwo[1] = 3;
            case 7:
                otherTwo[0] = 1;
                otherTwo[1] = 4;
            case 8:
                otherTwo[0] = 2;
                otherTwo[1] = 5;
        }

        return otherTwo;

    }

    private boolean checkSetForNumber(int findNum, int[] set) {

        for (int i = 0; i < set.length; i++) {
            if (set[i] == findNum) {
                return true;
            }
        }
        return false;
    }

    public void reCountNumbersInSquares() {

        countOfBoxesWithNumberIndexedByNumber = new int[10];
        countOfRowsWithNumberIndexedByNumber = new int[10];
        countOfColumnsWithNumberIndexedByNumber = new int[10];

        countOfFilledSquaresIndexedByBoxNumber = new int[9];
        countOfFilledSquaresIndexedByRowNumber = new int[9];
        countOfFilledSquaresIndexedByColumnNumber = new int[9];


        // count the number of times each number appears in any box
        for (int num = 1; num < 10; num++) {
            countOfBoxesWithNumberIndexedByNumber[num] = 0;
            for (int[] box : grid.getBoxes()) {
                for (int i = 0; i < 9; i++) {
                    if (num == box[i]) {
                        countOfBoxesWithNumberIndexedByNumber[num]++;
                    }
                }
                logger.debug("There are " + countOfBoxesWithNumberIndexedByNumber[num] + " boxes with the number " + num + " - " + intArrayToString(box));
            }
        }

        logger.debug(countOfBoxesWithNumberIndexedByNumber.toString());

        // count the number of times each number appears in any row
        for (int num = 1; num < 10; num++) {
            countOfRowsWithNumberIndexedByNumber[num] = 0;
            for (int[] row : grid.getRows()) {
                for (int i = 0; i < 9; i++) {
                    if (num == row[i]) {
                        countOfRowsWithNumberIndexedByNumber[num]++;
                    }
                }
                logger.debug("There are " + countOfRowsWithNumberIndexedByNumber[num] + " rows with the number " + num + " - " + intArrayToString(row));
            }
        }

        // count the number of times each number appears in any column
        for (int num = 1; num < 10; num++) {
            countOfColumnsWithNumberIndexedByNumber[num] = 0;
            for (int[] column : grid.getColumns()) {
                for (int i = 0; i < 9; i++) {
                    if (num == column[i]) {
                        countOfColumnsWithNumberIndexedByNumber[num]++;
                    }
                }
                logger.debug("There are " + countOfColumnsWithNumberIndexedByNumber[num] + " columns with the number " + num + " - " + intArrayToString(column));
            }
        }

        // count the number of filled squares in each box
        int boxNum = 0;
        for (int[] box : grid.getBoxes()) {
            countOfFilledSquaresIndexedByBoxNumber[boxNum] = 0;
            for (int sudNum : box) {
                if (sudNum > 0) {
                    countOfFilledSquaresIndexedByBoxNumber[boxNum]++;
                }
            }
            logger.debug("There are " + countOfFilledSquaresIndexedByBoxNumber[boxNum] + " squares filled in box number " + boxNum + " - " + intArrayToString(box));
            boxNum++;
        }


        // count the number of squares filled in each row
        int rowNum = 0;
        for (int[] row : grid.getRows()) {
            countOfFilledSquaresIndexedByRowNumber[rowNum] = 0;
            for (int sudNum : row) {
                if (sudNum > 0) {
                    countOfFilledSquaresIndexedByRowNumber[rowNum]++;
                }
            }
            logger.debug("There are " + countOfFilledSquaresIndexedByRowNumber[rowNum] + " squares filled in row number " + boxNum + " - " + intArrayToString(row));
            rowNum++;
        }

        // count the number of squares filled in each column
        int colNum = 0;
        for (int[] column : grid.getColumns()) {
            countOfFilledSquaresIndexedByColumnNumber[colNum] = 0;
            for (int sudNum : column) {
                if (sudNum > 0) {
                    countOfFilledSquaresIndexedByColumnNumber[colNum]++;
                }
            }
            logger.debug("There are " + countOfFilledSquaresIndexedByColumnNumber[colNum] + " squares filled in column number " + colNum + " - " + intArrayToString(column));
            colNum++;
        }
    }

    public int[] addTheLastNumberToTheSet(int[] set) {
        // make sure the set is not null
        if (set == null) {
            throw new RuntimeException();
        }
        // make sure the set has a length of 9
        if (set.length != 9) {
            throw new RuntimeException("There are only 9 squares in every set");
        }
        // make sure the set has 8 numbers
        int countOfNumbers = 0;
        for (int i = 0; i < set.length; i++) {
            if (set[i] > 0) {
                countOfNumbers++;
            }
        }
        if (countOfNumbers != 8) {
            throw new RuntimeException("there are supposed to be 8 squares with a number");
        }
        // find the missing number
        int missingNumber = 0;
        int sumOfNumbersInSet = 0;
        for (int i = 0; i < set.length; i++) {
            sumOfNumbersInSet += set[i];
        }
        int sumOfAllNumbers = 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9;

        missingNumber = sumOfAllNumbers - sumOfNumbersInSet;

        // add the missing number to the set
        for (int i = 0; i < set.length; i++) {
            if (set[i] == 0) {
                set[i] = missingNumber;
                logger.debug("Added missing number " + missingNumber + " to the set");
                break;
            }
        }

        return set;

    }

    private String intArrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();

        if (arr == null) {
            return "[]";
        }

        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        sb.append("]");
        return sb.toString();
    }

}
