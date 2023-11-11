package player;

import model.SudokuGrid;

public class Celebrate implements PlayerAction{
    @Override
    public String explanation() {
        return "Celebrate!!!";
    }

    @Override
    public SudokuGrid move(SudokuGrid grid) {
        return null;
    }
}
