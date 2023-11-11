package algorithms;

import model.SudokuGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.SudokuGridChecker;
import service.SudokuGridObserver;
import service.SudokuGridSolver;
import view.SudokuGridView;

import static service.SudokuGridTransformer.findColNumForBoxNumAndPosNum;
import static service.SudokuGridTransformer.findRowNumForBoxNumAndPosNum;

public class CrossCheck extends SudokuGridSolver implements SudokuGridSolverAlgorithm {

    static final Logger logger = LoggerFactory.getLogger(CrossCheck.class);

    SudokuGridObserver observer;

    @Override
    public SudokuGrid solve(SudokuGrid grid) {
        grid = solveByCrossChecking(grid);
        return grid;
    }

    @Override
    public String explanation() {
        String e = "this algorithm looks at the grid to see which " +
                "numbers have not been added to every row, column, and " +
                "box and for each of those numbers, goes through every " +
                "square in the grid and looks at the two rows " + "and " +
                "columns adjacent to that square and sees if the number in " + "mind is already present in the other two rows and " + "columns. If so, then the number in mind is added to the " + "square.";


        return e;
    }

    private static int[] sortUnsolvedNumbersByMostCommon(int[] numbers) {
        int[] sorted = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        // TODO -
        //  sort
        //  the
        //  indexes
        return sorted;
    }

    private SudokuGrid solveByCrossChecking(SudokuGrid grid) {
        logger.info("solve by cross-checking");

        observer = new SudokuGridObserver();

        // order
        // numbers by most common
        int[] numbersInOrderOfMostCommon = new int[9];
        numbersInOrderOfMostCommon =
                sortUnsolvedNumbersByMostCommon(observer.getCountOfBoxesWithNumberIndexedByNumber(grid));

        for (int numberInMind : numbersInOrderOfMostCommon) {

            int boxNum = -1;
            // go to each box without that number
            for (int[] box : grid.getBoxes()) {
                boxNum++;
                boolean found =
                        SudokuGridChecker.checkSetForNumber(numberInMind, box);
                if (!found) {
                    // go to each empty square in that box
                    for (int j = 0; j < box.length; j++) {
                        squareWithZero:
                        if (box[j] == 0) {
                            // get the row of numbers for that square
                            int rowNum =
                                    findRowNumForBoxNumAndPosNum(boxNum,
                                            j);
                            int[] row = grid.getRows()[rowNum];
                            logger.debug(SudokuGridView.intArrayToString(row));

                            // get the column of numbers for that square
                            int colNum = findColNumForBoxNumAndPosNum(boxNum,
                                            j);
                            int[] column = grid.getColumns()[colNum];
                            logger.debug(SudokuGridView.intArrayToString(column));

                            // see if that number is in any other square in
                            // the same row
                            for (int i : row) {
                                if (i == numberInMind) {
                                    break squareWithZero;
                                }
                            }

                            // see if that number is in any other square in
                            // the same column
                            for (int i : column) {
                                if (i == numberInMind) {
                                    break squareWithZero;
                                }
                            }

                            // see if that number is in two other columns of
                            // the other two boxes above or below the current
                            // box
                            int[] otherTwoColumns =
                                    findTheNumbersOfTheTwoAdjacentColumnsOrRows(colNum);
                            int[] otherColOne =
                                    grid.getColumns()[otherTwoColumns[0]];
                            boolean inOtherColOne =
                                    SudokuGridChecker.checkSetForNumber(numberInMind, otherColOne);

                            int[] otherColTwo =
                                    grid.getColumns()[otherTwoColumns[1]];
                            boolean inOtherColTwo =
                                    SudokuGridChecker.checkSetForNumber(numberInMind, otherColTwo);

                            // see if that number is in two other rows of the
                            // two boxes horizontal to the current box
                            int[] otherTwoRows =
                                    findTheNumbersOfTheTwoAdjacentColumnsOrRows(rowNum);
                            int[] otherRowOne = grid.getRows()[otherTwoRows[0]];
                            boolean inOtherRowOne =
                                    SudokuGridChecker.checkSetForNumber(numberInMind, otherRowOne);

                            int[] otherRowTwo = grid.getRows()[otherTwoRows[1]];
                            boolean inOtherRowTwo =
                                    SudokuGridChecker.checkSetForNumber(numberInMind, otherRowTwo);

                            // if a number can be added to an empty square
                            // and cannot be added to any other square in the
                            // same box, row, or column
                            // then add that number to the empty square
                            if (inOtherColOne && inOtherColTwo && inOtherRowOne && inOtherRowTwo) {
                                box[j] = numberInMind;
                                int[][] boxes = grid.getBoxes();
                                boxes[boxNum] = box;
                                grid.setBoxes(boxes);
                                return grid;
                            }
                        }
                    }
                }
            }
        }
        return grid;
    }


    private static int[] findTheNumbersOfTheTwoAdjacentColumnsOrRows(int num) {
        int[] otherTwo = new int[2];
        switch (num) {
            case 0:
                otherTwo[0] = 1;
                otherTwo[1] = 2;
                break;
            case 1:
                otherTwo[0] = 0;
                otherTwo[1] = 2;
                break;
            case 2:
                otherTwo[0] = 0;
                otherTwo[1] = 1;
                break;
            case 3:
                otherTwo[0] = 4;
                otherTwo[1] = 5;
                break;
            case 4:
                otherTwo[0] = 3;
                otherTwo[1] = 5;
                break;
            case 5:
                otherTwo[0] = 3;
                otherTwo[1] = 4;
                break;
            case 6:
                otherTwo[0] = 7;
                otherTwo[1] = 8;
                break;
            case 7:
                otherTwo[0] = 6;
                otherTwo[1] = 8;
                break;
            case 8:
                otherTwo[0] = 6;
                otherTwo[1] = 7;
                break;
        }

        return otherTwo;
    }

}
