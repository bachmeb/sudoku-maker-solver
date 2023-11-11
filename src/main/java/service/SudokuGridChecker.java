package service;

import model.SudokuGrid;

import static service.SudokuGridObserver.*;

public class SudokuGridChecker {

    public static boolean checkGridSolved(SudokuGrid grid) {

        boolean rowsSolved = false;
        boolean columnsSolved = false;
        boolean boxesSolved = false;

        if (grid == null) {
            return false;
        }

        rowsSolved = checkDimensionSolved(grid.getRows());
        columnsSolved = checkDimensionSolved(grid.getColumns());
        boxesSolved = checkDimensionSolved(grid.getBoxes());

        return rowsSolved && columnsSolved && boxesSolved;

    }

    private static boolean checkDimensionSolved(int[][] dim) {
        for (int[] a : dim) {
            if (!checkSetHasAllNineNumbers(a)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSetHasAllNineNumbers(int[] set) {
        for (int number = 1; number <= 9; number++) {
            if (!checkSetForNumber(number, set)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkGridForErrors(SudokuGrid grid) {

        for (int[] row : grid.getRows()) {
            if (checkSetForDuplicates(row)) {
                return true;
            }
        }

        for (int[] column : grid.getColumns()) {
            if (checkSetForDuplicates(column)) {
                return true;
            }
        }

        for (int[] box : grid.getBoxes()) {
            if (checkSetForDuplicates(box)) {
                return true;
            }
        }

        return false;

    }

    public static boolean checkSetForDuplicates(int[] set) {
        for (int num = 1; num < 10; num++) {
            int count = 0;
            for (int i : set) {
                if (i == num) {
                    count++;
                    if (count > 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkSetForNumber(int findNum, int[] set) {
        for (int j : set) {
            if (j == findNum) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkAllAdjacentRowsAndColumnsForNumber(SudokuGrid grid, int q, int n) {
        // See if the corresponding row column and box contain a given number
        int[] combined = lookThreeWays(grid, q);
        if (combined[n] == 0) {
            // if not, get the box num
            int boxNumber = getBoxNumForSquare(q);
            // Then get the rows and columns that intersect the same box
            int[][] columns = grid.getColumnsForBoxNum(boxNumber);
            int[][] rows = grid.getRowsForBoxNum(boxNumber);
            // See if a given number is present twice in the adjacent
            // rows and columns
            int[] rCount = countNumberAppearancesByDimension(columns);
            int[] cCount = countNumberAppearancesByDimension(rows);
            return rCount[n] == 2 && cCount[n] == 2;
        }
        return false;
    }

    private static boolean checkSuperSetForNumber(int[][] superSet, int n) {
        for (int[] set : superSet) {
            if (checkSetForNumber(n, set)) {
                return true;
            }
        }
        return false;
    }

    public boolean validGrid(int[][] grid) {
        boolean valid = grid.length == 9;
        for (int[] ints : grid) {
            if (ints.length != 9) {
                valid = false;
                break;
            }
        }
        return valid;
    }

}
