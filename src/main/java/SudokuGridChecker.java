public class SudokuGridChecker {

    public boolean checkGrid(SudokuGrid grid) {

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


    private boolean checkArray(int[] numbers) {

        int count = 0;

        for (int n = 1; n <= 9; n++) {
            for (int m : numbers) {
                if (m == 0) {
                    return false;
                }
                if (m == n) {
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
}
