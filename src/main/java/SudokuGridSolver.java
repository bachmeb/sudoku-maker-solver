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

            int countOfBoxesWithNumberIndexedByNumber[] = new int[10];
            int countOfRowsWithNumberIndexedByNumber[] = new int[10];
            int countOfColumnsWithNumberIndexedByNumber[] = new int[10];

            int countOfFilledSquaresIndexedByBoxNumber[] = new int[10];
            int countOfFilledSquaresIndexedByRowNumber[] = new int[10];
            int countOfFilledSquaresIndexedByColumnNumber[] = new int[10];

            // count the number of times each number appears in any box
            for(int num = 1; num <10; num++){
                int boxNumber = 1;
                for(int[] box : grid.getBoxes()){
                    countOfBoxesWithNumberIndexedByNumber[num] = 0;
                    for(int i = 0; i < 9; i++){
                        if(num == box[i]){
                            countOfBoxesWithNumberIndexedByNumber[num]++;
                        }
                    }
                    logger.info("There are " + countOfBoxesWithNumberIndexedByNumber[num] + " " + num + "s in box number " + boxNumber++ + " " + intArrayToString(box));
                }
            }

            // count the number of times each number appears in any row
            for(int num = 1; num <10; num++){
                int rowNumber = 1;
                for(int[] row : grid.getRows()){
                    countOfRowsWithNumberIndexedByNumber[num] = 0;
                    for(int i = 0; i < 9; i++){
                        if(num == row[i]){
                            countOfRowsWithNumberIndexedByNumber[num]++;
                        }
                    }
                    logger.info("There are " + countOfRowsWithNumberIndexedByNumber[num] + " " + num + "s in row number " + rowNumber++ + " - " + intArrayToString(row));
                }
            }

            // count the number of times each number appears in any column
            for(int num = 1; num <10; num++){
                int columnNumber = 1;
                for(int[] column : grid.getColumns()){
                    countOfColumnsWithNumberIndexedByNumber[num] = 0;
                    for(int i = 0; i < 9; i++){
                        if(num == column[i]){
                            countOfColumnsWithNumberIndexedByNumber[num]++;
                        }
                    }
                    logger.info("There are " + countOfColumnsWithNumberIndexedByNumber[num] + " " + num + "s in column number " + columnNumber++ + " - " + intArrayToString(column));
                }
            }

            // count the number of numbers in each box
            for(int num = 1; num <10; num++){
                for(int[] box : grid.getBoxes()){
                    countOfFilledSquaresIndexedByBoxNumber[num] = 0;
                    for(int i = 0; i < 9; i++){
                        if(box[i] > 0){
                            countOfFilledSquaresIndexedByBoxNumber[num]++;
                        }
                    }
                    logger.info("There are " + countOfFilledSquaresIndexedByBoxNumber[num] + " squares filled in box number " + num  + " - " + intArrayToString(box));
                }
            }

            // count the number of squares filled in each row
            for(int num = 1; num <10; num++){
                for(int[] row : grid.getRows()){
                    countOfFilledSquaresIndexedByRowNumber[num] = 0;
                    for(int i = 0; i < 9; i++){
                        if(row[i] > 0){
                            countOfFilledSquaresIndexedByRowNumber[num]++;
                        }
                    }
                    logger.info("There are " + countOfFilledSquaresIndexedByRowNumber[num] + " squares filled in row number " + num  + " - " + intArrayToString(row));
                }
            }

            // count the number of numbers in each column
            for(int num = 1; num <10; num++){
                for(int[] column : grid.getColumns()){
                    countOfFilledSquaresIndexedByColumnNumber[num] = 0;
                    for(int i = 0; i < 9; i++){
                        if(column[i] > 0){
                            countOfFilledSquaresIndexedByColumnNumber[num]++;
                        }
                    }
                    logger.info("There are " + countOfFilledSquaresIndexedByColumnNumber[num] + " squares filled in column number " + num  + " - " + intArrayToString(column));
                }
            }

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
