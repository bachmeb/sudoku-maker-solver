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


    public static int[] countValuesInBoxes(SudokuGrid grid){
        return countFilledSquaresByDimension(grid.getBoxes()) ;
    }
    public static int[] countValuesInColumns(SudokuGrid grid){
        return countFilledSquaresByDimension(grid.getColumns()) ;
    }
    public static int[] countValuesInRows(SudokuGrid grid){
        return countFilledSquaresByDimension(grid.getRows()) ;
    }

    public int[] getCountOfBoxesWithNumberIndexedByNumber() {
        return countOfColumnsWithNumberIndexedByNumber;
    }

    void setCountOfBoxesWithNumberIndexedByNumber(int[] countOfColumnsWithNumberIndexedByNumber) {
        this.countOfColumnsWithNumberIndexedByNumber = countOfColumnsWithNumberIndexedByNumber;
    }

    public int[] getCountOfFilledSquaresIndexedByBoxNumber() {
        return countOfFilledSquaresIndexedByBoxNumber;
    }

    void setCountOfFilledSquaresIndexedByBoxNumber(int[] countOfFilledSquaresIndexedByBoxNumber) {
        this.countOfFilledSquaresIndexedByBoxNumber = countOfFilledSquaresIndexedByBoxNumber;
    }

    public int[] getCountOfFilledSquaresIndexedByRowNumber() {
        return countOfFilledSquaresIndexedByRowNumber;
    }

    void setCountOfFilledSquaresIndexedByRowNumber(int[] countOfFilledSquaresIndexedByRowNumber) {
        this.countOfFilledSquaresIndexedByRowNumber = countOfFilledSquaresIndexedByRowNumber;
    }

    public int[] getCountOfFilledSquaresIndexedByColumnNumber() {
        return countOfFilledSquaresIndexedByColumnNumber;
    }

    void setCountOfFilledSquaresIndexedByColumnNumber(int[] countOfFilledSquaresIndexedByColumnNumber) {
        this.countOfFilledSquaresIndexedByColumnNumber = countOfFilledSquaresIndexedByColumnNumber;
    }


}
