import java.util.ArrayList;
import java.util.Collection;

public class SudokuGrid {

    int[] cols;
    int[][] rows;

    public SudokuGrid(){
        rows = new int[9][];

        cols = new int[9];

        cols[0] = 1;
        cols[1] = 1;
        cols[2] = 1;
        cols[3] = 1;
        cols[4] = 1;
        cols[5] = 1;
        cols[6] = 1;
        cols[7] = 1;
        cols[8] = 1;

        rows[0] = cols;

        cols = new int[9];
        cols[0] = 2;
        cols[1] = 2;
        cols[2] = 2;
        cols[3] = 2;
        cols[4] = 2;
        cols[5] = 2;
        cols[6] = 2;
        cols[7] = 2;
        cols[8] = 2;

        rows[1] = cols;

        cols = new int[9];
        cols[0] = 3;
        cols[1] = 3;
        cols[2] = 3;
        cols[3] = 3;
        cols[4] = 3;
        cols[5] = 3;
        cols[6] = 3;
        cols[7] = 3;
        cols[8] = 3;

        rows[2] = cols;

        cols = new int[9];
        cols[0] = 4;
        cols[1] = 4;
        cols[2] = 4;
        cols[3] = 4;
        cols[4] = 4;
        cols[5] = 4;
        cols[6] = 4;
        cols[7] = 4;
        cols[8] = 4;

        rows[3] = cols;

        cols = new int[9];
        cols[0] = 5;
        cols[1] = 5;
        cols[2] = 5;
        cols[3] = 5;
        cols[4] = 5;
        cols[5] = 5;
        cols[6] = 5;
        cols[7] = 5;
        cols[8] = 5;

        rows[4] = cols;

        cols = new int[9];
        cols[0] = 6;
        cols[1] = 6;
        cols[2] = 6;
        cols[3] = 6;
        cols[4] = 6;
        cols[5] = 6;
        cols[6] = 6;
        cols[7] = 6;
        cols[8] = 6;

        rows[5] = cols;

        cols = new int[9];
        cols[0] = 7;
        cols[1] = 7;
        cols[2] = 7;
        cols[3] = 7;
        cols[4] = 7;
        cols[5] = 7;
        cols[6] = 7;
        cols[7] = 7;
        cols[8] = 7;

        rows[6] = cols;

        cols = new int[9];
        cols[0] = 8;
        cols[1] = 8;
        cols[2] = 8;
        cols[3] = 8;
        cols[4] = 8;
        cols[5] = 8;
        cols[6] = 8;
        cols[7] = 8;
        cols[8] = 8;

        rows[7] = cols;

        cols = new int[9];
        cols[0] = 9;
        cols[1] = 9;
        cols[2] = 9;
        cols[3] = 9;
        cols[4] = 9;
        cols[5] = 9;
        cols[6] = 9;
        cols[7] = 9;
        cols[8] = 9;

        rows[8] = cols;
    }

    @Override
    public String toString(){
        String output;

        output = "I Am A Grid";

        if(cols == null){
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

    public int[] getCols() {
        return cols;
    }

    public void setCols(int[] cols) {
        this.cols = cols;
    }

    public int[][] getRows() {
        return rows;
    }

    public void setRows(int[][] rows) {
        this.rows = rows;
    }
}
