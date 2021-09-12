# sudoku solver logic

## identify
* look at the board
* recognize cells
* recognize rows
* recognize columns
* recognize squares (groups of 9 cells arranged in a square)

## evaluate
* see which cells are empty
* see which cells have values
* see that the value in each cell is a number between 1 and 9
* see that the value in each cell appears only once in its row, column, and square

## solve
* apply an algorithm to decide which value to add to which cell
* check to see if every cell has a permissible value
* check to see if the board is full
* continue solving or stop

### algorithms
#### add the last value
* find a row, column, or square with 8 values
* add the value has not already been added
#### triangulate
* find any empty cell
* look at all the values in the row, column, and square
* find the numbers that have not already been added to the row, column, and square
* if there is only one number, add that number to the cell


