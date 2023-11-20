import actions.PlayerAction;
import model.SudokuGrid;
import player.HardPlayer;
import player.MediumPlayer;
import player.Player;

import static player.PlayerUtil.print;
import static service.SudokuGridMaker.*;

public class Play {

    int[][] grids;
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
        grids = new int[10][];
        grids[0] = makeAlmostSolvedGridWithOneMissingNumber().getSquares();
        grids[1] = makeAlmostSolvedGridMissingLastRow().getSquares();
        grids[2] = makeAlmostSolvedGridMissingLastColumn().getSquares();
        grids[3] = makeAlmostSolvedGridMissingOneInEveryBox().getSquares();
        grids[4] = makeHalfSolvedGrid().getSquares();
        grids[5] = makeAlmostSolvedGridMissingOneRowInThreeBoxes().getSquares();
        grids[6] = makeGridFromPCGame().getSquares();
        grids[7] = makeMediumGridFromPcGame().getSquares();
        grids[0] = makeHardGridFromPcGame().getSquares();
        grids[0] = makeARandomGrid().getSquares();

    }

    private void gameLoop() {
        loop = 0;
        // Make a player
        Player player = new HardPlayer();
        // Introduce the player
        player.introduce();
        // Offer to play a new game
        boolean newGame = player.invite();
        // Play the new game
        while (newGame) {
            // Create a boolean to indicate when the game is finished
            boolean finished = false;
            // Give the player a new grid
            player.setupGrid(getNewSudokuGrid(loop));
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

    private int[] getNewSudokuGrid(int index) {
        if (index > grids.length) {
            throw new RuntimeException("No more grids!");
        }
        return grids[index];
    }

}
