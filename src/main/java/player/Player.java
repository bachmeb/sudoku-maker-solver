package player;

import algorithms.AdjacentElimination;
import algorithms.OneSquareLeft;
import algorithms.SetsOfEight;
import algorithms.ThreeWayElimination;
import model.SudokuGrid;

import java.util.Scanner;

import static player.PlayerUtil.print;
import static service.SudokuGridChecker.checkGridSolved;
import static service.SudokuGridObserver.countAllFilledSquares;

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
        print(grid.toString());
        print("Turn: " + nextAction);
        int filled = countAllFilledSquares(grid);
        print("Filled squares: " + filled);
        print("Empty squares: " + (81 - filled));
        if (checkGridSolved(grid)) {
            updatePlan(new Celebrate());
            return true;
        } else {
            updatePlan(determineNextAction(grid));
            return false;
        }
    }

    private PlayerAction determineNextAction(SudokuGrid grid) {
        int[] qv;
        // Check for sets of eight
        qv = new SetsOfEight().search(grid);
        if (qv != null) {
            return new SolveBySetsOfEight(grid, qv[0], qv[1]);
        }
        // Check if a square can be solved by adjacent elimination
        qv = new AdjacentElimination().search(grid);
        if (qv != null) {
            return new SolveByAdjacentElimination(grid, qv[0], qv[1]);
        }
        // Check if a square can be solved by only one number
        qv = new ThreeWayElimination().search(grid);
        if (qv != null) {
            return new SolveByThreeWayElimination(grid, qv[0], qv[1]);
        }
        // Check if a square can be solved by adding the one value that fits
        qv = new OneSquareLeft().search(grid);
        if (qv != null) {
            return new SolveByOneSquareLeft(grid, qv[0], qv[1]);
        }
        updatePlan(new DefaultAction());
        return null;
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
        this.grid = next.move();
    }
}
