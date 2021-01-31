import java.util.ArrayList;
import java.util.Collection;

public class SudokuGrid {

    int[][] columns;
    int[][] rows;
    int[][] boxes;

    public SudokuGrid(){

    }

    @Override
    public String toString(){
        String output;

        output = "I Am A Grid";

        if(columns == null){
            return "columns array is null";
        }

        if(rows == null){
            return "rows array is null";
        }
        StringBuilder sb = new StringBuilder();

        sb.append("\n");

        for (int[] row : rows){
            if(row == null) return "row is null";
            for(int numby : row) {
                if(numby == 0) return "numby is zero";
                sb.append(numby);
                sb.append("  ");
            }
            sb.append("\n");
        }

        if(!sb.isEmpty()){
            output = sb.toString();
        }

        return output;
    }

    public int[][] getColumns() {
        return columns;
    }

    public void setColumns(int[][] columns) {
        this.columns = columns;
    }

    public int[][] getBoxes() {
        return boxes;
    }

    public void setBoxes(int[][] boxes) {
        this.boxes = boxes;
    }

    public int[][] getRows() {
        return rows;
    }

    public void setRows(int[][] rows) {
        this.rows = rows;
        this.makeColumns();
        this.makeBoxes();
    }

    private void makeColumns(){

        int[][] columns = new int[9][];
        int[] column = new int[9];

        for (int colNum = 0; colNum < 9; colNum++) {
            int rowNum = 0;
            for (int[] row : this.getRows()) {
                column[rowNum++] = row[colNum];
            }
            columns[colNum] = column;
        }

        this.setColumns(columns);

    }

    private void makeBoxes(){

        int[][] boxes = new int[9][];
        int[] box = new int[9];

        int boxCount = 0;

        for (int boxCol = 0; boxCol < 9; boxCol = boxCol + 3) {

            for (int boxRow = 0; boxRow < 9; boxRow = boxRow + 3) {
                box = selectBox(this, boxCol, boxRow);
                boxes[boxCount++] = box;
            }

        }

        this.setBoxes(boxes);
    }

    private int[] selectBox(SudokuGrid grid, int column, int row) {

        int[] box = new int[9];
        int count = 0;

        for (int c = column; c < column + 3; c++) {
            for (int r = row; r < row + 3; r++) {
                box[count++] = grid.getRows()[c][r];
            }
        }

        return box;
    }

}
