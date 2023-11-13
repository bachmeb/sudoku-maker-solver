package algorithms;

import model.SudokuGrid;

import static service.SudokuGridObserver.getSquareForBoxNumAndPosNum;
import static service.SudokuGridObserver.whatCouldBeHere;

public class OneSquareBox extends SudokuAlgorithm {

    @Override
    public int[] search(SudokuGrid grid) {
        return aBoxHasASquareThatCanOnlyTakeOne(grid);
    }

    int[] aBoxHasASquareThatCanOnlyTakeOne(SudokuGrid grid) {
        int[][] boxes = grid.getBoxes();
        for (int b = 0; b < boxes.length; b++) {
            int[][] couldBesByPos = new int[9][];
            for (int p = 0; p < 9; p++) {
                if (boxes[b][p] == 0) {
                    int q = getSquareForBoxNumAndPosNum(b, p);
                    couldBesByPos[p] = whatCouldBeHere(grid, q);
                }
            }
            int[][] countByNum = new int[10][];
            for (int x = 0; x < couldBesByPos.length; x++) {
                if (couldBesByPos[x] != null) {
                    for (int y = 0; y < couldBesByPos[x].length; y++) {
                        if (countByNum[couldBesByPos[x][y]] == null) {
                            countByNum[couldBesByPos[x][y]] = new int[0];
                        }
                        int[] temp =
                                new int[countByNum[couldBesByPos[x][y]].length + 1];
                        System.arraycopy(countByNum[couldBesByPos[x][y]], 0,
                                temp, 0,
                                countByNum[couldBesByPos[x][y]].length);

                        temp[temp.length - 1] = x;
                        countByNum[couldBesByPos[x][y]] = temp;
                    }
                }
            }
            for (int i = 1; i < countByNum.length; i++) {
                if (countByNum[i] != null) {
                    if (countByNum[i].length == 1) {
                        int[] squareAndValue = new int[2];
                        squareAndValue[0] = getSquareForBoxNumAndPosNum(b,
                                countByNum[i][0]); //
                        squareAndValue[1] = i;
                        return squareAndValue;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String explanation() {
        return """
                One Square Box.
                This algorithm looks at every square in a box and determines what
                could be added to each square by looking at the corresponding column,
                row, and box, and after looking at each box fills in any square that
                has a potential number that none of the other squares have.
                """;
    }
}
