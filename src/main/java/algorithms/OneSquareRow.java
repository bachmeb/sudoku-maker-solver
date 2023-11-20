package algorithms;

import model.SudokuGrid;

import static service.SudokuGridChecker.checkSetForNumber;
import static service.SudokuGridObserver.*;

public class OneSquareRow extends SudokuAlgorithm {

    @Override
    public int[] search(SudokuGrid grid) {
        return aBoxOnlyHasOneSquareLeftForANumber(grid);
    }

    int[] aBoxOnlyHasOneSquareLeftForANumber(SudokuGrid grid) {
        int[][] boxes = grid.getBoxes();
        for (int b = 0; b < boxes.length; b++) {
            int[] box0 = boxes[b];
            int[] horizontallyAdjacentBoxNumbers = getHorizontallyAdjacentBoxNumbers(b);
            int[] box1 = boxes[horizontallyAdjacentBoxNumbers[0]];
            int[] box2 = boxes[horizontallyAdjacentBoxNumbers[1]];
            for (int p = 0; p < 9; p++) {
                if (box0[p] == 0) {
                    if (theInnerRowHasMoreThanOneZero(box0, p)) {
                        continue;
                    }
                    if (theOtherTwoBoxesHaveTheSameNumberInTheAdjacentRows(box1, box2, p)) {
                        int v = theNumberTheOtherTwoBoxesHaveTheSameInTheAdjacentRows(box1, box2, p);
                        if (checkSetForNumber(v, box0)) {
                            continue;
                        }
                        int q = getSquareForBoxNumAndPosNum(b, p);
                        int[] squareAndValue = new int[2];
                        squareAndValue[0] = q;
                        squareAndValue[1] = v;
                        return squareAndValue;
                    }
                }
            }
        }
        return null;
    }

    //TODO - should be plural numbers
    int theNumberTheOtherTwoBoxesHaveTheSameInTheAdjacentRows(int[] box1,
                                                                 int[] box2,
                                                                 int position) {
        for (int n = 1; n < 10; n++) {
            int[] vAdjacent1 = getHorizontallyAdjacentSquaresInBox(box1,
                    position);
            int[] vAdjacent2 = getHorizontallyAdjacentSquaresInBox(box2,
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
                One Square Box Row.
                This algorithm looks at three boxes horizontally and determines if
                two of the boxes already contain a given number, and if the third
                box which does not contain the number only has one square where
                the number could be added.
                """;
    }
}
