import model.SudokuGrid;
import player.Player;
import player.PlayerAction;

import static player.PlayerUtil.print;
import static service.SudokuGridMaker.*;

public class Play {

    public static void main(String[] args) {
        Play lets = new Play();
        lets.go();
    }

    private void go() {
        // Begin game loop
        gameLoop();
    }

    private void gameLoop() {
        // create a sudoku grid
        SudokuGrid grid = getNewSudokuGrid();
        // Make a player
        Player player = new Player(grid);
        // Introduce the player
        player.introduce();
        // Create a boolean to indicate when the game is finished
        boolean finished = false;
        // Loop until finished
        while (!finished) {
            // Assess what to do next
            finished = player.assess();
            // Get the next action
            PlayerAction next = player.getNextAction();
            // explain what's going to happen next
            player.explain(next);
            // wait for input
            player.waitForInput("Ready?");
            // given input, do the next thing
            player.take(next);
        }
        print("Our game is over. Thanks for playing.");
    }

    private SudokuGrid getNewSudokuGrid() {
        //return makeAlmostSolvedGridWithOneMissingNumber();
        //return makeAlmostSolvedGridMissingLastRow();
        //return makeAlmostSolvedGridMissingLastColumn();
        //return makeAlmostSolvedGridMissingOneInEveryBox();
        //return makeHalfSolvedGrid();
        //return makeAlmostSolvedGridMissingOneRowInThreeBoxes();
        return makeGridFromPCGame();
    }


}
