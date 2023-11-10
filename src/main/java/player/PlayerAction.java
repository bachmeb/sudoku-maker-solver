package player;

import model.SudokuGrid;

public interface PlayerAction {
    String explanation();

    SudokuGrid move(SudokuGrid grid);
}
