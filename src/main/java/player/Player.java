package player;

import model.SudokuGrid;

import java.util.Scanner;

import static player.PlayerUtil.print;

public class Player {

    PlayerAction[] plan = new PlayerAction[3];
    int nextAction = 0;
    SudokuGrid grid;

    public void introduce() {
        print("Hi, We're going to play Sudoku. Let's solve this puzzle together.");
    }

    public PlayerAction getNextAction() {
        return plan[nextAction];
    }

    public void assess(SudokuGrid grid) {
        this.grid = grid;
        // Check if the grid is solved
        // Check columns for sets of eight
        // Check rows for sets of eight
        // Check boxes for sets of eight
        //
        // Check for sets of eight
        //
        PlayerAction next = new SolveBySetsOfEight();
        updatePlan(next);
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
        String response = scanner.nextLine();
        print(response);
    }

    public boolean finished(SudokuGrid grid) {
        return true;
    }

    public void explain(PlayerAction next) {
        print(next.explanation());
    }

    public SudokuGrid take(PlayerAction next) {
        nextAction++;
        return next.move(grid);
    }
}
