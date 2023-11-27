package player;

import actions.Celebrate;
import actions.PlayerAction;
import model.SudokuGrid;
import service.SudokuGridMaker;

import java.util.Scanner;

import static player.PlayerUtil.print;
import static service.SudokuGridChecker.checkGridSolved;
import static service.SudokuGridMaker.makeARandomGrid;
import static service.SudokuGridObserver.countAllFilledSquares;

public abstract class Player {

    PlayerAction[] plan = new PlayerAction[0];
    int nextAction = 0;
    SudokuGrid grid;
    SudokuGridMaker maker = new SudokuGridMaker();

    public void setupGrid(int[] squares) {
        this.grid = new SudokuGrid(squares);
        nextAction = 0;
        plan = new PlayerAction[0];
    }

    abstract public void introduce();

    abstract public PlayerAction determineNextAction(SudokuGrid grid);

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

    public int howManyEmptySquares() {
        Scanner scanner = new Scanner(System.in);
        print("How many empty squares?");
        return scanner.nextInt();
    }
    public static Player whichPlayer() {
        Scanner scanner = new Scanner(System.in);
        print("Which player do you want to try to solve the puzzle?");
        String answer = scanner.next();
        return switch (answer) {
            case "h" -> new HardPlayer();
            case "e" -> new EasyPlayer();
            case "m" -> new MediumPlayer();
            default -> new Player() {
                @Override
                public void introduce() {
                    print("I'm the default player");
                }

                @Override
                public PlayerAction determineNextAction(SudokuGrid grid) {
                    return null;
                }
            };
        };
    }


    public Player switchPlayer() {
        Scanner scanner = new Scanner(System.in);
        print("Do you want to switch players?");
        String answer = scanner.next();
        if(answer.contains("y")){
            return whichPlayer();
        }
        return this;
    }

    public void whichGridDoYouWant() {

        Scanner scanner = new Scanner(System.in);
        print("Which grid do you want to play?");
        String answer = scanner.next();

        int[] squares;
        switch (answer) {
            case "9" -> squares = maker.getNumberedGrid(9);
            case "10" ->squares= maker.getNumberedGrid(10);
            case "11" ->squares= maker.getNumberedGrid(11);
            default -> squares=maker.getNumberedGrid(0);
        };

        setupGrid(squares);

    }


    private int[] getNewRandomSudokuGrid(int emptySquares) {
        return makeARandomGrid(emptySquares).getSquares();
    }

//    private int[] getNewSudokuGrid(int index) {
//        if (index > grids.length) {
//            throw new RuntimeException("No more grids!");
//        }
//        return grids[index];
//    }


    public SudokuGrid giveUpGrid() {
        return grid;
    }
}
