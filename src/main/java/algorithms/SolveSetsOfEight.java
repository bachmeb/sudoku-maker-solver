package algorithms;

import model.SudokuGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.SudokuGridObserver;
import service.SudokuGridSolver;

public class SolveSetsOfEight extends SudokuGridSolver implements SudokuGridSolverAlgorithm {

    static final Logger logger =
            LoggerFactory.getLogger(SolveSetsOfEight.class);

    SudokuGridObserver observer;

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

    @Override
    public SudokuGrid solve(SudokuGrid grid) {
        solveSetsOfEight(grid);
        return
                grid;
    }

    private SudokuGrid solveSetsOfEight(SudokuGrid grid) {
        logger.info("solve by looking for sets of eight");

        grid = fillLastEmptySquareInAnyBox(grid);
        grid = fillLastEmptySquareInAnyRow(grid);
        grid = fillLastEmptySquareInAnyColumn(grid);

        return grid;
    }

    private SudokuGrid fillLastEmptySquareInAnyColumn(SudokuGrid grid) {
        observer = new SudokuGridObserver(grid);
        observer.reCountNumbersInSquares();
        // if the number of numbers in a given column is 8 then add the last number
        for (int i = 0; i < observer.getCountOfFilledSquaresIndexedByColumnNumber().length; i++) {
            if (observer.getCountOfFilledSquaresIndexedByColumnNumber()[i] == 8) {
                logger.info("There are 8 squares filled in column number " + i);
                int set[] = addTheLastNumberToTheSet(grid.getColumns()[i]);
                int[][] columns = grid.getColumns();
                columns[i] = set;
                grid.setColumns(columns);
            }
        }
        return grid;
    }

    private SudokuGrid fillLastEmptySquareInAnyRow(SudokuGrid grid) {
        observer = new SudokuGridObserver(grid);
        observer.reCountNumbersInSquares();
        // if the number of numbers in a given row is 8 then add the last number
        for (int i = 0; i < observer.getCountOfFilledSquaresIndexedByRowNumber().length; i++) {
            if (observer.getCountOfFilledSquaresIndexedByRowNumber()[i] == 8) {
                logger.info("There are 8 squares filled in row number " + i);
                int set[] = addTheLastNumberToTheSet(grid.getRows()[i]);
                int[][] rows = grid.getRows();
                rows[i] = set;
                grid.setRows(rows);
            }
        }
        return grid;
    }

    private SudokuGrid fillLastEmptySquareInAnyBox(SudokuGrid grid) {
        observer = new SudokuGridObserver(grid);
        observer.reCountNumbersInSquares();
        // if the number of numbers in a given box is 8 then add the last number
        for (int i = 0; i < observer.getCountOfFilledSquaresIndexedByBoxNumber().length; i++) {
            if (observer.getCountOfFilledSquaresIndexedByBoxNumber()[i] == 8) {
                logger.info("There are 8 squares filled in box number " + i);
                int set[] = addTheLastNumberToTheSet(grid.getBoxes()[i]);
                int[][] boxes = grid.getBoxes();
                boxes[i] = set;
                grid.setBoxes(boxes);
            }
        }
        return grid;
    }

}
