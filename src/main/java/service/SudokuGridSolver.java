package service;

import algorithms.SolveByCrossChecking;
import algorithms.SolveSetsOfEight;
import model.SudokuGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.SudokuGridView;

public class SudokuGridSolver2 implements SudokuGridSolver {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridSolver2.class);

    SudokuGrid grid;

    int[] countOfBoxesWithNumberIndexedByNumber = new int[10];
    int[] countOfRowsWithNumberIndexedByNumber = new int[10];
    int[] countOfColumnsWithNumberIndexedByNumber = new int[10];

    int[] countOfFilledSquaresIndexedByBoxNumber = new int[9];
    int[] countOfFilledSquaresIndexedByRowNumber = new int[9];
    int[] countOfFilledSquaresIndexedByColumnNumber = new int[9];

    @Override
    public SudokuGrid solve(SudokuGrid grid) {
        this.grid = grid;
        int loop = 0;
        boolean solved;

        SudokuGridChecker checker = new SudokuGridChecker();

        // is the grid solved?
        solverLoop:
        while (true) {

            solved = checker.checkGridSolved(grid);

            if (solved) {
                break solverLoop;
            }

            logger.info("loop number: " + loop++ + " - not yet solved");

            // does the grid have any errors?
            boolean hasError = checker.checkGridForErrors(grid);

            if (hasError) {
                logger.info("this grid has more than one of the same number in a box, row, or column");
                return grid;
            }

            SudokuGridSolver solver = new SolveSetsOfEight();
            grid = solver.solve(grid);
            solved = checker.checkGridSolved(grid);

            if (solved) {
                break solverLoop;
            }

            solver = new SolveByCrossChecking();
            grid = solver.solve(grid);
            solved = checker.checkGridSolved(grid);
            if (solved) {
                break solverLoop;
            }


            //TODO gotOne = solveByEliminationInAdjacentBoxesHorizontal();
            if (solved) {
                break solverLoop;
            }


            //TODO gotOne = solveByEliminationInAdjacentBoxesVertical();
            if (solved) {
                break solverLoop;
            }


            // quit if we get this far because every method has been tried
            logger.info("I give up after " + loop + " loops! I've tried everything I know how to do...");
            logger.info(grid.toString());
            return grid;

        }

        if (solved) {
            logger.info("This grid is solved after " + loop + " loops!!!");
        }
        logger.info(grid.toString());
        return grid;

    }


    public void reCountNumbersInSquares(SudokuGrid grid) {

        countAppearancesByBox(grid);
        countAppearancesByRow(grid);
        countAppearancesByColumn(grid);
        // count the number of filled squares in each box
        countFilledSquares(grid.getBoxes(), countOfFilledSquaresIndexedByBoxNumber, " squares filled in box number ");

        // count the number of squares filled in each row
        countFilledSquares(grid.getRows(), countOfFilledSquaresIndexedByRowNumber, " squares filled in row number ");

        // count the number of squares filled in each column
        countFilledSquares(grid.getColumns(), countOfFilledSquaresIndexedByColumnNumber, " squares filled in column number ");
    }

    private void countFilledSquares(int[][] things, int[] countOfFilledSquaresInThing, String s) {
        int thingNum = 0;
        for (int[] thing : things) {
            countOfFilledSquaresInThing[thingNum] = 0;
            for (int sudNum : thing) {
                if (sudNum > 0) {
                    countOfFilledSquaresInThing[thingNum]++;
                }
            }
            logger.debug("There are " + countOfFilledSquaresInThing[thingNum] + s + thingNum + " - " + SudokuGridView.intArrayToString(thing));
            thingNum++;
        }
    }

    private void countAppearancesByColumn(SudokuGrid grid) {
        // count the number of times each number appears in any column
        for (int num = 1; num < 10; num++) {
            countOfColumnsWithNumberIndexedByNumber[num] = 0;
            for (int[] column : grid.getColumns()) {
                for (int i = 0; i < 9; i++) {
                    if (num == column[i]) {
                        countOfColumnsWithNumberIndexedByNumber[num]++;
                    }
                }
                logger.debug("There are " + countOfColumnsWithNumberIndexedByNumber[num] + " columns with the number " + num + " - " + SudokuGridView.intArrayToString(column));
            }
        }
    }

    private void countAppearancesByRow(SudokuGrid grid) {
        // count the number of times each number appears in any row
        for (int num = 1; num < 10; num++) {
            countOfRowsWithNumberIndexedByNumber[num] = 0;
            for (int[] row : grid.getRows()) {
                for (int i = 0; i < 9; i++) {
                    if (num == row[i]) {
                        countOfRowsWithNumberIndexedByNumber[num]++;
                    }
                }
                logger.debug("There are " + countOfRowsWithNumberIndexedByNumber[num] + " rows with the number " + num + " - " + SudokuGridView.intArrayToString(row));
            }
        }
    }

    private void countAppearancesByBox(SudokuGrid grid) {
        // count the number of times each number appears in any box
        for (int num = 1; num < 10; num++) {
            countOfBoxesWithNumberIndexedByNumber[num] = 0;
            for (int[] box : grid.getBoxes()) {
                for (int i = 0; i < 9; i++) {
                    if (num == box[i]) {
                        countOfBoxesWithNumberIndexedByNumber[num]++;
                    }
                }
                logger.debug("There are " + countOfBoxesWithNumberIndexedByNumber[num] + " boxes with the number " + num + " - " + SudokuGridView.intArrayToString(box));
            }
        }

        logger.debug(countOfBoxesWithNumberIndexedByNumber.toString());
    }

    public int[] getCountOfBoxesWithNumberIndexedByNumber() {
        return countOfBoxesWithNumberIndexedByNumber;
    }

    void setCountOfBoxesWithNumberIndexedByNumber(int[] countOfBoxesWithNumberIndexedByNumber) {
        this.countOfBoxesWithNumberIndexedByNumber = countOfBoxesWithNumberIndexedByNumber;
    }

    public int[] getCountOfRowsWithNumberIndexedByNumber() {
        return countOfRowsWithNumberIndexedByNumber;
    }

    void setCountOfRowsWithNumberIndexedByNumber(int[] countOfRowsWithNumberIndexedByNumber) {
        this.countOfRowsWithNumberIndexedByNumber = countOfRowsWithNumberIndexedByNumber;
    }

    public  int[] getCountOfColumnsWithNumberIndexedByNumber() {
        return countOfColumnsWithNumberIndexedByNumber;
    }

    void setCountOfColumnsWithNumberIndexedByNumber(int[] countOfColumnsWithNumberIndexedByNumber) {
        this.countOfColumnsWithNumberIndexedByNumber = countOfColumnsWithNumberIndexedByNumber;
    }
    
    public  int[] getCountOfFilledSquaresIndexedByBoxNumber() {
        return countOfFilledSquaresIndexedByBoxNumber;
    }

    void setCountOfFilledSquaresIndexedByBoxNumber(int[] countOfFilledSquaresIndexedByBoxNumber) {
        this.countOfFilledSquaresIndexedByBoxNumber = countOfFilledSquaresIndexedByBoxNumber;
    }

    public  int[] getCountOfFilledSquaresIndexedByRowNumber() {
        return countOfFilledSquaresIndexedByRowNumber;
    }

    void setCountOfFilledSquaresIndexedByRowNumber(int[] countOfFilledSquaresIndexedByRowNumber) {
        this.countOfFilledSquaresIndexedByRowNumber = countOfFilledSquaresIndexedByRowNumber;
    }

    public  int[] getCountOfFilledSquaresIndexedByColumnNumber() {
        return countOfFilledSquaresIndexedByColumnNumber;
    }

    void setCountOfFilledSquaresIndexedByColumnNumber(int[] countOfFilledSquaresIndexedByColumnNumber) {
        this.countOfFilledSquaresIndexedByColumnNumber = countOfFilledSquaresIndexedByColumnNumber;
    }


}
