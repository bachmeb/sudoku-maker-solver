package actions;

import model.SudokuGrid;

import static player.PlayerUtil.print;

public class DefaultAction implements PlayerAction {
    SudokuGrid grid;
    public DefaultAction(SudokuGrid grid) {
        this.grid = grid;
    }

    @Override
    public String explanation() {
        return """
        This is the default action.
        
        I don't know what to do next....
        
        """;
    }

    @Override
    public SudokuGrid move() {
        print("..............looking at the same grid.");
        return grid;
    }
}
