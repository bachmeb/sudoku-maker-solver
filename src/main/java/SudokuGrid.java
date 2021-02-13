public class SudokuGrid {

    int[] squares;
    int[][] rows;
    int[][] columns;
    int[][] boxes;

    public SudokuGrid() {
        this.setSquares(new int[81]);
    }

    public SudokuGrid(int[] squares) {
        this.setSquares(squares);
    }

    public boolean validSquares(int[] squares){
        boolean valid = true;
        if(squares == null){
            valid = false;
        }
        if(squares.length != 81){
            valid = false;
        }
        for(int i=0;i<squares.length;i++){
            if(squares[i] < 0){
                valid = false;
            }
            if(squares[i] > 9){
                valid = false;
            }
        }
        return valid;
    }

    public int[] getSquares() {
        return this.squares;
    }

    public void setSquares(int[] squares) {
        if(validSquares(squares)) {
            this.squares = squares;
        }
        else {
            throw new RuntimeException("invalid array of squares");
        }
    }

    public int[][] getRows() {
        return makeRowsOfSquares();
    }

    public int[][] getColumns() {
        return makeColumnsOfSquares();
    }

    public int[][] getBoxes() {
        return makeBoxesOfSquares();
    }

    public void setRows(int[][] rows) {
        int[] set = makeSquaresFromRows(rows);
        this.squares = set;
    }

    public void setColumns(int[][] columns) {
        int[][] rows = makeRowsFromColumns(columns);
        this.squares = makeSquaresFromRows(rows);
    }

    public void setBoxes(int[][] boxes) {
        int[][] rows = makeRowsFromBoxes(boxes);
        this.squares = makeSquaresFromRows(rows);
    }

    @Override
    public String toString() {
        String output;

        int[][] rows = new int[9][9];

        rows = makeRowsOfSquares();

        StringBuilder sb = new StringBuilder();

        sb.append("I Am A Grid");

        sb.append("\n");

        for (int[] row : rows) {
            for (int numby : row) {
                sb.append(numby);
                sb.append("  ");
            }
            sb.append("\n");
        }

            output = sb.toString();

        return output;
    }

    private int[][] makeRowsOfSquares(){
        int[][] rows = new int[9][9];
        int[] row = new int[9];
        int rowIndex = 0;
        int rowsIndex = 0;
        for (int squaresIndex = 0;squaresIndex<squares.length;squaresIndex++){
            if(rowIndex == 9){
                rowIndex = 0;
                rows[rowsIndex++] = row;
                row = new int[9];
            }
            row[rowIndex++]=squares[squaresIndex];
        }
        rows[rowsIndex] = row;
        return rows;
    }

    private int[][] makeColumnsOfSquares(){
        int[][] rows = makeRowsOfSquares();
        int[][] columns = makeColumnsFromRows(rows);
        return columns;
    }

    private int[][] makeBoxesOfSquares(){
        int[][] rows = makeRowsOfSquares();
        int[][] boxes = makeBoxesFromRows(rows);
        return boxes;
    }

    public boolean validGrid(int[][] grid) {
        boolean valid = true;

        if (grid.length != 9) {
            valid = false;
        }
        for (int i = 0; i < grid.length; i++) {
            if (grid[i].length != 9) {
                valid = false;
            }
        }

        return valid;
    }

    private int[] makeSquaresFromRows(int[][] rows){
        int s = 0;
        int [] sqs = new int[81];
        for(int i=0;i<rows.length;i++){
            for(int j=0;j<9;j++){
                sqs[s++]=rows[i][j];
            }
        }
        return sqs;
    }

    private int[][] makeColumnsFromRows(int[][] rowSet) {

        int[][] columns = new int[9][9];

        for (int colNum = 0; colNum < 9; colNum++) {
            int[] column = new int[9];
            int rowNum = 0;
            for (int[] row : rowSet) {
                column[rowNum++] = row[colNum];
            }
            columns[colNum] = column;
        }

        return columns;

    }

    private int[][] makeRowsFromColumns(int[][] colSet) {

        int[][] rows = new int[9][9];

        for (int rowNum = 0; rowNum < 9; rowNum++) {
            int[] row = new int[9];
            int colNum = 0;
            for (int[] column : colSet) {
                row[colNum++] = column[rowNum];
            }
            rows[rowNum] = row;
        }

        return rows;

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

    private int[][] makeBoxesFromColumns(int[][] colSet) {

        int[][] boxes = new int[9][9];

        boxes[0][0] = colSet[0][0];
        boxes[0][1] = colSet[1][0];
        boxes[0][2] = colSet[2][0];
        boxes[0][3] = colSet[0][1];
        boxes[0][4] = colSet[1][1];
        boxes[0][5] = colSet[2][1];
        boxes[0][6] = colSet[0][2];
        boxes[0][7] = colSet[1][2];
        boxes[0][8] = colSet[2][2];

        boxes[1][0] = colSet[3][0];
        boxes[1][1] = colSet[4][0];
        boxes[1][2] = colSet[5][0];
        boxes[1][3] = colSet[3][1];
        boxes[1][4] = colSet[4][1];
        boxes[1][5] = colSet[5][1];
        boxes[1][6] = colSet[3][2];
        boxes[1][7] = colSet[4][2];
        boxes[1][8] = colSet[5][2];

        boxes[2][0] = colSet[6][0];
        boxes[2][1] = colSet[7][0];
        boxes[2][2] = colSet[8][0];
        boxes[2][3] = colSet[6][1];
        boxes[2][4] = colSet[7][1];
        boxes[2][5] = colSet[8][1];
        boxes[2][6] = colSet[6][2];
        boxes[2][7] = colSet[7][2];
        boxes[2][8] = colSet[8][2];

        ///

        boxes[3][0] = colSet[0][3];
        boxes[3][1] = colSet[1][3];
        boxes[3][2] = colSet[2][3];
        boxes[3][3] = colSet[0][4];
        boxes[3][4] = colSet[1][4];
        boxes[3][5] = colSet[2][4];
        boxes[3][6] = colSet[0][5];
        boxes[3][7] = colSet[1][5];
        boxes[3][8] = colSet[2][5];

        boxes[4][0] = colSet[3][3];
        boxes[4][1] = colSet[4][3];
        boxes[4][2] = colSet[5][3];
        boxes[4][3] = colSet[3][4];
        boxes[4][4] = colSet[4][4];
        boxes[4][5] = colSet[5][4];
        boxes[4][6] = colSet[3][5];
        boxes[4][7] = colSet[4][5];
        boxes[4][8] = colSet[5][5];

        boxes[5][0] = colSet[6][3];
        boxes[5][1] = colSet[7][3];
        boxes[5][2] = colSet[8][3];
        boxes[5][3] = colSet[6][4];
        boxes[5][4] = colSet[7][4];
        boxes[5][5] = colSet[8][4];
        boxes[5][6] = colSet[6][5];
        boxes[5][7] = colSet[7][5];
        boxes[5][8] = colSet[8][5];

        ///

        boxes[6][0] = colSet[0][6];
        boxes[6][1] = colSet[1][6];
        boxes[6][2] = colSet[2][6];
        boxes[6][3] = colSet[0][7];
        boxes[6][4] = colSet[1][7];
        boxes[6][5] = colSet[2][7];
        boxes[6][6] = colSet[0][8];
        boxes[6][7] = colSet[1][8];
        boxes[6][8] = colSet[2][8];

        boxes[7][0] = colSet[3][6];
        boxes[7][1] = colSet[4][6];
        boxes[7][2] = colSet[5][6];
        boxes[7][3] = colSet[3][7];
        boxes[7][4] = colSet[4][7];
        boxes[7][5] = colSet[5][7];
        boxes[7][6] = colSet[3][8];
        boxes[7][7] = colSet[4][8];
        boxes[7][8] = colSet[5][8];

        boxes[8][0] = colSet[6][6];
        boxes[8][1] = colSet[7][6];
        boxes[8][2] = colSet[8][6];
        boxes[8][3] = colSet[6][7];
        boxes[8][4] = colSet[7][7];
        boxes[8][5] = colSet[8][7];
        boxes[8][6] = colSet[6][8];
        boxes[8][7] = colSet[7][8];
        boxes[8][8] = colSet[8][8];

        return boxes;
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
        row = new int[9];
        colNum = 0;

        for(int boxNum = 0; boxNum < 3; boxNum++){
            for(int posNum = 3; posNum < 6; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        row = new int[9];
        colNum = 0;

        for(int boxNum = 0; boxNum < 3; boxNum++){
            for(int posNum = 6; posNum < 9; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        row = new int[9];
        colNum = 0;

        ///

        for(int boxNum = 3; boxNum < 6; boxNum++){
            for(int posNum = 0; posNum < 3; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        row = new int[9];
        colNum = 0;

        for(int boxNum = 3; boxNum < 6; boxNum++){
            for(int posNum = 3; posNum < 6; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        row = new int[9];
        colNum = 0;

        for(int boxNum = 3; boxNum < 6; boxNum++){
            for(int posNum = 6; posNum < 9; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        row = new int[9];
        colNum = 0;

        ///

        for(int boxNum = 6; boxNum < 9; boxNum++){
            for(int posNum = 0; posNum < 3; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        row = new int[9];
        colNum = 0;

        for(int boxNum = 6; boxNum < 9; boxNum++){
            for(int posNum = 3; posNum < 6; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;
        row = new int[9];
        colNum = 0;

        for(int boxNum = 6; boxNum < 9; boxNum++){
            for(int posNum = 6; posNum < 9; posNum++){
                row[colNum++] = boxSet[boxNum][posNum];
            }
        }
        rows[rowNum++] = row;

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

    public int findRowNumForBoxNumAndPosNum(int boxNum, int posNum){

        int[][] rowNumbersByBoxNumAndPosNum = new int[9][9];

        rowNumbersByBoxNumAndPosNum[0][0] = 0;
        rowNumbersByBoxNumAndPosNum[0][1] = 0;
        rowNumbersByBoxNumAndPosNum[0][2] = 0;
        rowNumbersByBoxNumAndPosNum[0][3] = 1;
        rowNumbersByBoxNumAndPosNum[0][4] = 1;
        rowNumbersByBoxNumAndPosNum[0][5] = 1;
        rowNumbersByBoxNumAndPosNum[0][6] = 2;
        rowNumbersByBoxNumAndPosNum[0][7] = 2;
        rowNumbersByBoxNumAndPosNum[0][8] = 2;

        rowNumbersByBoxNumAndPosNum[1][0] = 0;
        rowNumbersByBoxNumAndPosNum[1][1] = 0;
        rowNumbersByBoxNumAndPosNum[1][2] = 0;
        rowNumbersByBoxNumAndPosNum[1][3] = 1;
        rowNumbersByBoxNumAndPosNum[1][4] = 1;
        rowNumbersByBoxNumAndPosNum[1][5] = 1;
        rowNumbersByBoxNumAndPosNum[1][6] = 2;
        rowNumbersByBoxNumAndPosNum[1][7] = 2;
        rowNumbersByBoxNumAndPosNum[1][8] = 2;

        rowNumbersByBoxNumAndPosNum[2][0] = 0;
        rowNumbersByBoxNumAndPosNum[2][1] = 0;
        rowNumbersByBoxNumAndPosNum[2][2] = 0;
        rowNumbersByBoxNumAndPosNum[2][3] = 1;
        rowNumbersByBoxNumAndPosNum[2][4] = 1;
        rowNumbersByBoxNumAndPosNum[2][5] = 1;
        rowNumbersByBoxNumAndPosNum[2][6] = 2;
        rowNumbersByBoxNumAndPosNum[2][7] = 2;
        rowNumbersByBoxNumAndPosNum[2][8] = 2;

        ///

        rowNumbersByBoxNumAndPosNum[3][0] = 3;
        rowNumbersByBoxNumAndPosNum[3][1] = 3;
        rowNumbersByBoxNumAndPosNum[3][2] = 3;
        rowNumbersByBoxNumAndPosNum[3][3] = 4;
        rowNumbersByBoxNumAndPosNum[3][4] = 4;
        rowNumbersByBoxNumAndPosNum[3][5] = 4;
        rowNumbersByBoxNumAndPosNum[3][6] = 5;
        rowNumbersByBoxNumAndPosNum[3][7] = 5;
        rowNumbersByBoxNumAndPosNum[3][8] = 5;

        rowNumbersByBoxNumAndPosNum[4][0] = 3;
        rowNumbersByBoxNumAndPosNum[4][1] = 3;
        rowNumbersByBoxNumAndPosNum[4][2] = 3;
        rowNumbersByBoxNumAndPosNum[4][3] = 4;
        rowNumbersByBoxNumAndPosNum[4][4] = 4;
        rowNumbersByBoxNumAndPosNum[4][5] = 4;
        rowNumbersByBoxNumAndPosNum[4][6] = 5;
        rowNumbersByBoxNumAndPosNum[4][7] = 5;
        rowNumbersByBoxNumAndPosNum[4][8] = 5;

        rowNumbersByBoxNumAndPosNum[5][0] = 3;
        rowNumbersByBoxNumAndPosNum[5][1] = 3;
        rowNumbersByBoxNumAndPosNum[5][2] = 3;
        rowNumbersByBoxNumAndPosNum[5][3] = 4;
        rowNumbersByBoxNumAndPosNum[5][4] = 4;
        rowNumbersByBoxNumAndPosNum[5][5] = 4;
        rowNumbersByBoxNumAndPosNum[5][6] = 5;
        rowNumbersByBoxNumAndPosNum[5][7] = 5;
        rowNumbersByBoxNumAndPosNum[5][8] = 5;

        ///

        rowNumbersByBoxNumAndPosNum[6][0] = 6;
        rowNumbersByBoxNumAndPosNum[6][1] = 6;
        rowNumbersByBoxNumAndPosNum[6][2] = 6;
        rowNumbersByBoxNumAndPosNum[6][3] = 7;
        rowNumbersByBoxNumAndPosNum[6][4] = 7;
        rowNumbersByBoxNumAndPosNum[6][5] = 7;
        rowNumbersByBoxNumAndPosNum[6][6] = 8;
        rowNumbersByBoxNumAndPosNum[6][7] = 8;
        rowNumbersByBoxNumAndPosNum[6][8] = 8;

        rowNumbersByBoxNumAndPosNum[7][0] = 6;
        rowNumbersByBoxNumAndPosNum[7][1] = 6;
        rowNumbersByBoxNumAndPosNum[7][2] = 6;
        rowNumbersByBoxNumAndPosNum[7][3] = 7;
        rowNumbersByBoxNumAndPosNum[7][4] = 7;
        rowNumbersByBoxNumAndPosNum[7][5] = 7;
        rowNumbersByBoxNumAndPosNum[7][6] = 8;
        rowNumbersByBoxNumAndPosNum[7][7] = 8;
        rowNumbersByBoxNumAndPosNum[7][8] = 8;

        rowNumbersByBoxNumAndPosNum[8][0] = 6;
        rowNumbersByBoxNumAndPosNum[8][1] = 6;
        rowNumbersByBoxNumAndPosNum[8][2] = 6;
        rowNumbersByBoxNumAndPosNum[8][3] = 7;
        rowNumbersByBoxNumAndPosNum[8][4] = 7;
        rowNumbersByBoxNumAndPosNum[8][5] = 7;
        rowNumbersByBoxNumAndPosNum[8][6] = 8;
        rowNumbersByBoxNumAndPosNum[8][7] = 8;
        rowNumbersByBoxNumAndPosNum[8][8] = 8;

        return rowNumbersByBoxNumAndPosNum[boxNum][posNum];
    }

}
