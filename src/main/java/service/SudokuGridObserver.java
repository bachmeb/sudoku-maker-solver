package service;

import model.SudokuGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.SudokuGridView;

public class SudokuGridObserver {

    static final Logger logger =
            LoggerFactory.getLogger(SudokuGridObserver.class);


    public static int countAllFilledSquares(SudokuGrid grid) {
        int count = 0;
        for (int square : grid.getSquares()) {
            if (square > 0) {
                count++;
            }
        }
        return count;
    }

    private static int[]  countFilledSquaresByDimension(int[][] things) {
        int[] countOfFilledSquaresInThing = new int[things.length];
        int thingNum = 0;
        for (int[] thing : things) {
            countOfFilledSquaresInThing[thingNum] = 0;
            for (int value : thing) {
                if (value > 0) {
                    countOfFilledSquaresInThing[thingNum]++;
                }
            }
            thingNum++;
        }
        return countOfFilledSquaresInThing;
    }




    private int[] countNumberAppearancesByDimension(int[][] slices) {
        // count the number of times each number appears in any slice of the given dimension
        int[] countByDimension = new int[10];
        for (int num = 1; num < 10; num++) {
            countByDimension[num] = 0;
            for (int[] slice : slices) {
                for (int i = 0; i < 9; i++) {
                    if (num == slice[i]) {
                        countByDimension[num]++;
                    }
                }
            }
        }
        return countByDimension;
    }



    public static int[] countValuesInBoxes(SudokuGrid grid){
        return countFilledSquaresByDimension(grid.getBoxes()) ;
    }
    public static int[] countValuesInColumns(SudokuGrid grid){
        return countFilledSquaresByDimension(grid.getColumns()) ;
    }
    public static int[] countValuesInRows(SudokuGrid grid){
        return countFilledSquaresByDimension(grid.getRows()) ;
    }

    public int[] getCountOfBoxesWithNumberIndexedByNumber(SudokuGrid grid) {
        return   countNumberAppearancesByDimension(grid.getBoxes());
    }



}
