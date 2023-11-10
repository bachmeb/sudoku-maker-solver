package algorithms;

import model.SudokuGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.SudokuGridChecker;
import service.SudokuGridObserver;
import view.SudokuGridView;

public class AdjacentElimination implements SudokuGridSolverAlgorithm {

    static final Logger logger =
            LoggerFactory.getLogger(AdjacentElimination.class);

    SudokuGridObserver observer;

    @Override
    public SudokuGrid solve(SudokuGrid grid) {
        grid = solveByEliminationInAdjacentBoxesHorizontal(grid);
        return grid;
    }

    private SudokuGrid solveByEliminationInAdjacentBoxesHorizontal(SudokuGrid grid) {
        logger.info("solve by elimination in adjacent boxes and rows horizontal");

        // get a list of numbers 1 - 9
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // loop through the list
        for (int numberInMind : numbers) {

            // loop through every box
            for (int boxIndex = 0; boxIndex < grid.getBoxes().length; boxIndex++) {

                // see if the number in mind is in the box
                boolean numberInMindIsInBox = SudokuGridChecker.checkSetForNumber(numberInMind, grid.getBoxes()[boxIndex]);

                // if the number in mind isn't in the box
                if (!numberInMindIsInBox) {

                    // get the two boxes horizontally adjacent to the box
                    int[] otherTwoAdjacentBoxesHorizontal = findTwoHorizontallyAdjacentSquaresInBox(boxIndex);

                    // see if the number is in the other two horizontally adjacent boxes
                    int count = 0;
                    for (int hBoxIndex : otherTwoAdjacentBoxesHorizontal) {
                        boolean itsThere = SudokuGridChecker.checkSetForNumber(numberInMind, grid.getBoxes()[hBoxIndex]);
                        if (itsThere) {
                            count++;
                        }
                    }

                    // if yes then get the index numbers of the two rows that have the number in mind
                    if (count == 2) {
                        int otherRowsThatHaveTheNumberInMind[] = new int[2];
                        int otherRowsIndex = 0;
                        for (int i = 0; i < otherTwoAdjacentBoxesHorizontal.length; i++) {
                            int[] box = grid.getBoxes()[otherTwoAdjacentBoxesHorizontal[i]];
                            for (int pos = 0; pos < 9; pos++) {
                                if (box[pos] == numberInMind) {
                                    int rowNum = grid.findRowNumForBoxNumAndPosNum(otherTwoAdjacentBoxesHorizontal[i], pos);
                                    otherRowsThatHaveTheNumberInMind[otherRowsIndex++] = rowNum;
                                }
                            }
                        }

                        // select the three squares that aren't in the same row as the two that already have the number
                        int candidateSquares[] = new int[3];
                        int candidateSquaresPos[] = new int[3];
                        int candidateIndex = 0;
                        for (int posNum = 0; posNum < 9; posNum++) {
                            int rowNum = grid.findRowNumForBoxNumAndPosNum(boxIndex, posNum);
                            boolean inOther = false;
                            for (int i = 0; i < 2; i++) {
                                if (rowNum == otherRowsThatHaveTheNumberInMind[i]) {
                                    inOther = true;
                                }
                            }
                            if (!inOther) {
                                if (candidateIndex > 2) {
                                    throw new RuntimeException("What's happening???");
                                }
                                candidateSquares[candidateIndex] = grid.getBoxes()[boxIndex][posNum];
                                candidateSquaresPos[candidateIndex++] = posNum;
                            }
                        }

                        // see if two of the boxes are occupied by other numbers
                        int countOfOtherOccupied = 0;
                        for (int y = 0; y < 3; y++) {
                            if (candidateSquares[y] > 0) {
                                countOfOtherOccupied++;
                            }
                        }

                        // if so then add the number in mind to the empty box
                        if (countOfOtherOccupied == 2) {
                            for (int b = 0; b < 3; b++) {
                                if (candidateSquares[b] == 0) {
                                    int box[] = grid.getBoxes()[boxIndex];
                                    box[candidateSquaresPos[b]] = numberInMind;
                                    int[][] boxes = grid.getBoxes();
                                    boxes[boxIndex] = box;
                                    grid.setBoxes(boxes);
                                    return grid;
                                }
                            }
                        }

                        // if the count is one see if one of the available squares already has the number in mind in the same column
                        if (countOfOtherOccupied == 1) {
                            int firstSquarePos = -1;
                            boolean foundNumberInMindInFirstColumn = false;
                            for (int b = 0; b < 3; b++) {
                                if (candidateSquares[b] == 0) {
                                    if (foundNumberInMindInFirstColumn) {
                                        int box[] = grid.getBoxes()[boxIndex];
                                        box[candidateSquaresPos[b]] = numberInMind;
                                        int[][] boxes = grid.getBoxes();
                                        boxes[boxIndex] = box;
                                        grid.setBoxes(boxes);
                                        return grid;
                                    } else if (firstSquarePos == -1) {
                                        firstSquarePos = candidateSquaresPos[b];
                                    }
                                    // get the column of numbers for that square
                                    int colNum = grid.findColNumForBoxNumAndPosNum(boxIndex, candidateSquaresPos[b]);
                                    int[] column = grid.getColumns()[colNum];
                                    logger.debug(SudokuGridView.intArrayToString(column));

                                    // see if that number is in any other square in that column
                                    for (int c = 0; c < column.length; c++) {
                                        if (column[c] == numberInMind) {
                                            if (foundNumberInMindInFirstColumn == false && firstSquarePos == -1) {
                                                foundNumberInMindInFirstColumn = true;
                                            } else if (foundNumberInMindInFirstColumn == false && firstSquarePos > -1) {
                                                int box[] = grid.getBoxes()[boxIndex];
                                                box[candidateSquaresPos[b]] = numberInMind;
                                                int[][] boxes = grid.getBoxes();
                                                boxes[boxIndex] = box;
                                                grid.setBoxes(boxes);
                                                return grid;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return grid;
    }

    private SudokuGrid solveByEliminationInAdjacentBoxesVertical(SudokuGrid grid) {
        logger.info("solve by elimination in adjacent boxes and rows vertical");

        // get a list of numbers 1 - 9
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // loop through the list
        for (int numberInMind : numbers) {

            // loop through every box
            for (int boxIndex = 0; boxIndex < grid.getBoxes().length; boxIndex++) {

                // see if the number in mind is in the box
                boolean numberInMindIsInBox = SudokuGridChecker.checkSetForNumber(numberInMind, grid.getBoxes()[boxIndex]);

                // if the number in mind isn't in the box
                if (!numberInMindIsInBox) {

                    // get the two boxes vertically adjacent to the box
                    int[] adjacentBoxesVertical = findTwoVerticallyAdjacentSquaresInABox(boxIndex);

                    // see if the number is in the other two adjacent boxes
                    int count = 0;
                    for (int vBoxIndex : adjacentBoxesVertical) {
                        boolean itsThere = SudokuGridChecker.checkSetForNumber(numberInMind, grid.getBoxes()[vBoxIndex]);
                        if (itsThere) {
                            count++;
                        }
                    }

                    // if yes then get the index numbers of the columns that have the number in mind
                    if (count == 2) {
                        int otherColNums[] = new int[2];
                        int otherColsIndex = 0;
                        for (int i = 0; i < adjacentBoxesVertical.length; i++) {
                            int[] box = grid.getBoxes()[adjacentBoxesVertical[i]];
                            for (int pos = 0; pos < 9; pos++) {
                                if (box[pos] == numberInMind) {
                                    int colNum = grid.findColNumForBoxNumAndPosNum(adjacentBoxesVertical[i], pos);
                                    otherColNums[otherColsIndex++] = colNum;
                                }
                            }
                        }

                        // select the three squares in the column of the box that isn't in the same column as the two that already have the number
                        int candidateSquares[] = new int[3];
                        int candidateSquaresPos[] = new int[3];
                        int candidateIndex = 0;
                        for (int posNum = 0; posNum < 9; posNum++) {
                            int colNum = grid.findColNumForBoxNumAndPosNum(boxIndex, posNum);
                            boolean inOther = false;
                            for (int i = 0; i < 2; i++) {
                                if (colNum == otherColNums[i]) {
                                    inOther = true;
                                }
                            }
                            if (!inOther) {
                                if (candidateIndex > 2) {
                                    throw new RuntimeException("What's happening???");
                                }
                                candidateSquares[candidateIndex] = grid.getBoxes()[boxIndex][posNum];
                                candidateSquaresPos[candidateIndex++] = posNum;
                                logger.debug("candidateSquaresPos[ candidateIndex ]: " + candidateIndex);
                            }
                        }

                        // see if two of the boxes are occupied by other numbers
                        int countOfOtherOccupied = 0;
                        for (int y = 0; y < 3; y++) {
                            if (candidateSquares[y] > 0) {
                                countOfOtherOccupied++;
                            }
                        }

                        // if so then add the number in mind to the empty box
                        if (countOfOtherOccupied == 2) {
                            for (int b = 0; b < 3; b++) {
                                if (candidateSquares[b] == 0) {
                                    int box[] = grid.getBoxes()[boxIndex];
                                    box[candidateSquaresPos[b]] = numberInMind;
                                    int[][] boxes = grid.getBoxes();
                                    boxes[boxIndex] = box;
                                    grid.setBoxes(boxes);
                                    return grid;
                                }
                            }
                        }
                    }
                }
            }
        }
        return grid;
    }


    private int[] findTwoHorizontallyAdjacentSquaresInBox(int num) {
        int[] otherTwo = new int[2];
        switch (num) {
            case 0:
                otherTwo[0] = 1;
                otherTwo[1] = 2;
                break;
            case 1:
                otherTwo[0] = 0;
                otherTwo[1] = 2;
                break;
            case 2:
                otherTwo[0] = 0;
                otherTwo[1] = 1;
                break;
            case 3:
                otherTwo[0] = 4;
                otherTwo[1] = 5;
                break;
            case 4:
                otherTwo[0] = 3;
                otherTwo[1] = 5;
                break;
            case 5:
                otherTwo[0] = 3;
                otherTwo[1] = 4;
                break;
            case 6:
                otherTwo[0] = 7;
                otherTwo[1] = 8;
                break;
            case 7:
                otherTwo[0] = 6;
                otherTwo[1] = 8;
                break;
            case 8:
                otherTwo[0] = 6;
                otherTwo[1] = 7;
                break;
        }

        return otherTwo;

    }

    private int[] findTwoVerticallyAdjacentSquaresInABox(int num) {
        int[] otherTwo = new int[2];
        switch (num) {
            case 0:
                otherTwo[0] = 3;
                otherTwo[1] = 6;
                break;
            case 1:
                otherTwo[0] = 4;
                otherTwo[1] = 7;
                break;
            case 2:
                otherTwo[0] = 5;
                otherTwo[1] = 8;
                break;
            case 3:
                otherTwo[0] = 0;
                otherTwo[1] = 6;
                break;
            case 4:
                otherTwo[0] = 1;
                otherTwo[1] = 7;
                break;
            case 5:
                otherTwo[0] = 2;
                otherTwo[1] = 8;
                break;
            case 6:
                otherTwo[0] = 0;
                otherTwo[1] = 3;
                break;
            case 7:
                otherTwo[0] = 1;
                otherTwo[1] = 4;
                break;
            case 8:
                otherTwo[0] = 2;
                otherTwo[1] = 5;
                break;
        }

        return otherTwo;

    }

}
