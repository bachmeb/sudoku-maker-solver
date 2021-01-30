public class SudokuGridMaker {

    public SudokuGrid make(){
        //return new SudokuGrid();
        return privateMake();
    }

    private SudokuGrid privateMake(){
        SudokuGrid grid = new SudokuGrid();

        int[] cols;
        int[][] rows;

        cols = new int[9];
        rows = new int[9][];

        grid.setCols(cols);
        grid.setRows(rows);

        return grid;
    }

}
