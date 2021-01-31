public class SudokuGridChecker {

    public boolean solve(SudokuGrid grid){

        boolean rowsSolved = false;
        boolean columnsSolved = false;
        boolean boxesSolved = false;

        if(grid == null){
            return false;
        }

        int[] row;
        int[] column;
        int[] box;

        row = new int[9];
        column = new int[9];
        box = new int[9];

        int count = 0;

        for (int[] cols : grid.getRows()){
            row = cols;
            if(check(row)){
                count++;
            }
        }

        if(count == 9){
            rowsSolved = true;
        }

        count = 0;

        for(int colNum = 0; colNum < 9; colNum++){
            int rowNum = 0;
            for (int[] rw : grid.getRows()){
                column[rowNum++] = rw[colNum];
            }
            if(check(column)){
                count++;
            }
        }

        if(count == 9){
            columnsSolved = true;
        }

        count = 0;

        for(int boxCol = 0; boxCol < 9; boxCol = boxCol+3){

            for(int boxRow = 0; boxRow < 9; boxRow = boxRow+3){
                box = selectBox(grid,boxCol,boxRow);

                if(check(box)){
                    count++;
                }
            }
        }

        if(count == 9){
            boxesSolved = true;
        }

        return rowsSolved && columnsSolved && boxesSolved;

    }

    private int[] selectBox(SudokuGrid grid, int column, int row){

        int[] box = new int[9];
        int count = 0;

        for(int c = column; c < column+3 ; c++){
            for(int r = row; r < row+3; r++){
                box[count++] = grid.getRows()[r][c];
            }
        }

        return box;
    }

    private boolean check(int[] numbers){
        boolean ok = true;
        for (int m : numbers){
            int count = 0;
            if(m == 0){
                return false;
            }
            for (int n = 1; n<=9; n++){
                if(m==n){
                    count++;
                }
            }
            if (count != 1){
                ok = false;
            }
        }
        return ok;
    }

}
