package service;

import model.SudokuGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.SudokuGridView;

public class SudokuGridObserver {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridObserver.class);

    SudokuGrid grid;

    int countOfAllFilledSquares;

    int[] countOfNumberAppearancesByBoxIndexedByNumber = new int[10];
    int[] countOfRowsWithNumberIndexedByNumber = new int[10];
    int[] countOfColumnsWithNumberIndexedByNumber = new int[10];

    int[] countOfFilledSquaresIndexedByBoxNumber = new int[9];
    int[] countOfFilledSquaresIndexedByRowNumber = new int[9];
    int[] countOfFilledSquaresIndexedByColumnNumber = new int[9];

    public SudokuGridObserver(SudokuGrid grid) {
        this.grid = grid;
    }

    public void reCountNumbersInSquares() {

        countOfAllFilledSquares = countAllFilledSquares();
        countNumberAppearancesByDimension( countOfNumberAppearancesByBoxIndexedByNumber, "boxes");
        countNumberAppearancesByDimension(countOfColumnsWithNumberIndexedByNumber, "columns");
        countNumberAppearancesByDimension( countOfRowsWithNumberIndexedByNumber, "rows");

        // count the number of filled squares in each box
        countFilledSquaresByDimension(grid.getBoxes(), countOfFilledSquaresIndexedByBoxNumber, " squares filled in box number ");

        // count the number of squares filled in each row
        countFilledSquaresByDimension(grid.getRows(), countOfFilledSquaresIndexedByRowNumber, " squares filled in row number ");

        // count the number of squares filled in each column
        countFilledSquaresByDimension(grid.getColumns(), countOfFilledSquaresIndexedByColumnNumber, " squares filled in column number ");
    }

    int countAllFilledSquares() {
        int count = 0;
        for (int square : grid.getSquares()) {
            if (square > 0) {
                count++;
            }
        }
        return count;
    }

    private void countFilledSquaresByDimension(int[][] things, int[] countOfFilledSquaresInThing, String s) {
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

    private void countNumberAppearancesByDimension(int[] countByDimension, String dimensionName) {
        // count the number of times each number appears in any slice of the given dimension
        int[][] slices;

        switch (dimensionName) {
            case "columns":
                slices = grid.getColumns();
                break;
            case "rows":
                slices = grid.getRows();
                break;
            case "boxes":
                slices = grid.getBoxes();
                break;
            default:
                slices = new int[0][0];
        }

        for (int num = 1; num < 10; num++) {
            countByDimension[num] = 0;
            for (int[] slice : slices) {
                for (int i = 0; i < 9; i++) {
                    if (num == slice[i]) {
                        countByDimension[num]++;
                    }
                }
                logger.debug("There are " + countByDimension[num] + " " + dimensionName + " with the number " + num + " - " + SudokuGridView.intArrayToString(slice));
            }
        }
    }

    public int[] getCountOfRowsWithNumberIndexedByNumber() {
        return countOfRowsWithNumberIndexedByNumber;
    }

    void setCountOfRowsWithNumberIndexedByNumber(int[] countOfRowsWithNumberIndexedByNumber) {
        this.countOfRowsWithNumberIndexedByNumber = countOfRowsWithNumberIndexedByNumber;
    }

    public int[] getCountOfColumnsWithNumberIndexedByNumber() {
        return countOfColumnsWithNumberIndexedByNumber;
    }

    void setCountOfColumnsWithNumberIndexedByNumber(int[] countOfColumnsWithNumberIndexedByNumber) {
        this.countOfColumnsWithNumberIndexedByNumber = countOfColumnsWithNumberIndexedByNumber;
    }

    public int[] getCountOfBoxesWithNumberIndexedByNumber() {
        return countOfColumnsWithNumberIndexedByNumber;
    }

    void setCountOfBoxesWithNumberIndexedByNumber(int[] countOfColumnsWithNumberIndexedByNumber) {
        this.countOfColumnsWithNumberIndexedByNumber = countOfColumnsWithNumberIndexedByNumber;
    }

    public int[] getCountOfFilledSquaresIndexedByBoxNumber() {
        return countOfFilledSquaresIndexedByBoxNumber;
    }

    void setCountOfFilledSquaresIndexedByBoxNumber(int[] countOfFilledSquaresIndexedByBoxNumber) {
        this.countOfFilledSquaresIndexedByBoxNumber = countOfFilledSquaresIndexedByBoxNumber;
    }

    public int[] getCountOfFilledSquaresIndexedByRowNumber() {
        return countOfFilledSquaresIndexedByRowNumber;
    }

    void setCountOfFilledSquaresIndexedByRowNumber(int[] countOfFilledSquaresIndexedByRowNumber) {
        this.countOfFilledSquaresIndexedByRowNumber = countOfFilledSquaresIndexedByRowNumber;
    }

    public int[] getCountOfFilledSquaresIndexedByColumnNumber() {
        return countOfFilledSquaresIndexedByColumnNumber;
    }

    void setCountOfFilledSquaresIndexedByColumnNumber(int[] countOfFilledSquaresIndexedByColumnNumber) {
        this.countOfFilledSquaresIndexedByColumnNumber = countOfFilledSquaresIndexedByColumnNumber;
    }

}
