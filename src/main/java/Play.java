import actions.DefaultAction;
import actions.PlayerAction;
import model.SudokuGrid;
import player.Player;

import static player.PlayerUtil.print;

public class Play {

    int loop;

    public static void main(String[] args) {
        Play lets = new Play();
        lets.go();
    }

    private void go() {
        gameLoop();
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
            // Give the player a new grid
            player.whichGridDoYouWant();
            // Loop until finished
            while (!finished) {
                // Assess what to do next
                finished = player.assess();
                // Get the next action
                PlayerAction next = player.getNextAction();
                if (next instanceof DefaultAction) {
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

}
