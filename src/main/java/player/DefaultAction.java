package player;

import model.SudokuGrid;

public class DefaultAction implements PlayerAction {
    @Override
    public String explanation() {
        return "this is the default action";
    }

    @Override
    public SudokuGrid move(SudokuGrid grid) {
        return grid;
    }
}
