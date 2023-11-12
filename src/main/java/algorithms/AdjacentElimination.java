package algorithms;

import model.SudokuGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static service.SudokuGridChecker.checkBoxAndAdjacentRowsAndColumnsForNumber;
import static service.SudokuGridChecker.checkGridForErrors;

public class AdjacentElimination implements SudokuGridSolverAlgorithm {

    static final Logger logger =
            LoggerFactory.getLogger(AdjacentElimination.class);

    @Override
    public SudokuGrid solve(SudokuGrid grid) {
        solveByEliminationInAdjacentBoxesHorizontal(grid);
        if (checkGridForErrors(grid)) {
            throw new RuntimeException("Grid has errors");
        }
        return grid;
    }

    @Override
    public String explanation() {
        return """
                Adjacent elimination.
                This algorithm looks at every square and checks to see for every number,
                1 through 9, if the number is present in the box and the adjacent rows
                and columns that intersect the box.
                """;
    }

    private void solveByEliminationInAdjacentBoxesHorizontal(SudokuGrid grid) {
        logger.info("solve by elimination in adjacent boxes and rows " +
                "horizontal");

        // get a list of numbers 1 - 9
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // loop through the list
        for (int n : numbers) {
            int[] squares = grid.getSquares();
            // loop through every square
            for (int q = 0; q < squares.length; q++) {
                if (checkBoxAndAdjacentRowsAndColumnsForNumber(grid, q, n)) {
                    squares[q] = n;
                    grid.setSquares(squares);
                    return;
                }
            }
        }
    }

}
