import actions.DefaultAction;
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
    int emptySquares;

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
        grids[8] = makeHardGridFromPcGame().getSquares();
        grids[9] = makeARandomGrid(30).getSquares();

    }

    private void gameLoop() {
        loop = 0;
        boolean newGame = true;
        // Play the new game
        while (newGame) {
            // Create a boolean to indicate when the game is finished
            boolean finished = false;
            // Make a player
            Player player = Player.whichPlayer();
            // Introduce the player
            player.introduce();
            // How many empty squares should the grid have?
            emptySquares = player.howManyEmptySquares();
            // Give the player a new grid
            player.setupGrid(getNewRandomSudokuGrid(emptySquares));
            // Loop until finished
            while (!finished) {
                // Assess what to do next
                finished = player.assess();
                // Get the next action
                PlayerAction next = player.getNextAction();
                if(next instanceof DefaultAction){
                    // explain that this is the default player
                    player.explain(next);
                    // Give up the grid
                    SudokuGrid grid = player.giveUpGrid();
                    // prompt to switch players
                    player = player.switchPlayer();
                    // Set up the grid
                    player.setupGrid(grid.getSquares());
                    continue;
                } else {
                    // explain what's going to happen next
                    player.explain(next);
                }
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

    private int[] getNewRandomSudokuGrid(int emptySquares) {
        return makeARandomGrid(emptySquares).getSquares();
    }

    private int[] getNewSudokuGrid(int index) {
        if (index > grids.length) {
            throw new RuntimeException("No more grids!");
        }
        return grids[index];
    }

}
