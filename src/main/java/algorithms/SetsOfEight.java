package algorithms;

import model.SudokuGrid;

import static player.PlayerUtil.print;
import static service.SudokuGridChecker.checkGridForErrors;
import static service.SudokuGridObserver.*;

public class SetsOfEight implements SudokuGridSolverAlgorithm {

    /**
     * @param grid
     * @return
     */
    public static int[] aDimensionHasEight(SudokuGrid grid) {
        int[] qv;
        // Check boxes for sets of eight
        qv = checkBoxesForSetsOfEight(grid);
        if (qv != null) {
            return qv;
        }
        // Check rows for sets of eight
        qv = checkRowsForSetsOfEight(grid);
        if (qv != null) {
            return qv;
        }
        // Check columns for sets of eight
        qv = checkColumnsForSetsOfEight(grid);
        if (qv != null) {
            return qv;
        }
        return null;
    }

    private static int[] checkColumnsForSetsOfEight(SudokuGrid grid) {
        int[][] columns = grid.getColumns();
        for (int i = 0; i < 9; i++) {
            if (countFilledSquaresInSet(columns[i]) == 8) {

                for (int j = 0; j < 9; j++) {
                    if (columns[i][j] == 0) {
                        int[] qv = new int[2];
                        qv[0] = getSquareForColumnNumberAndPosition(i, j);
                        qv[1] = findMissingNumberInSet(columns[i]);
                        return qv;
                    }
                }
            }
        }
        return null;
    }

    private static int[] checkRowsForSetsOfEight(SudokuGrid grid) {
        int[][] rows = grid.getRows();
        for (int i = 0; i < 9; i++) {
            if (countFilledSquaresInSet(rows[i]) == 8) {
                for (int j = 0; j < 9; j++) {
                    if (rows[i][j] == 0) {
                        int[] qv = new int[2];
                        qv[0] = getSquareForRowNumberAndPosition(i, j);
                        qv[1] = findMissingNumberInSet(rows[i]);
                        return qv;
                    }
                }
            }
        }
        return null;
    }

    private static int[] checkBoxesForSetsOfEight(SudokuGrid grid) {
        int[][] boxes = grid.getBoxes();
        for (int i = 0; i < 9; i++) {
            if (countFilledSquaresInSet(boxes[i]) == 8) {
                for (int j = 0; j < 9; j++) {
                    if (boxes[i][j] == 0) {
                        int[] qv = new int[2];
                        qv[0] = getSquareForBoxNumAndPosNum(i, j);
                        qv[1] = findMissingNumberInSet(boxes[i]);
                        return qv;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public SudokuGrid solve(SudokuGrid grid, int q, int v) {
        int[] squares = grid.getSquares();
        squares[q] = v;
        if (checkGridForErrors(grid)) {
            print(grid.toString());
            throw new RuntimeException("Grid has errors");
        }
        return grid;
    }

    @Override
    public int[] search(SudokuGrid grid) {
        return aDimensionHasEight(grid);
    }

    @Override
    public String explanation() {
        return """
                Sets of eight.
                This algorithm checks each column, row, and box to see if
                eight squares are already filled. If so, the one empty square
                is filled with the missing number.
                """;
    }

}
