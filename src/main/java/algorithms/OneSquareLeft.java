package algorithms;

import model.SudokuGrid;

import static service.SudokuGridChecker.checkSetForNumber;
import static service.SudokuGridObserver.*;

public class OneSquareLeft implements SudokuGridSolverAlgorithm{
    @Override
    public SudokuGrid solve(SudokuGrid grid) {
        int[][] boxes = grid.getBoxes();
        for (int b = 0; b < boxes.length; b++) {
            int[] box0 = boxes[b];
            int[] verticallyAdjacentBoxes = getVerticallyAdjacentBoxNumbers(b);
            int[] box1 = boxes[verticallyAdjacentBoxes[0]];
            int[] box2 = boxes[verticallyAdjacentBoxes[1]];
            for (int p = 0; p < 9; p++) {
                if (box0[p] == 0) {
                    if(theOtherTwoBoxesHaveTheSameNumberInTheAdjacentColumns(box1,box2,p)) {
                        box0[p] = theNumberTheOtherTwoBoxesHaveTheSameInTheAdjacentColumns(box1, box2, p);
                        grid.setBoxes(boxes);
                        return grid;
                    }
                }
            }
        }return grid;
    }

    int theNumberTheOtherTwoBoxesHaveTheSameInTheAdjacentColumns(int[] box1, int[] box2, int position) {
        for (int n = 1; n < 10; n++) {
            int[] vAdjacent1 = getVerticallyAdjacentSquaresInBox(box1,
                    position);
            int[] vAdjacent2 = getVerticallyAdjacentSquaresInBox(box2,
                    position);
            if (checkSetForNumber(n, vAdjacent1) && checkSetForNumber(n,
                    vAdjacent2)) {
                return n;
            }
        }
        return 0;
    }

    @Override
    public String explanation() {
        return """
                One Square Left.
                This algorithm looks at three boxes and determines if two of the 
                boxes already contain a given number, and if the third box which
                does not contain the number only has one square where the number
                could be added.
                """;
    }
}
