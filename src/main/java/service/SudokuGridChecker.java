package toppppy;

import model.SudokuGrid;

public class SudokuGridChecker {

    public boolean checkGridSolved(SudokuGrid grid) {

        boolean rowsSolved = false;
        boolean columnsSolved = false;
        boolean boxesSolved = false;

        if (grid == null) {
            return false;
        }

        int count = 0;

        for (int[] row : grid.getRows()) {
            if (checkArray(row)) {
                count++;
            }
        }

        if (count == 9) {
            rowsSolved = true;
        }

        count = 0;

        for (int[] column : grid.getColumns()) {
            if (checkArray(column)) {
                count++;
            }
        }

        if (count == 9) {
            columnsSolved = true;
        }

        count = 0;

        for (int[] box : grid.getBoxes()) {
            if (checkArray(box)) {
                count++;
            }
        }

        if (count == 9) {
            boxesSolved = true;
        }

        return rowsSolved && columnsSolved && boxesSolved;

    }

    private boolean checkArray(int[] set) {

        int count = 0;

        for (int number = 1; number <= 9; number++) {
            for (int answer : set) {
                if (answer == 0) {
                    return false;
                }
                if (answer == number) {
                    count++;
                }
            }
            if (count != 1) {
                return false;
            }
            count = 0;
        }

        return true;
    }

    public boolean checkGridForErrors(SudokuGrid grid) {

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

    private boolean checkSetForDuplicates(int[] set) {
        for (int num = 1; num < 10; num++) {
            int count = 0;
            for (int pos = 0; pos < set.length; pos++) {
                if (set[pos] == num) {
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

        for (int i = 0; i < set.length; i++) {
            if (set[i] == findNum) {
                return true;
            }
        }
        return false;
    }


}
