package service;

import model.SudokuGrid;

import java.util.Random;

import static player.PlayerUtil.print;
import static service.SudokuGridChecker.*;
import static service.SudokuGridObserver.whatCouldBeHere;

public class SudokuGridMaker {

    public static SudokuGrid makeEmptyGrid() {

        SudokuGrid grid = new SudokuGrid();

        int[][] rows = new int[9][];

        int[] row = new int[9];

        rows[0] = row;
        rows[1] = row;
        rows[2] = row;
        rows[3] = row;
        rows[4] = row;
        rows[5] = row;
        rows[6] = row;
        rows[7] = row;
        rows[8] = row;

        grid.setRows(rows);

        return grid;

    }

    public static SudokuGrid makeZerosGrid() {

        SudokuGrid grid = new SudokuGrid();

        int[][] rows = new int[9][];

        int[] row = new int[9];

        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[0] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[1] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[2] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[3] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[4] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[5] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[6] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[7] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[8] = row;

        grid.setRows(rows);

        return grid;
    }

    public static SudokuGrid makeOnesThruNinesGrid() {

        SudokuGrid grid = new SudokuGrid();

        int[][] rows = new int[9][];

        int[] row = new int[9];

        row[0] = 1;
        row[1] = 1;
        row[2] = 1;
        row[3] = 1;
        row[4] = 1;
        row[5] = 1;
        row[6] = 1;
        row[7] = 1;
        row[8] = 1;

        rows[0] = row;

        row = new int[9];
        row[0] = 2;
        row[1] = 2;
        row[2] = 2;
        row[3] = 2;
        row[4] = 2;
        row[5] = 2;
        row[6] = 2;
        row[7] = 2;
        row[8] = 2;

        rows[1] = row;

        row = new int[9];
        row[0] = 3;
        row[1] = 3;
        row[2] = 3;
        row[3] = 3;
        row[4] = 3;
        row[5] = 3;
        row[6] = 3;
        row[7] = 3;
        row[8] = 3;

        rows[2] = row;

        row = new int[9];
        row[0] = 4;
        row[1] = 4;
        row[2] = 4;
        row[3] = 4;
        row[4] = 4;
        row[5] = 4;
        row[6] = 4;
        row[7] = 4;
        row[8] = 4;

        rows[3] = row;

        row = new int[9];
        row[0] = 5;
        row[1] = 5;
        row[2] = 5;
        row[3] = 5;
        row[4] = 5;
        row[5] = 5;
        row[6] = 5;
        row[7] = 5;
        row[8] = 5;

        rows[4] = row;

        row = new int[9];
        row[0] = 6;
        row[1] = 6;
        row[2] = 6;
        row[3] = 6;
        row[4] = 6;
        row[5] = 6;
        row[6] = 6;
        row[7] = 6;
        row[8] = 6;

        rows[5] = row;

        row = new int[9];
        row[0] = 7;
        row[1] = 7;
        row[2] = 7;
        row[3] = 7;
        row[4] = 7;
        row[5] = 7;
        row[6] = 7;
        row[7] = 7;
        row[8] = 7;

        rows[6] = row;

        row = new int[9];
        row[0] = 8;
        row[1] = 8;
        row[2] = 8;
        row[3] = 8;
        row[4] = 8;
        row[5] = 8;
        row[6] = 8;
        row[7] = 8;
        row[8] = 8;

        rows[7] = row;

        row = new int[9];
        row[0] = 9;
        row[1] = 9;
        row[2] = 9;
        row[3] = 9;
        row[4] = 9;
        row[5] = 9;
        row[6] = 9;
        row[7] = 9;
        row[8] = 9;

        rows[8] = row;

        grid.setRows(rows);

        return grid;
    }

    public static SudokuGrid makeSolvedGrid() {

        SudokuGrid grid = new SudokuGrid();

        int[] row;
        int[][] rows;

        row = new int[9];
        rows = new int[9][9];

        row[0] = 1;
        row[1] = 2;
        row[2] = 3;

        row[3] = 4;
        row[4] = 5;
        row[5] = 6;

        row[6] = 7;
        row[7] = 8;
        row[8] = 9;

        rows[0] = row;

        row = new int[9];

        row[0] = 4;
        row[1] = 5;
        row[2] = 6;

        row[3] = 7;
        row[4] = 8;
        row[5] = 9;

        row[6] = 1;
        row[7] = 2;
        row[8] = 3;

        rows[1] = row;

        row = new int[9];

        row[0] = 7;
        row[1] = 8;
        row[2] = 9;

        row[3] = 1;
        row[4] = 2;
        row[5] = 3;

        row[6] = 4;
        row[7] = 5;
        row[8] = 6;

        rows[2] = row;

        ///
        row = new int[9];

        row[0] = 2;
        row[1] = 3;
        row[2] = 4;

        row[3] = 5;
        row[4] = 6;
        row[5] = 7;

        row[6] = 8;
        row[7] = 9;
        row[8] = 1;

        rows[3] = row;

        row = new int[9];

        row[0] = 5;
        row[1] = 6;
        row[2] = 7;

        row[3] = 8;
        row[4] = 9;
        row[5] = 1;

        row[6] = 2;
        row[7] = 3;
        row[8] = 4;

        rows[4] = row;

        row = new int[9];

        row[0] = 8;
        row[1] = 9;
        row[2] = 1;

        row[3] = 2;
        row[4] = 3;
        row[5] = 4;

        row[6] = 5;
        row[7] = 6;
        row[8] = 7;

        rows[5] = row;

        ///
        row = new int[9];

        row[0] = 3;
        row[1] = 4;
        row[2] = 5;

        row[3] = 6;
        row[4] = 7;
        row[5] = 8;

        row[6] = 9;
        row[7] = 1;
        row[8] = 2;

        rows[6] = row;

        row = new int[9];

        row[0] = 6;
        row[1] = 7;
        row[2] = 8;

        row[3] = 9;
        row[4] = 1;
        row[5] = 2;

        row[6] = 3;
        row[7] = 4;
        row[8] = 5;

        rows[7] = row;

        row = new int[9];

        row[0] = 9;
        row[1] = 1;
        row[2] = 2;

        row[3] = 3;
        row[4] = 4;
        row[5] = 5;

        row[6] = 6;
        row[7] = 7;
        row[8] = 8;

        rows[8] = row;

        grid.setRows(rows);

        return grid;

    }

    public static SudokuGrid makeAlmostSolvedGridWithOneMissingNumber() {

        SudokuGrid grid = new SudokuGrid();

        int[] row;
        int[][] rows;

        row = new int[9];
        rows = new int[9][];

        row[0] = 1;
        row[1] = 2;
        row[2] = 0; // <--- missing number

        row[3] = 4;
        row[4] = 5;
        row[5] = 6;

        row[6] = 7;
        row[7] = 8;
        row[8] = 9;

        rows[0] = row;

        row = new int[9];

        row[0] = 4;
        row[1] = 5;
        row[2] = 6;

        row[3] = 7;
        row[4] = 8;
        row[5] = 9;

        row[6] = 1;
        row[7] = 2;
        row[8] = 3;

        rows[1] = row;

        row = new int[9];

        row[0] = 7;
        row[1] = 8;
        row[2] = 9;

        row[3] = 1;
        row[4] = 2;
        row[5] = 3;

        row[6] = 4;
        row[7] = 5;
        row[8] = 6;

        rows[2] = row;

        ///
        row = new int[9];

        row[0] = 2;
        row[1] = 3;
        row[2] = 4;

        row[3] = 5;
        row[4] = 6;
        row[5] = 7;

        row[6] = 8;
        row[7] = 9;
        row[8] = 1;

        rows[3] = row;

        row = new int[9];

        row[0] = 5;
        row[1] = 6;
        row[2] = 7;

        row[3] = 8;
        row[4] = 9;
        row[5] = 1;

        row[6] = 2;
        row[7] = 3;
        row[8] = 4;

        rows[4] = row;

        row = new int[9];

        row[0] = 8;
        row[1] = 9;
        row[2] = 1;

        row[3] = 2;
        row[4] = 3;
        row[5] = 4;

        row[6] = 5;
        row[7] = 6;
        row[8] = 7;

        rows[5] = row;

        ///
        row = new int[9];

        row[0] = 3;
        row[1] = 4;
        row[2] = 5;

        row[3] = 6;
        row[4] = 7;
        row[5] = 8;

        row[6] = 9;
        row[7] = 1;
        row[8] = 2;

        rows[6] = row;

        row = new int[9];

        row[0] = 6;
        row[1] = 7;
        row[2] = 8;

        row[3] = 9;
        row[4] = 1;
        row[5] = 2;

        row[6] = 3;
        row[7] = 4;
        row[8] = 5;

        rows[7] = row;

        row = new int[9];

        row[0] = 9;
        row[1] = 1;
        row[2] = 2;

        row[3] = 3;
        row[4] = 4;
        row[5] = 5;

        row[6] = 6;
        row[7] = 7;
        row[8] = 8;

        rows[8] = row;

        grid.setRows(rows);

        return grid;

    }

    public static SudokuGrid makeAlmostSolvedGridMissingLastRow() {

        SudokuGrid grid = new SudokuGrid();

        int[] row;
        int[][] rows;

        row = new int[9];
        rows = new int[9][9];

        row[0] = 1;
        row[1] = 2;
        row[2] = 3;

        row[3] = 4;
        row[4] = 5;
        row[5] = 6;

        row[6] = 7;
        row[7] = 8;
        row[8] = 9;

        rows[0] = row;

        row = new int[9];

        row[0] = 4;
        row[1] = 5;
        row[2] = 6;

        row[3] = 7;
        row[4] = 8;
        row[5] = 9;

        row[6] = 1;
        row[7] = 2;
        row[8] = 3;

        rows[1] = row;

        row = new int[9];

        row[0] = 7;
        row[1] = 8;
        row[2] = 9;

        row[3] = 1;
        row[4] = 2;
        row[5] = 3;

        row[6] = 4;
        row[7] = 5;
        row[8] = 6;

        rows[2] = row;

        ///
        row = new int[9];

        row[0] = 2;
        row[1] = 3;
        row[2] = 4;

        row[3] = 5;
        row[4] = 6;
        row[5] = 7;

        row[6] = 8;
        row[7] = 9;
        row[8] = 1;

        rows[3] = row;

        row = new int[9];

        row[0] = 5;
        row[1] = 6;
        row[2] = 7;

        row[3] = 8;
        row[4] = 9;
        row[5] = 1;

        row[6] = 2;
        row[7] = 3;
        row[8] = 4;

        rows[4] = row;

        row = new int[9];

        row[0] = 8;
        row[1] = 9;
        row[2] = 1;

        row[3] = 2;
        row[4] = 3;
        row[5] = 4;

        row[6] = 5;
        row[7] = 6;
        row[8] = 7;

        rows[5] = row;

        ///
        row = new int[9];

        row[0] = 3;
        row[1] = 4;
        row[2] = 5;

        row[3] = 6;
        row[4] = 7;
        row[5] = 8;

        row[6] = 9;
        row[7] = 1;
        row[8] = 2;

        rows[6] = row;

        row = new int[9];

        row[0] = 6;
        row[1] = 7;
        row[2] = 8;

        row[3] = 9;
        row[4] = 1;
        row[5] = 2;

        row[6] = 3;
        row[7] = 4;
        row[8] = 5;

        rows[7] = row;

        row = new int[9];

        row[0] = 0;
        row[1] = 0;
        row[2] = 0;

        row[3] = 0;
        row[4] = 0;
        row[5] = 0;

        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[8] = row;

        grid.setRows(rows);

        return grid;

    }

    public static SudokuGrid makeAlmostSolvedGridMissingLastColumn() {

        SudokuGrid grid = new SudokuGrid();

        int[] row;
        int[][] rows;

        row = new int[9];
        rows = new int[9][9];

        row[0] = 1;
        row[1] = 2;
        row[2] = 3;

        row[3] = 4;
        row[4] = 5;
        row[5] = 6;

        row[6] = 7;
        row[7] = 8;
        row[8] = 0;

        rows[0] = row;

        row = new int[9];

        row[0] = 4;
        row[1] = 5;
        row[2] = 6;

        row[3] = 7;
        row[4] = 8;
        row[5] = 9;

        row[6] = 1;
        row[7] = 2;
        row[8] = 0;

        rows[1] = row;

        row = new int[9];

        row[0] = 7;
        row[1] = 8;
        row[2] = 9;

        row[3] = 1;
        row[4] = 2;
        row[5] = 3;

        row[6] = 4;
        row[7] = 5;
        row[8] = 0;

        rows[2] = row;

        ///
        row = new int[9];

        row[0] = 2;
        row[1] = 3;
        row[2] = 4;

        row[3] = 5;
        row[4] = 6;
        row[5] = 7;

        row[6] = 8;
        row[7] = 9;
        row[8] = 0;

        rows[3] = row;

        row = new int[9];

        row[0] = 5;
        row[1] = 6;
        row[2] = 7;

        row[3] = 8;
        row[4] = 9;
        row[5] = 1;

        row[6] = 2;
        row[7] = 3;
        row[8] = 0;

        rows[4] = row;

        row = new int[9];

        row[0] = 8;
        row[1] = 9;
        row[2] = 1;

        row[3] = 2;
        row[4] = 3;
        row[5] = 4;

        row[6] = 5;
        row[7] = 6;
        row[8] = 0;

        rows[5] = row;

        ///
        row = new int[9];

        row[0] = 3;
        row[1] = 4;
        row[2] = 5;

        row[3] = 6;
        row[4] = 7;
        row[5] = 8;

        row[6] = 9;
        row[7] = 1;
        row[8] = 0;

        rows[6] = row;

        row = new int[9];

        row[0] = 6;
        row[1] = 7;
        row[2] = 8;

        row[3] = 9;
        row[4] = 1;
        row[5] = 2;

        row[6] = 3;
        row[7] = 4;
        row[8] = 0;

        rows[7] = row;

        row = new int[9];

        row[0] = 9;
        row[1] = 1;
        row[2] = 2;

        row[3] = 3;
        row[4] = 4;
        row[5] = 5;

        row[6] = 6;
        row[7] = 7;
        row[8] = 0;

        rows[8] = row;

        grid.setRows(rows);

        return grid;

    }

    public static SudokuGrid makeAlmostSolvedGridMissingOneInEveryBox() {

        SudokuGrid grid = new SudokuGrid();

        int[] row;
        int[][] rows;

        row = new int[9];
        rows = new int[9][9];

        row[0] = 0;
        row[1] = 2;
        row[2] = 3;

        row[3] = 0;
        row[4] = 5;
        row[5] = 6;

        row[6] = 0;
        row[7] = 8;
        row[8] = 9;

        rows[0] = row;

        row = new int[9];

        row[0] = 4;
        row[1] = 5;
        row[2] = 6;

        row[3] = 7;
        row[4] = 8;
        row[5] = 9;

        row[6] = 1;
        row[7] = 2;
        row[8] = 3;

        rows[1] = row;

        row = new int[9];

        row[0] = 7;
        row[1] = 8;
        row[2] = 9;

        row[3] = 1;
        row[4] = 2;
        row[5] = 3;

        row[6] = 4;
        row[7] = 5;
        row[8] = 6;

        rows[2] = row;

        ///
        row = new int[9];

        row[0] = 0;
        row[1] = 3;
        row[2] = 4;

        row[3] = 0;
        row[4] = 6;
        row[5] = 7;

        row[6] = 0;
        row[7] = 9;
        row[8] = 1;

        rows[3] = row;

        row = new int[9];

        row[0] = 5;
        row[1] = 6;
        row[2] = 7;

        row[3] = 8;
        row[4] = 9;
        row[5] = 1;

        row[6] = 2;
        row[7] = 3;
        row[8] = 4;

        rows[4] = row;

        row = new int[9];

        row[0] = 8;
        row[1] = 9;
        row[2] = 1;

        row[3] = 2;
        row[4] = 3;
        row[5] = 4;

        row[6] = 5;
        row[7] = 6;
        row[8] = 7;

        rows[5] = row;

        ///
        row = new int[9];

        row[0] = 0;
        row[1] = 4;
        row[2] = 5;

        row[3] = 0;
        row[4] = 7;
        row[5] = 8;

        row[6] = 0;
        row[7] = 1;
        row[8] = 2;

        rows[6] = row;

        row = new int[9];

        row[0] = 6;
        row[1] = 7;
        row[2] = 8;

        row[3] = 9;
        row[4] = 1;
        row[5] = 2;

        row[6] = 3;
        row[7] = 4;
        row[8] = 5;

        rows[7] = row;

        row = new int[9];

        row[0] = 9;
        row[1] = 1;
        row[2] = 2;

        row[3] = 3;
        row[4] = 4;
        row[5] = 5;

        row[6] = 6;
        row[7] = 7;
        row[8] = 8;

        rows[8] = row;

        grid.setRows(rows);

        return grid;

    }

    public static SudokuGrid makeHalfSolvedGrid() {

        SudokuGrid grid = new SudokuGrid();

        int[] row;
        int[][] rows;

        row = new int[9];
        rows = new int[9][9];

        row[0] = 1;
        row[1] = 2;
        row[2] = 3;

        row[3] = 4;
        row[4] = 5;
        row[5] = 6;

        row[6] = 7;
        row[7] = 8;
        row[8] = 0;

        rows[0] = row;

        row = new int[9];

        row[0] = 4;
        row[1] = 0;
        row[2] = 6;

        row[3] = 7;
        row[4] = 0;
        row[5] = 9;

        row[6] = 1;
        row[7] = 0;
        row[8] = 3;

        rows[1] = row;

        row = new int[9];

        row[0] = 0;
        row[1] = 8;
        row[2] = 9;

        row[3] = 0;
        row[4] = 2;
        row[5] = 3;

        row[6] = 0;
        row[7] = 5;
        row[8] = 6;

        rows[2] = row;

        ///
        row = new int[9];

        row[0] = 2;
        row[1] = 3;
        row[2] = 0;

        row[3] = 5;
        row[4] = 6;
        row[5] = 0;

        row[6] = 8;
        row[7] = 9;
        row[8] = 0;

        rows[3] = row;

        row = new int[9];

        row[0] = 5;
        row[1] = 0;
        row[2] = 7;

        row[3] = 8;
        row[4] = 0;
        row[5] = 1;

        row[6] = 2;
        row[7] = 0;
        row[8] = 4;

        rows[4] = row;

        row = new int[9];

        row[0] = 0;
        row[1] = 9;
        row[2] = 1;

        row[3] = 0;
        row[4] = 3;
        row[5] = 4;

        row[6] = 0;
        row[7] = 6;
        row[8] = 7;

        rows[5] = row;

        ///
        row = new int[9];

        row[0] = 3;
        row[1] = 4;
        row[2] = 0;

        row[3] = 6;
        row[4] = 7;
        row[5] = 0;

        row[6] = 9;
        row[7] = 1;
        row[8] = 0;

        rows[6] = row;

        row = new int[9];

        row[0] = 6;
        row[1] = 0;
        row[2] = 8;

        row[3] = 9;
        row[4] = 0;
        row[5] = 2;

        row[6] = 3;
        row[7] = 0;
        row[8] = 5;

        rows[7] = row;

        row = new int[9];

        row[0] = 0;
        row[1] = 1;
        row[2] = 2;

        row[3] = 0;
        row[4] = 4;
        row[5] = 5;

        row[6] = 0;
        row[7] = 7;
        row[8] = 8;

        rows[8] = row;

        grid.setRows(rows);

        return grid;

    }

    public static SudokuGrid makeAlmostSolvedGridMissingOneRowInThreeBoxes() {

        SudokuGrid grid = new SudokuGrid();

        int[] row;
        int[][] rows;

        row = new int[9];
        rows = new int[9][9];

        row[0] = 0;
        row[1] = 0;
        row[2] = 0;

        row[3] = 4;
        row[4] = 5;
        row[5] = 6;

        row[6] = 7;
        row[7] = 8;
        row[8] = 9;

        rows[0] = row;

        row = new int[9];

        row[0] = 4;
        row[1] = 5;
        row[2] = 6;

        row[3] = 7;
        row[4] = 8;
        row[5] = 9;

        row[6] = 1;
        row[7] = 2;
        row[8] = 3;

        rows[1] = row;

        row = new int[9];

        row[0] = 7;
        row[1] = 8;
        row[2] = 9;

        row[3] = 1;
        row[4] = 2;
        row[5] = 3;

        row[6] = 4;
        row[7] = 5;
        row[8] = 6;

        rows[2] = row;

        ///
        row = new int[9];

        row[0] = 0;
        row[1] = 0;
        row[2] = 0;

        row[3] = 5;
        row[4] = 6;
        row[5] = 7;

        row[6] = 8;
        row[7] = 9;
        row[8] = 1;

        rows[3] = row;

        row = new int[9];

        row[0] = 5;
        row[1] = 6;
        row[2] = 7;

        row[3] = 8;
        row[4] = 9;
        row[5] = 1;

        row[6] = 2;
        row[7] = 3;
        row[8] = 4;

        rows[4] = row;

        row = new int[9];

        row[0] = 8;
        row[1] = 9;
        row[2] = 1;

        row[3] = 2;
        row[4] = 3;
        row[5] = 4;

        row[6] = 5;
        row[7] = 6;
        row[8] = 7;

        rows[5] = row;

        ///
        row = new int[9];

        row[0] = 0;
        row[1] = 0;
        row[2] = 0;

        row[3] = 6;
        row[4] = 7;
        row[5] = 8;

        row[6] = 9;
        row[7] = 1;
        row[8] = 2;

        rows[6] = row;

        row = new int[9];

        row[0] = 6;
        row[1] = 7;
        row[2] = 8;

        row[3] = 9;
        row[4] = 1;
        row[5] = 2;

        row[6] = 3;
        row[7] = 4;
        row[8] = 5;

        rows[7] = row;

        row = new int[9];

        row[0] = 9;
        row[1] = 1;
        row[2] = 2;

        row[3] = 3;
        row[4] = 4;
        row[5] = 5;

        row[6] = 6;
        row[7] = 7;
        row[8] = 8;

        rows[8] = row;

        grid.setRows(rows);

        return grid;

    }

    public static SudokuGrid makeAlmostSolvedGridMissingOneBox() {

        SudokuGrid grid = new SudokuGrid();

        int[] row;
        int[][] rows;

        row = new int[9];
        rows = new int[9][9];

        row[0] = 0;
        row[1] = 0;
        row[2] = 0;

        row[3] = 4;
        row[4] = 5;
        row[5] = 6;

        row[6] = 7;
        row[7] = 8;
        row[8] = 9;

        rows[0] = row;

        row = new int[9];

        row[0] = 0;
        row[1] = 0;
        row[2] = 0;

        row[3] = 7;
        row[4] = 8;
        row[5] = 9;

        row[6] = 1;
        row[7] = 2;
        row[8] = 3;

        rows[1] = row;

        row = new int[9];

        row[0] = 0;
        row[1] = 0;
        row[2] = 0;

        row[3] = 1;
        row[4] = 2;
        row[5] = 3;

        row[6] = 4;
        row[7] = 5;
        row[8] = 6;

        rows[2] = row;

        ///
        row = new int[9];

        row[0] = 2;
        row[1] = 3;
        row[2] = 4;

        row[3] = 5;
        row[4] = 6;
        row[5] = 7;

        row[6] = 8;
        row[7] = 9;
        row[8] = 1;

        rows[3] = row;

        row = new int[9];

        row[0] = 5;
        row[1] = 6;
        row[2] = 7;

        row[3] = 8;
        row[4] = 9;
        row[5] = 1;

        row[6] = 2;
        row[7] = 3;
        row[8] = 4;

        rows[4] = row;

        row = new int[9];

        row[0] = 8;
        row[1] = 9;
        row[2] = 1;

        row[3] = 2;
        row[4] = 3;
        row[5] = 4;

        row[6] = 5;
        row[7] = 6;
        row[8] = 7;

        rows[5] = row;

        ///
        row = new int[9];

        row[0] = 3;
        row[1] = 4;
        row[2] = 5;

        row[3] = 6;
        row[4] = 7;
        row[5] = 8;

        row[6] = 9;
        row[7] = 1;
        row[8] = 2;

        rows[6] = row;

        row = new int[9];

        row[0] = 6;
        row[1] = 7;
        row[2] = 8;

        row[3] = 9;
        row[4] = 1;
        row[5] = 2;

        row[6] = 3;
        row[7] = 4;
        row[8] = 5;

        rows[7] = row;

        row = new int[9];

        row[0] = 9;
        row[1] = 1;
        row[2] = 2;

        row[3] = 3;
        row[4] = 4;
        row[5] = 5;

        row[6] = 6;
        row[7] = 7;
        row[8] = 8;

        rows[8] = row;

        grid.setRows(rows);

        return grid;

    }

    public static SudokuGrid makeAlmostSolvedGridMissingTwoBoxes() {

        SudokuGrid grid = new SudokuGrid();

        int[] row;
        int[][] rows;

        row = new int[9];
        rows = new int[9][9];

        row[0] = 0;
        row[1] = 0;
        row[2] = 0;

        row[3] = 4;
        row[4] = 5;
        row[5] = 6;

        row[6] = 7;
        row[7] = 8;
        row[8] = 9;

        rows[0] = row;

        row = new int[9];

        row[0] = 0;
        row[1] = 0;
        row[2] = 0;

        row[3] = 7;
        row[4] = 8;
        row[5] = 9;

        row[6] = 1;
        row[7] = 2;
        row[8] = 3;

        rows[1] = row;

        row = new int[9];

        row[0] = 0;
        row[1] = 0;
        row[2] = 0;

        row[3] = 1;
        row[4] = 2;
        row[5] = 3;

        row[6] = 4;
        row[7] = 5;
        row[8] = 6;

        rows[2] = row;

        ///
        row = new int[9];

        row[0] = 2;
        row[1] = 3;
        row[2] = 4;

        row[3] = 5;
        row[4] = 6;
        row[5] = 7;

        row[6] = 8;
        row[7] = 9;
        row[8] = 1;

        rows[3] = row;

        row = new int[9];

        row[0] = 5;
        row[1] = 6;
        row[2] = 7;

        row[3] = 8;
        row[4] = 9;
        row[5] = 1;

        row[6] = 2;
        row[7] = 3;
        row[8] = 4;

        rows[4] = row;

        row = new int[9];

        row[0] = 8;
        row[1] = 9;
        row[2] = 1;

        row[3] = 2;
        row[4] = 3;
        row[5] = 4;

        row[6] = 5;
        row[7] = 6;
        row[8] = 7;

        rows[5] = row;

        ///
        row = new int[9];

        row[0] = 3;
        row[1] = 4;
        row[2] = 5;

        row[3] = 6;
        row[4] = 7;
        row[5] = 8;

        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[6] = row;

        row = new int[9];

        row[0] = 6;
        row[1] = 7;
        row[2] = 8;

        row[3] = 9;
        row[4] = 1;
        row[5] = 2;

        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[7] = row;

        row = new int[9];

        row[0] = 9;
        row[1] = 1;
        row[2] = 2;

        row[3] = 3;
        row[4] = 4;
        row[5] = 5;

        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[8] = row;

        grid.setRows(rows);

        return grid;

    }

    public static SudokuGrid makeGridFromPCGame() {

        SudokuGrid grid = new SudokuGrid();

        int[][] rows = new int[9][];

        int[] row = new int[9];

        row[0] = 8;
        row[1] = 9;
        row[2] = 0;
        row[3] = 5;
        row[4] = 4;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 0;

        rows[0] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 6;
        row[3] = 9;
        row[4] = 2;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 8;

        rows[1] = row;

        row = new int[9];
        row[0] = 2;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 0;
        row[5] = 6;
        row[6] = 1;
        row[7] = 9;
        row[8] = 0;

        rows[2] = row;

        row = new int[9];
        row[0] = 3;
        row[1] = 0;
        row[2] = 7;
        row[3] = 0;
        row[4] = 0;
        row[5] = 8;
        row[6] = 0;
        row[7] = 1;
        row[8] = 0;

        rows[3] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 5;
        row[2] = 0;
        row[3] = 7;
        row[4] = 0;
        row[5] = 2;
        row[6] = 0;
        row[7] = 8;
        row[8] = 0;

        rows[4] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 8;
        row[2] = 0;
        row[3] = 1;
        row[4] = 0;
        row[5] = 0;
        row[6] = 7;
        row[7] = 0;
        row[8] = 4;

        rows[5] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 7;
        row[2] = 2;
        row[3] = 3;
        row[4] = 0;
        row[5] = 0;
        row[6] = 0;
        row[7] = 0;
        row[8] = 6;

        rows[6] = row;

        row = new int[9];
        row[0] = 4;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 8;
        row[5] = 5;
        row[6] = 9;
        row[7] = 0;
        row[8] = 0;

        rows[7] = row;

        row = new int[9];
        row[0] = 0;
        row[1] = 0;
        row[2] = 0;
        row[3] = 0;
        row[4] = 7;
        row[5] = 4;
        row[6] = 0;
        row[7] = 3;
        row[8] = 2;

        rows[8] = row;

        grid.setRows(rows);

        return grid;
    }

    public static SudokuGrid makeMediumGridFromPcGame() {
        SudokuGrid grid = new SudokuGrid();
        int[] squares = {0, 0, 3, 1, 0, 0, 9, 7, 0, 0, 1, 0, 4, 0, 0, 0, 0, 8
                , 0, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 5, 0, 0, 4, 0, 0, 0, 0,
                1, 0, 0, 0, 8, 0, 0, 9, 0, 0, 7, 0, 0, 5, 0, 0, 0, 0, 0, 0, 1
                , 0, 0, 0, 0, 0, 5, 0, 8, 0, 0, 0, 0, 3, 0, 0, 4, 9, 0, 0, 6,
                2, 0};
        grid.setSquares(squares);
        return grid;
    }

    public static SudokuGrid makeHardGridFromPcGame() {
        SudokuGrid grid = new SudokuGrid();
        int[] squares = {0, 9, 0, 0, 0, 0, 6, 0, 0, 2, 0, 8, 7, 0, 0, 0, 3, 1
                , 5, 0, 0, 0, 0, 4, 0, 2, 0, 0, 0, 0, 0, 0, 0, 8, 0, 7, 0, 0,
                0, 1, 0, 2, 0, 0, 0, 9, 0, 3, 0, 0, 0, 0, 0, 0, 0, 8, 0, 2, 0
                , 0, 0, 0, 3, 4, 3, 0, 0, 0, 8, 5, 0, 6, 0, 0, 6, 0, 0, 0, 0,
                8, 0};
        grid.setSquares(squares);
        return grid;
    }

    public static SudokuGrid makeARandomGrid(int emptySquares) {
        int[] solvedSquares = generateSolvedGridWithRandomValues();
        int[] unsolvedSquares = removeSomeValues(solvedSquares, emptySquares);
        return new SudokuGrid(unsolvedSquares);
    }

    private static int[] removeSomeValues(int[] solvedSquares, int howMany) {
        for (int i = 0; i < howMany; i++) {
            boolean removedOne = false;
            while (!removedOne) {
                int r = new Random().nextInt(1, 81);
                if (!(solvedSquares[r] == 0)) {
                    solvedSquares[r] = 0;
                    removedOne = true;
                }
            }
        }
        return solvedSquares;
    }

    private static int[] generateSolvedGridWithRandomValues() {
        int[] squares = new int[81];
        int backtrack = 0;
        int passes = 0;
        for (int q = 0; q < squares.length; q++) {
            passes++;
            int r = new Random().nextInt(1, 10);
            squares[q] = r;
            boolean solvable = isSolvable(squares);
            while (!solvable) {
                squares[q] = 0;
                int[] couldBes = whatCouldBeHere(new SudokuGrid(squares), q);
                for (int pos = 0; pos <= couldBes.length; pos++) {
                    if (pos == couldBes.length) {
                        if (q < backtrack) {
                            backtrack = 0;
                        }
                        q = eraseValues(squares, q, backtrack++);
                        break;
                    }
                    squares[q] = couldBes[pos];
                    solvable = isSolvable(squares);
                    if (solvable) {
                        break;
                    }
                }
            }
            //print(String.valueOf(q));
        }
        print("This grid was created in " + passes + " passes");
        if (checkSetForNumber(0, squares)) {
            throw new RuntimeException("generated grid has zeros");
        }
        if (!checkGridSolved(new SudokuGrid(squares))) {
            throw new RuntimeException("this grid is not solved");
        }
        return squares;
    }

    private static int eraseValues(int[] squares, int q, int i) {
        for (int j = 0; j < i; j++) {
            squares[q - j] = 0;
        }
        return q - i;
    }

    private static boolean isSolvable(int[] squares) {
        SudokuGrid grid = new SudokuGrid(squares);
        //print(grid.toString());
        return !checkGridForErrors(grid);
    }

}
