package player;

import actions.*;
import model.SudokuGrid;

import java.util.Scanner;

import static player.PlayerUtil.print;
import static service.SudokuGridChecker.checkGridSolved;
import static service.SudokuGridObserver.countAllFilledSquares;

public abstract class Player {

    PlayerAction[] plan = new PlayerAction[0];
    int nextAction = 0;
    SudokuGrid grid;

    public void setGrid(SudokuGrid grid) {
        this.grid = grid;
        nextAction = 0;
        plan = new PlayerAction[0];
    }

    abstract public void introduce() ;
    abstract public PlayerAction determineNextAction(SudokuGrid grid) ;

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

    public boolean invite() {
        Scanner scanner = new Scanner(System.in);
        print("Do you want to make a new grid?");
        String response = scanner.next();
        return response.contains("y");
    }
}
