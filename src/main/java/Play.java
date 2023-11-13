import model.SudokuGrid;
import player.Player;
import player.PlayerAction;

import static player.PlayerUtil.print;
import static service.SudokuGridMaker.*;

public class Play {

    SudokuGrid[] grids;
    int loop;

    public static void main(String[] args) {
        Play lets = new Play();
        lets.go();
    }

    private void go() {
        buildGrids();
        gameLoop();
    }

    private void buildGrids() {
        grids = new SudokuGrid[10];
        grids[0] = makeAlmostSolvedGridWithOneMissingNumber();
        grids[1] = makeAlmostSolvedGridMissingLastRow();
        grids[2] = makeAlmostSolvedGridMissingLastColumn();
        grids[3] = makeAlmostSolvedGridMissingOneInEveryBox();
        grids[4] = makeHalfSolvedGrid();
        grids[5] = makeAlmostSolvedGridMissingOneRowInThreeBoxes();
        grids[6] = makeGridFromPCGame();
    }

    private void gameLoop() {
        loop = 0;
        // Make a player
        Player player = new Player();
        // Introduce the player
        player.introduce();
        // Offer to play a new game
        boolean newGame = player.invite();
        // Play the new game
        while (newGame) {
            // Create a boolean to indicate when the game is finished
            boolean finished = false;
            // Give the player a new grid
            player.setGrid(getNewSudokuGrid(loop));
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
            loop++;
            print("Our game is over. Thanks for playing.");
            // Prompt to play again
            newGame = player.invite();
        }
    }

    private SudokuGrid getNewSudokuGrid(int i) {
        if(i>grids.length){
            throw new RuntimeException("No more grids!");
        }
        return grids[i];
    }

}
