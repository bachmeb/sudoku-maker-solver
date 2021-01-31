import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuGridSolver {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridSolver.class);


    public SudokuGrid solve(SudokuGrid grid) {
        SudokuGridChecker checker = new SudokuGridChecker();

        int loop = 0;

        // is the grid solved?
        while (checker.checkGrid(grid) != true && loop < 100) {

            logger.info("loop number: " + loop++ + " - not yet solved");

            int[][] counts = new int[10][3]; // [numbers][box,row,column]

            // count the number of each number in each box
            for(int num = 1; num <10; num++){
                for(int[] box : grid.getBoxes()){
                    counts[num][0] = 0;
                    for(int i = 0; i < 9; i++){
                        if(num == box[i]){
                            counts[num][0]++;
                        }
                    }
                    logger.info("There are " + counts[num][0] + " " + num + "s in this box " + intArrayToString(box));
                }
            }


            // count the number of each number in each row
            for(int num = 1; num <10; num++){
                for(int[] row : grid.getRows()){
                    counts[num][1] = 0;
                    for(int i = 0; i < 9; i++){
                        if(num == row[i]){
                            counts[num][1]++;
                        }
                    }
                    logger.info("There are " + counts[num][1] + " " + num + "s in this row " + intArrayToString(row));
                }
            }

            // count the number of each number in each column
            for(int num = 1; num <10; num++){
                for(int[] column : grid.getColumns()){
                    counts[num][2] = 0;
                    for(int i = 0; i < 9; i++){
                        if(num == column[i]){
                            counts[num][2]++;
                        }
                    }
                    logger.info("There are " + counts[num][2] + " " + num + "s in this column " + intArrayToString(column));
                }
            }

            // count the number of numbers in each box

            // count the number of numbers in each row

            // count the number of numbers in each column

            // if the number of numbers in a given box is 8 then add the last number

            // if the number of numbers in a given row is 8 then add the last number

            // if the number of numbers in a given column is 8 then add the last number

            // start with the number found in the most boxes

            // go to each box without that number

            // go to each empty square in that box

            // see if that number is in any other square in the same row

            // see if that number is in any other square in the same column

            // determine if that number can be added to any other square in the same box

            // if a number can be added to an empty square and cannot be added to any other square in the same box, row, or square
            // then add that number to the empty square

        }

        logger.info("This grid is solved!!!");

        return grid;

    }

    private String intArrayToString(int[] arr){
        StringBuilder sb = new StringBuilder();

        if(arr == null){
            return "[]";
        }

        sb.append("[");
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i]);
        }
        sb.append("]");
        return sb.toString();
    }

}
