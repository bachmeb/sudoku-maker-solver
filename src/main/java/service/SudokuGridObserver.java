package service;

import model.SudokuGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuGridObserver {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridObserver.class);

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

    public int[] getCountOfBoxesWithNumberIndexedByNumber(SudokuGrid grid) {
        return countNumberAppearancesByDimension(grid.getBoxes());
    }

}
