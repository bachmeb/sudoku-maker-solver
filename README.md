# sudoku-maker-solver

## Summary
This point of this project is for me to see if I can:
* solve a sudoku puzzle
* identify the logic required to solve a sudoku puzzle
* write a program to solve a sudoku puzzle

## Constraints
* No external help. I haven't read anything else that explains how to solve a sudoku puzzle
* No imported libraries (except for unit testing). I haven't imported any classes that would make building or transforming a matrix any easier. 
* Primitive data types and simple data structures. Where possible and practical I'm using integers and arrays and arrays of arrays. That's it. Not even the Java Collections API.
* Core Java 1.6 or below. I'm using lots of for loops and if statements. No streams. I may revise this later, or refactor the application to use Java 1.8+ conventions. I wanted to get the application to work with the simplest conventions I could before applying methods that made my work easier.
* Only algorithms a person can use without a computer. I got the idea to write a Java program to solve a sudoku puzzle after seeing someone demonstrate a solution to any sudoku puzzle using a 15-line recursive algorithm. I wanted to see, by comparison, how much more work does a person have to do to solve a sudoku puzzle without a computer. And how much more code would I have to write in an application that uses the same algorithms a person does when solving a puzzle without a computer.

## Tools
* 4 GB PC running Fedora Linux 38
* IntelliJ Community Edition 2023.2.4
* Gnome Sudoku 45.2 

## Discoveries
* Now that the application will solve an easy puzzle and a medium puzzle, I can see that there are some algorithms required to solve a medium puzzle that are not required to solve an easy puzzle.
* The extra algorithm required to solve the medium puzzle cannot be applied without recording the possible values for every square in a given box before identifying what the final value is. This practice can be applied with pencil and paper by writing possible values in the corner of each square. I think this algorithm would be exceedingly difficult to try without some way of recording possible values for review later. This is the algorithm I think many people are not going to be able to apply just by looking at the puzzle and thinking about it; which in the case of a puzzle which cannot be solved using only the simplest algorithms will make the puzzle seem unsolvable. 
* The medium puzzle can be solved with the two simplest algorithms after applying the most difficult algorithms, as needed, once or twice. Which, upon reflection, makes sense. The puzzle cannot become more difficult to solve after the most difficult problems in the puzzle have been solved. 

### Some grids can't be solved
or, some grids have more than one solution. For example, this grid can't be solved by any player, because there is more than one way to solve the grid and there isn't enough information in the grid to decide which way to solve it. 
```
-------------------------------------
|  6  1  2  |  8  7  4  |  3  5  9  |  
|  3  5  4  |     9     |  6  7  8  |  
|  7  8  9  |  3  5  6  |  2  1  4  |  
-------------------------------------
|  1  2  8  |  5  4  7  |  9  3  6  |  
|  9  3  5  |     6     |  4  8  7  |  
|  4  6  7  |  9  3  8  |  5  2  1  |  
-------------------------------------
|  2     1  |  6  8  5  |  7     3  |  
|  5  7  3  |  4  1  9  |  8  6  2  |  
|  8     6  |  7  2  3  |  1     5  |  
-------------------------------------
```
There are two methods of solving a grid I did not want to use: backtracking and guessing. This grid seems to show that some grids which can be solved cannot be solved without guessing and perhaps backtracking.
