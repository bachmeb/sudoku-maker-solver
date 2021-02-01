public class SudokuGrid {

    int[][] columns;
    int[][] rows;
    int[][] boxes;

    public SudokuGrid() {

    }

    @Override
    public String toString() {
        String output;

        output = "I Am A Grid";

        if (columns == null) {
            throw new RuntimeException("columns array is null");
        }

        if (rows == null) {
            throw new RuntimeException("rows array is null");
        }
        StringBuilder sb = new StringBuilder();

        sb.append("\n");

        for (int[] row : rows) {
            if (row == null) {
                throw new RuntimeException("row[] is null");
            }
            for (int numby : row) {
                if (numby > 9) {
                    throw new RuntimeException("This numby is too high: " + numby);
                }
                if (numby < 0) {
                    throw new RuntimeException("This numby is too low: " + numby);
                }
                sb.append(numby);
                sb.append("  ");
            }
            sb.append("\n");
        }

        if (!sb.isEmpty()) {
            output = sb.toString();
        }

        return output;
    }

    public int[][] getColumns() {
        return columns;
    }

    public void setColumns(int[][] newColumns) {
        // validate array lengths
        if (newColumns.length != 9) {
            throw new RuntimeException();
        }
        for (int i = 0; i < newColumns.length; i++) {
            if (newColumns[i].length != 9) {
                throw new RuntimeException();
            }
        }
        this.columns = newColumns;
    }

    public int[][] getBoxes() {
        return boxes;
    }

    public void setBoxes(int[][] newBoxes) {
        // validate array lengths
        if (newBoxes.length != 9) {
            throw new RuntimeException();
        }
        for (int i = 0; i < newBoxes.length; i++) {
            if (newBoxes[i].length != 9) {
                throw new RuntimeException();
            }
        }
        this.boxes = newBoxes;
        this.columns = makeColumnsFromBoxes(newBoxes);
        this.rows = makeRowsFromBoxes(newBoxes);
    }

    public int[][] getRows() {
        return rows;
    }

    public void setRows(int[][] newRows) {
        // validate array lengths
        if (newRows.length != 9) {
            throw new RuntimeException();
        }
        for (int i = 0; i < newRows.length; i++) {
            if (newRows[i].length != 9) {
                throw new RuntimeException();
            }
        }
        this.rows = newRows;
        this.columns = makeColumnsFromRows(newRows);
        this.boxes = makeBoxesFromRows(newRows);
    }

    private int[][] makeColumnsFromRows(int[][] rowSet) {

        int[][] columns = new int[9][];
        int[] column = new int[9];

        for (int colNum = 0; colNum < 9; colNum++) {
            int rowNum = 0;
            for (int[] row : rowSet) {
                column[rowNum++] = row[colNum];
            }
            columns[colNum] = column;
        }

        return columns;

    }

    private int[][] makeColumnsFromBoxes(int[][] boxSet) {

        int[][] columns = new int[9][9];

        columns[0][0] = boxSet[0][0];
        columns[0][1] = boxSet[0][3];
        columns[0][2] = boxSet[0][6];
        columns[0][3] = boxSet[3][0];
        columns[0][4] = boxSet[3][3];
        columns[0][5] = boxSet[3][6];
        columns[0][6] = boxSet[6][0];
        columns[0][7] = boxSet[6][3];
        columns[0][8] = boxSet[6][6];

        columns[1][0] = boxSet[0][1];
        columns[1][1] = boxSet[0][4];
        columns[1][2] = boxSet[0][7];
        columns[1][3] = boxSet[3][1];
        columns[1][4] = boxSet[3][4];
        columns[1][5] = boxSet[3][7];
        columns[1][6] = boxSet[6][1];
        columns[1][7] = boxSet[6][4];
        columns[1][8] = boxSet[6][7];

        columns[2][0] = boxSet[0][2];
        columns[2][1] = boxSet[0][5];
        columns[2][2] = boxSet[0][8];
        columns[2][3] = boxSet[3][2];
        columns[2][4] = boxSet[3][5];
        columns[2][5] = boxSet[3][8];
        columns[2][6] = boxSet[6][2];
        columns[2][7] = boxSet[6][5];
        columns[2][8] = boxSet[6][8];

        ///

        columns[3][0] = boxSet[1][0];
        columns[3][1] = boxSet[1][3];
        columns[3][2] = boxSet[1][6];
        columns[3][3] = boxSet[4][0];
        columns[3][4] = boxSet[4][3];
        columns[3][5] = boxSet[4][6];
        columns[3][6] = boxSet[7][0];
        columns[3][7] = boxSet[7][3];
        columns[3][8] = boxSet[7][6];

        columns[4][0] = boxSet[1][1];
        columns[4][1] = boxSet[1][4];
        columns[4][2] = boxSet[1][7];
        columns[4][3] = boxSet[4][1];
        columns[4][4] = boxSet[4][4];
        columns[4][5] = boxSet[4][7];
        columns[4][6] = boxSet[7][1];
        columns[4][7] = boxSet[7][4];
        columns[4][8] = boxSet[7][7];

        columns[5][0] = boxSet[1][2];
        columns[5][1] = boxSet[1][5];
        columns[5][2] = boxSet[1][8];
        columns[5][3] = boxSet[4][2];
        columns[5][4] = boxSet[4][5];
        columns[5][5] = boxSet[4][8];
        columns[5][6] = boxSet[7][2];
        columns[5][7] = boxSet[7][5];
        columns[5][8] = boxSet[7][8];

        ///

        columns[6][0] = boxSet[2][0];
        columns[6][1] = boxSet[2][3];
        columns[6][2] = boxSet[2][6];
        columns[6][3] = boxSet[5][0];
        columns[6][4] = boxSet[5][3];
        columns[6][5] = boxSet[5][6];
        columns[6][6] = boxSet[8][0];
        columns[6][7] = boxSet[8][3];
        columns[6][8] = boxSet[8][6];

        columns[7][0] = boxSet[2][1];
        columns[7][1] = boxSet[2][4];
        columns[7][2] = boxSet[2][7];
        columns[7][3] = boxSet[5][1];
        columns[7][4] = boxSet[5][4];
        columns[7][5] = boxSet[5][7];
        columns[7][6] = boxSet[8][1];
        columns[7][7] = boxSet[8][4];
        columns[7][8] = boxSet[8][7];

        columns[8][0] = boxSet[2][2];
        columns[8][1] = boxSet[2][5];
        columns[8][2] = boxSet[2][8];
        columns[8][3] = boxSet[5][2];
        columns[8][4] = boxSet[5][5];
        columns[8][5] = boxSet[5][8];
        columns[8][6] = boxSet[8][2];
        columns[8][7] = boxSet[8][5];
        columns[8][8] = boxSet[8][8];

        return columns;
    }

    private int[][] makeRowsFromBoxes(int[][] boxSet) {

        int[][] rows = new int[9][9];
        int[] row = new int[9];
        int rowNum = 0;
        int colNum = 0;

        for(int boxNum = 0; boxNum < 3; boxNum++){
            for(int posNum = 0; posNum < 3; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        colNum = 0;

        for(int boxNum = 0; boxNum < 3; boxNum++){
            for(int posNum = 3; posNum < 6; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        colNum = 0;

        for(int boxNum = 0; boxNum < 3; boxNum++){
            for(int posNum = 6; posNum < 9; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        colNum = 0;

        ///

        for(int boxNum = 3; boxNum < 6; boxNum++){
            for(int posNum = 0; posNum < 3; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        colNum = 0;

        for(int boxNum = 3; boxNum < 6; boxNum++){
            for(int posNum = 3; posNum < 6; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        colNum = 0;

        for(int boxNum = 3; boxNum < 6; boxNum++){
            for(int posNum = 6; posNum < 9; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        colNum = 0;

        ///

        for(int boxNum = 6; boxNum < 9; boxNum++){
            for(int posNum = 0; posNum < 3; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        colNum = 0;

        for(int boxNum = 6; boxNum < 9; boxNum++){
            for(int posNum = 3; posNum < 6; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        colNum = 0;

        for(int boxNum = 6; boxNum < 9; boxNum++){
            for(int posNum = 6; posNum < 9; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        colNum = 0;

        return rows;

    }


    private int[][] makeBoxesFromRows(int[][] rowSet) {

        int[][] boxes = new int[9][];
        int[] box = new int[9];

        int boxNum = 0;

        for (int boxCol = 0; boxCol < 9; boxCol = boxCol + 3) {

            for (int boxRow = 0; boxRow < 9; boxRow = boxRow + 3) {
                box = selectBoxFromRows(rowSet, boxCol, boxRow);
                boxes[boxNum++] = box;
            }
        }
        return boxes;
    }

    private int[] selectBoxFromRows(int[][] rowSet, int row, int column) {

        int[] box = new int[9];
        int count = 0;
        for (int r = row; r < row + 3; r++) {
            for (int c = column; c < column + 3; c++) {
                box[count++] = rowSet[r][c];
            }
        }
        return box;
    }

}
