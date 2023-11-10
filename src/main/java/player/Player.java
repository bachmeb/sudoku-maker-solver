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

    public void introduce() {
        print("Hi! We're going to play Sudoku. Let's solve this puzzle " +
                "together.");
    }

    public PlayerAction getNextAction() {
        return plan[nextAction];
    }

    public void assess(SudokuGrid grid) {
        this.grid = grid;
        PlayerAction next = new DefaultAction();
        // Check if the grid is solved
        if (checkGridSolved(grid)) {
            next = new Celebrate();
        }
        // Check for sets of eight
        if (aDimensionHasEight(grid)){
            next = new SolveBySetsOfEight();
        };
        // Check if a square can be solved by only one number
        if (aSquareCanBeSolvedByOnlyOne(grid)){
            next = new SolveByThreeWayElimination();
        };
        updatePlan(next);
    }

    private boolean aSquareCanBeSolvedByOnlyOne(SudokuGrid grid) {
        return false;
    }

    private boolean aDimensionHasEight(SudokuGrid grid) {
        int[] values;
        // Check columns for sets of eight
        values = countValuesInColumns(grid);
        for(int j : values){
            if(j==8){
                return true;
            }
        }
        // Check rows for sets of eight
        values = countValuesInRows(grid);
        for(int j : values){
            if(j==8){
                return true;
            }
        }
        // Check boxes for sets of eight
        values = countValuesInBoxes(grid);
        for(int j : values){
            if(j==8){
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
        String response = scanner.nextLine();
        print(response);
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
