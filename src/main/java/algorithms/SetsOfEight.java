package algorithms;

import model.SudokuGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.SudokuGridSolver;

import static service.SudokuGridObserver.*;

public class SetsOfEight extends SudokuGridSolver implements SudokuGridSolverAlgorithm {

    static final Logger logger = LoggerFactory.getLogger(SetsOfEight.class);


    @Override
    public String explanation() {
        return """
                Sets of eight.
                This algorithm checks each column, row, and box to see if
                eight squares are already filled. If so, the one empty square
                is filled with the missing number.
                """;
    }

    @Override
    public SudokuGrid solve(SudokuGrid grid) {
        return solveSetsOfEight(grid);
    }

    public static int[] addTheLastNumberToTheSet(int[] set) {
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
        for (int j : set) {
            if (j > 0) {
                countOfNumbers++;
            }
        }
        if (countOfNumbers != 8) {
            throw new RuntimeException("there are supposed to be 8 squares " + "with a number");
        }
        // find the missing number
        int missingNumber = 0;
        int sumOfNumbersInSet = 0;
        for (int j : set) {
            sumOfNumbersInSet += j;
        }
        int sumOfAllNumbers = 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9;

        missingNumber = sumOfAllNumbers - sumOfNumbersInSet;

        // add the missing number to the set
        for (int i = 0; i < set.length; i++) {
            if (set[i] == 0) {
                set[i] = missingNumber;
                logger.debug("Added missing number " + missingNumber + " to " + "the set");
                break;
            }
        }
        return set;
    }


    private SudokuGrid solveSetsOfEight(SudokuGrid grid) {
        logger.info("solve by looking for sets of eight");

        if (fillLastEmptySquareInAnyBox(grid)) {
            return grid;
        }
        if (fillLastEmptySquareInAnyRow(grid)) {
            return grid;
        }
        if (fillLastEmptySquareInAnyColumn(grid)) {
            return grid;
        }
        return grid;
    }

    private boolean fillLastEmptySquareInAnyColumn(SudokuGrid grid) {
        // if the number of numbers in a given column is 8 then add the last
        // number\
        int[] slices = countValuesInColumns(grid);
        for (int i = 0; i < slices.length; i++) {
            if (slices[i] == 8) {
                logger.info("There are 8 squares filled in column number " + i);
                int[] set = addTheLastNumberToTheSet(grid.getColumns()[i]);
                int[][] columns = grid.getColumns();
                columns[i] = set;
                grid.setColumns(columns);
                return true;
            }
        }
        return false;
    }

    private boolean fillLastEmptySquareInAnyRow(SudokuGrid grid) {
        // if the number of numbers in a given row is 8 then add the last number
        int[] slices = countValuesInRows(grid);
        for (int i = 0; i < slices.length; i++) {
            if (slices[i] == 8) {
                logger.info("There are 8 squares filled in row number " + i);
                int[] set = addTheLastNumberToTheSet(grid.getRows()[i]);
                int[][] rows = grid.getRows();
                rows[i] = set;
                grid.setRows(rows);
                return true;
            }
        }
        return false;
    }

    private boolean fillLastEmptySquareInAnyBox(SudokuGrid grid) {
        // if the number of numbers in a given box is 8 then add the last number
        int[] slices = countValuesInBoxes(grid);
        for (int i = 0; i < slices.length; i++) {
            if (slices[i] == 8) {
                logger.info("There are 8 squares filled in box number " + i);
                int[] set = addTheLastNumberToTheSet(grid.getBoxes()[i]);
                int[][] boxes = grid.getBoxes();
                boxes[i] = set;
                grid.setBoxes(boxes);
                return true;
            }
        }
        return false;
    }

}
