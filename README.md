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
* Core Java 1.6 or below. I'm using lots of for loops and if statements. No streams. I may revise this later, or refactor the application to use Java 1.8+ conventions. I wanted to get the application to work with the simplest conventions I could before applying methods that made it easier for me.

## Tools
* 4 GB PC running Fedora Linux 38
* IntelliJ Community Edition 2023.2.4
* Gnome Sudoku 45.2 

## Discoveries
* Now that the application will solve an easy puzzle and a medium puzzle, I can see that there are some algorithms required to solve a medium puzzle that are not required to solve an easy puzzle.
* The extra algorithms required to solve the medium puzzle are not practical to try without the practice of recording the possible values for every square in a box before identifying what the final value can then by process of elimination be seen to be. 
* The medium puzzle can be solved with the two simplest algorithms after applying the most difficult algorithms, as needed, once or twice. Which, upon reflection, makes sense. The puzzle can not become more difficult to solve after the most difficult problem in the puzzle has been solved. 
