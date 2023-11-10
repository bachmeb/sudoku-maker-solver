package player;

import model.SudokuGrid;

import static player.PlayerUtil.print;

public class PlayerAction3 implements PlayerAction {
    @Override
    public String explanation() {
        return "this is what im going to do";
    }

    @Override
    public SudokuGrid move(SudokuGrid grid) {
        return grid;
    }
}
