import model.SudokuGrid;
import player.Player;
import player.PlayerAction;

import static player.PlayerUtil.print;

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
        SudokuGrid grid = new SudokuGrid();
        // print the grid
        print(grid.toString());
        // Make a player
        Player player = new Player();
        // Introduce the player
        player.introduce();
        // Create a boolean to indicate when the game is finished
        boolean finished = false;
        // Loop until finished
        while (!finished) {
            // Assess what to do next
            player.assess(grid);
            // Get the next action
            PlayerAction next = player.getNextAction();
            // explain what's going to happen next
            player.explain(next);
            // wait for input
            player.waitForInput("Ready?");
            // given input, do the next thing
            finished = player.take(next);
        }
        print("Our game is over. Thanks for playing.");
    }


}
