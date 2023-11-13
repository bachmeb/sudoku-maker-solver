package model;

import static service.SudokuGridObserver.*;
import static service.SudokuGridTransformer.*;

public class SudokuGrid {

    int[] squares;

    public SudokuGrid() {
        this.setSquares(new int[81]);
    }

    public SudokuGrid(int[] squares) {
        this.setSquares(squares);
    }

    boolean validSquares(int[] squares) {
        boolean valid = squares != null;
        assert squares != null;
        if (squares.length != 81) {
            valid = false;
        }
        for (int square : squares) {
            if (square < 0) {
                valid = false;
            }
            if (square > 9) {
                valid = false;
            }
        }
        return valid;
    }

    public int[] getSquares() {
        return this.squares;
    }

    public void setSquares(int[] squares) {
        if (validSquares(squares)) {
            this.squares = squares;
        } else {
            throw new RuntimeException("invalid array of squares");
        }
    }

    public int[][] getRows() {
        return makeRowsFromSquares(this.squares);
    }

    public void setRows(int[][] rows) {
        this.squares = makeSquaresFromRows(rows);
    }

    public int[][] getColumns() {
        return makeColumnsFromSquares(this.squares);
    }

    public void setColumns(int[][] columns) {
        int[][] rows = makeRowsFromColumns(columns);
        this.squares = makeSquaresFromRows(rows);
    }

    public int[][] getBoxes() {
        return makeBoxesFromSquares(this.squares);
    }

    public void setBoxes(int[][] boxes) {
        int[][] rows = makeRowsFromBoxes(boxes);
        this.squares = makeSquaresFromRows(rows);
    }

    @Override
    public String toString() {
        String output;

        int[][] rows = makeRowsFromSquares(this.squares);

        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        String ROW = "-------------------------------------\n";

        sb.append(ROW);
        int rowNum = 0;
        for (int[] row : rows) {
            sb.append("|  ");
            rowNum++;
            int colNum = 0;
            for (int numby : row) {
                colNum++;
                if (numby == 0) {
                    sb.append(" ");
                } else {
                    sb.append(numby);
                }
                sb.append("  ");
                if (colNum % 3 == 0) {
                    sb.append("|  ");
                }
            }
            sb.append("\n");
            if (rowNum % 3 == 0) {
                sb.append(ROW);
            }
        }

        output = sb.toString();

        return output;
    }

    public int[] getBoxForSquare(int square) {
        int box = getBoxNumForSquare(square);
        return getBoxes()[box];
    }

    public int[] getColumnForSquare(int square) {
        int column = getColumnNumForSquare(square);
        return getColumns()[column];
    }

    public int[] getRowForSquare(int square) {
        int row = getRowNumForSquare(square);
        return getRows()[row];
    }

    public int[][] getColumnsForBoxNum(int n) {
        // box 0 = columns 0,1,2 = (0*3)+0,(0*3)+1,(0*3)+2
        // box 1 = columns 3,4,5 = (1*3)+0,(1*3)+1,(1*3)+2
        // box 2 = columns 6,7,8 = (2*3)+0,(2*3)+1,(2*3)+2
        // box 3 = columns 0,1,2 = (0*3)+0,(2*3)+1,(2*3)+2
        int[][] columns = getColumns();
        int boxRow = n / 3;
        int minus = boxRow * 3;
        int boxColumn = n - minus;
        int[] col0 = columns[(boxColumn * 3)];
        int[] col1 = columns[(boxColumn * 3) + 1];
        int[] col2 = columns[(boxColumn * 3) + 2];
        int[][] columnsForBoxNum = new int[3][9];
        columnsForBoxNum[0] = col0;
        columnsForBoxNum[1] = col1;
        columnsForBoxNum[2] = col2;
        return columnsForBoxNum;
    }

    public int[][] getRowsForBoxNum(int n) {
        // box 0 = row 0, 1, 2 = (0+0),(0+1),(0+2)
        // box 1 = row 0, 1, 2 = (boxRow+0),(boxRow+1),(boxRow+2)
        // box 3 = row 3, 4, 5 = (boxRow+0),(boxRow+1),(boxRow+2)
        int boxRow = n / 3;
        int rowRow = boxRow * 3;
        int[][] rows = getRows();
        int[] row0 = rows[rowRow];
        int[] row1 = rows[rowRow + 1];
        int[] row2 = rows[rowRow + 2];
        int[][] rowsForBoxNum = new int[3][9];
        rowsForBoxNum[0] = row0;
        rowsForBoxNum[1] = row1;
        rowsForBoxNum[2] = row2;
        return rowsForBoxNum;
    }
}
