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
    }
}
