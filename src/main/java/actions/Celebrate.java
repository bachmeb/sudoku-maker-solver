package actions;

import model.SudokuGrid;

import static player.PlayerUtil.print;

public class Celebrate implements PlayerAction {
    @Override
    public String explanation() {
        return "Celebrate!!!";
    }

    @Override
    public SudokuGrid move() {
        print("Woohoo!!");
        return null;
    }
}
