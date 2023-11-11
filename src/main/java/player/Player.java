package player;

import model.SudokuGrid;

import java.util.Scanner;

import static player.PlayerUtil.print;
import static service.SudokuGridChecker.checkGridSolved;
import static service.SudokuGridObserver.*;

public class Player {

    PlayerAction[] plan = new PlayerAction[3];
    int nextAction = 0;
    SudokuGrid grid;

    public Player(SudokuGrid grid) {
        this.grid = grid;
    }

    public void introduce() {
        print("Hi! We're going to play Sudoku. Let's solve this puzzle " +
                "together.");
    }

    public PlayerAction getNextAction() {
        return plan[nextAction];
    }

    public boolean assess() {
        // print the grid
        print(grid.toString());
        // Create a default action
        PlayerAction next = new DefaultAction();
        // Print the turn number
        print("Turn: " + nextAction);
        // Print the number of filled squares
        int filled = countAllFilledSquares(grid);
        print("Filled squares: " + filled);
        // Print the number of empty squares
        print("Empty squares: " + (81 - filled));
        // Preface to new action
        System.out.print("Next action: ");
        // Check if the grid is solved
        if (checkGridSolved(grid)) {
            next = new Celebrate();
            updatePlan(next);
            return true;
        }
        // Check for sets of eight
        if (aDimensionHasEight(grid)) {
            next = new SolveBySetsOfEight();
            updatePlan(next);
            return false;        }
        ;
        // Check if a square can be solved by only one number
        if (aSquareCanBeSolvedByOnlyOne(grid)) {
            next = new SolveByThreeWayElimination();
            updatePlan(next);
            return false;        }
        ;
        updatePlan(next);
        return false;
    }

    private boolean aSquareCanBeSolvedByOnlyOne(SudokuGrid grid) {
        int[] boxes = countNumberAppearancesByDimension(grid.getBoxes());
        int[] columns = countNumberAppearancesByDimension(grid.getColumns());
        int[] rows = countNumberAppearancesByDimension(grid.getRows());
        for (int i = 1; i < boxes.length; i++) {
            if ((boxes[i] == 8)) {
                return true;
            }
            if ((columns[i] == 8)) {
                return true;
            }
            if ((rows[i] == 8)) {
                return true;
            }
        }
        return false;
    }

    private boolean aDimensionHasEight(SudokuGrid grid) {
        int[] values;
        // Check columns for sets of eight
        values = countValuesInColumns(grid);
        for (int j : values) {
            if (j == 8) {
                return true;
            }
        }
        // Check rows for sets of eight
        values = countValuesInRows(grid);
        for (int j : values) {
            if (j == 8) {
                return true;
            }
        }
        // Check boxes for sets of eight
        values = countValuesInBoxes(grid);
        for (int j : values) {
            if (j == 8) {
                return true;
            }
        }
        return false;
    }

    public void updatePlan(PlayerAction next) {
        PlayerAction[] newPlan = new PlayerAction[nextAction + 1];
        System.arraycopy(plan, 0, newPlan, 0, nextAction);
        newPlan[newPlan.length - 1] = next;
        plan = newPlan;
    }

    public void waitForInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        print(prompt);
        scanner.nextLine();
    }

    public void explain(PlayerAction next) {
        print(next.explanation());
    }

    public boolean take(PlayerAction next) {
        nextAction++;
        this.grid = next.move(this.grid);
        return checkGridSolved(this.grid);
    }
}
