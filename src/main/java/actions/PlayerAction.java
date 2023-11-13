package actions;

import model.SudokuGrid;

public interface PlayerAction {
    String explanation();

    SudokuGrid move();
}
