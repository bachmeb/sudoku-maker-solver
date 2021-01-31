public class SudokuGridMaker {

    public SudokuGrid makeEmptyGrid(){

        SudokuGrid grid = new SudokuGrid();

        int rows[][] = new int[9][];

        int row[]= new int[9];

        rows[0] = row;
        rows[1] = row;
        rows[2] = row;
        rows[3] = row;
        rows[4] = row;
        rows[5] = row;
        rows[6] = row;
        rows[7] = row;
        rows[8] = row;

        grid.setRows(rows);

        return grid;

    }


    public SudokuGrid makeZerosGrid(){

        SudokuGrid grid = new SudokuGrid();

        int rows[][] = new int[9][];

        int row[]= new int[9];

        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[0] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[1] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[2] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[3] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[4] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[5] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[6] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[7] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[8] = row;

        grid.setRows(rows);

        return grid;
    }

    public SudokuGrid makeOnesThruNinesGrid(){

        SudokuGrid grid = new SudokuGrid();
        
        int rows[][] = new int[9][];

        int row[]= new int[9];

        row[0] = 1;
        row[1] = 1;
        row[2] = 1;
        row[3] = 1;
        row[4] = 1;
        row[5] = 1;
        row[6] = 1;
        row[7] = 1;
        row[8] = 1;

        rows[0] = row;

        row = new int[9];
        row[0] = 2;
        row[1] = 2;
        row[2] = 2;
        row[3] = 2;
        row[4] = 2;
        row[5] = 2;
        row[6] = 2;
        row[7] = 2;
        row[8] = 2;

        rows[1] = row;

        row = new int[9];
        row[0] = 3;
        row[1] = 3;
        row[2] = 3;
        row[3] = 3;
        row[4] = 3;
        row[5] = 3;
        row[6] = 3;
        row[7] = 3;
        row[8] = 3;

        rows[2] = row;

        row = new int[9];
        row[0] = 4;
        row[1] = 4;
        row[2] = 4;
        row[3] = 4;
        row[4] = 4;
        row[5] = 4;
        row[6] = 4;
        row[7] = 4;
        row[8] = 4;

        rows[3] = row;

        row = new int[9];
        row[0] = 5;
        row[1] = 5;
        row[2] = 5;
        row[3] = 5;
        row[4] = 5;
        row[5] = 5;
        row[6] = 5;
        row[7] = 5;
        row[8] = 5;

        rows[4] = row;

        row = new int[9];
        row[0] = 6;
        row[1] = 6;
        row[2] = 6;
        row[3] = 6;
        row[4] = 6;
        row[5] = 6;
        row[6] = 6;
        row[7] = 6;
        row[8] = 6;

        rows[5] = row;

        row = new int[9];
        row[0] = 7;
        row[1] = 7;
        row[2] = 7;
        row[3] = 7;
        row[4] = 7;
        row[5] = 7;
        row[6] = 7;
        row[7] = 7;
        row[8] = 7;

        rows[6] = row;

        row = new int[9];
        row[0] = 8;
        row[1] = 8;
        row[2] = 8;
        row[3] = 8;
        row[4] = 8;
        row[5] = 8;
        row[6] = 8;
        row[7] = 8;
        row[8] = 8;

        rows[7] = row;

        row = new int[9];
        row[0] = 9;
        row[1] = 9;
        row[2] = 9;
        row[3] = 9;
        row[4] = 9;
        row[5] = 9;
        row[6] = 9;
        row[7] = 9;
        row[8] = 9;

        rows[8] = row;
        
        grid.setRows(rows);
        
        return grid;
    }

    public SudokuGrid makeSolvedGrid(){

        SudokuGrid grid = new SudokuGrid();

        int[] cols;
        int[][] rows;

        cols = new int[9];
        rows = new int[9][];

        cols[0] = 1;
        cols[1] = 2;
        cols[2] = 3;

        cols[3] = 4;
        cols[4] = 5;
        cols[5] = 6;

        cols[6] = 7;
        cols[7] = 8;
        cols[8] = 9;

        rows[0] = cols;

        cols = new int[9];

        cols[0] = 4;
        cols[1] = 5;
        cols[2] = 6;

        cols[3] = 7;
        cols[4] = 8;
        cols[5] = 9;

        cols[6] = 1;
        cols[7] = 2;
        cols[8] = 3;

        rows[1] = cols;

        cols = new int[9];

        cols[0] = 7;
        cols[1] = 8;
        cols[2] = 9;

        cols[3] = 1;
        cols[4] = 2;
        cols[5] = 3;

        cols[6] = 4;
        cols[7] = 5;
        cols[8] = 6;

        rows[2] = cols;

        ///
        cols = new int[9];

        cols[0] = 2;
        cols[1] = 3;
        cols[2] = 4;

        cols[3] = 5;
        cols[4] = 6;
        cols[5] = 7;

        cols[6] = 8;
        cols[7] = 9;
        cols[8] = 1;

        rows[3] = cols;

        cols = new int[9];

        cols[0] = 5;
        cols[1] = 6;
        cols[2] = 7;

        cols[3] = 8;
        cols[4] = 9;
        cols[5] = 1;

        cols[6] = 2;
        cols[7] = 3;
        cols[8] = 4;

        rows[4] = cols;

        cols = new int[9];

        cols[0] = 8;
        cols[1] = 9;
        cols[2] = 1;

        cols[3] = 2;
        cols[4] = 3;
        cols[5] = 4;

        cols[6] = 5;
        cols[7] = 6;
        cols[8] = 7;

        rows[5] = cols;

        ///
        cols = new int[9];

        cols[0] = 3;
        cols[1] = 4;
        cols[2] = 5;

        cols[3] = 6;
        cols[4] = 7;
        cols[5] = 8;

        cols[6] = 9;
        cols[7] = 1;
        cols[8] = 2;

        rows[6] = cols;

        cols = new int[9];

        cols[0] = 6;
        cols[1] = 7;
        cols[2] = 8;

        cols[3] = 9;
        cols[4] = 1;
        cols[5] = 2;

        cols[6] = 3;
        cols[7] = 4;
        cols[8] = 5;

        rows[7] = cols;

        cols = new int[9];

        cols[0] = 9;
        cols[1] = 1;
        cols[2] = 2;

        cols[3] = 3;
        cols[4] = 4;
        cols[5] = 5;

        cols[6] = 6;
        cols[7] = 7;
        cols[8] = 8;

        rows[8] = cols;

        grid.setRows(rows);

        return grid;

    }


    public SudokuGrid makeAlmostSolvedGridWithOneMissingNumber(){

        SudokuGrid grid = new SudokuGrid();

        int[] cols;
        int[][] rows;

        cols = new int[9];
        rows = new int[9][];

        cols[0] = 1;
        cols[1] = 2;
        cols[2] = 0; // <--- missing number

        cols[3] = 4;
        cols[4] = 5;
        cols[5] = 6;

        cols[6] = 7;
        cols[7] = 8;
        cols[8] = 9;

        rows[0] = cols;

        cols = new int[9];

        cols[0] = 4;
        cols[1] = 5;
        cols[2] = 6;

        cols[3] = 7;
        cols[4] = 8;
        cols[5] = 9;

        cols[6] = 1;
        cols[7] = 2;
        cols[8] = 3;

        rows[1] = cols;

        cols = new int[9];

        cols[0] = 7;
        cols[1] = 8;
        cols[2] = 9;

        cols[3] = 1;
        cols[4] = 2;
        cols[5] = 3;

        cols[6] = 4;
        cols[7] = 5;
        cols[8] = 6;

        rows[2] = cols;

        ///
        cols = new int[9];

        cols[0] = 2;
        cols[1] = 3;
        cols[2] = 4;

        cols[3] = 5;
        cols[4] = 6;
        cols[5] = 7;

        cols[6] = 8;
        cols[7] = 9;
        cols[8] = 1;

        rows[3] = cols;

        cols = new int[9];

        cols[0] = 5;
        cols[1] = 6;
        cols[2] = 7;

        cols[3] = 8;
        cols[4] = 9;
        cols[5] = 1;

        cols[6] = 2;
        cols[7] = 3;
        cols[8] = 4;

        rows[4] = cols;

        cols = new int[9];

        cols[0] = 8;
        cols[1] = 9;
        cols[2] = 1;

        cols[3] = 2;
        cols[4] = 3;
        cols[5] = 4;

        cols[6] = 5;
        cols[7] = 6;
        cols[8] = 7;

        rows[5] = cols;

        ///
        cols = new int[9];

        cols[0] = 3;
        cols[1] = 4;
        cols[2] = 5;

        cols[3] = 6;
        cols[4] = 7;
        cols[5] = 8;

        cols[6] = 9;
        cols[7] = 1;
        cols[8] = 2;

        rows[6] = cols;

        cols = new int[9];

        cols[0] = 6;
        cols[1] = 7;
        cols[2] = 8;

        cols[3] = 9;
        cols[4] = 1;
        cols[5] = 2;

        cols[6] = 3;
        cols[7] = 4;
        cols[8] = 5;

        rows[7] = cols;

        cols = new int[9];

        cols[0] = 9;
        cols[1] = 1;
        cols[2] = 2;

        cols[3] = 3;
        cols[4] = 4;
        cols[5] = 5;

        cols[6] = 6;
        cols[7] = 7;
        cols[8] = 8;

        rows[8] = cols;

        grid.setRows(rows);

        return grid;

    }


}
