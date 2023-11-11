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
        // Print the turn number
        print("Turn: " + nextAction);
        // Print the number of filled squares
        int filled = countAllFilledSquares(grid);
        print("Filled squares: " + filled);
        // Print the number of empty squares
        print("Empty squares: " + (81 - filled));
        // Check if the grid is solved
        if (checkGridSolved(grid)) {
            updatePlan(new Celebrate());
            return true;
        }
        // Check for sets of eight
        if (aDimensionHasEight(grid)) {
            updatePlan(new SolveBySetsOfEight());
            return false;
        }
        // Check if a square can be solved by only one number
        if (aSquareCanBeSolvedByOnlyOne(grid)) {
            updatePlan(new SolveByThreeWayElimination());
            return false;
        }
        // Check if a square can be solved by adjacent elimination
        if (aSquareCanBeSolvedByAdjacentElimination(grid)) {
            updatePlan(new SolveByAdjacentElimination());
            return false;
        }
        updatePlan(new DefaultAction());
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
        scanner.next();
        print("Here we go....");
    }

    public void explain(PlayerAction next) {
        // Preface to new action
        System.out.print("Next action: ");
        print(next.explanation());
    }

    public void take(PlayerAction next) {
        nextAction++;
        this.grid = next.move(this.grid);
    }
}
