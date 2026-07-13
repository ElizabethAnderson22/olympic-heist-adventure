package org.uob.a1;

public class Score {
    // declaration of required fields
    private int startingScore;
    private int currentScore;
    private int numRoomsVisited;
    private int numPuzzlesSolved;
    private final int PUZZLE_VALUE = 10;

    // constructor method
    public Score (int startingScore)
    {
        this.startingScore = startingScore;
    }

    // method that will be performed when a new room is visited
    public void visitRoom ()
    {
        numRoomsVisited++;
    }

    // method that will be performed when a puzzle is solved
    public void solvePuzzle ()
    {
        numPuzzlesSolved++;
    }

    // method that calculates and returns the current score. The score is calculated as the starting score minus the number of rooms visited plus the number of solved puzzles times the score per puzzle.
    public double getScore()
    {
        return (startingScore - numRoomsVisited + numPuzzlesSolved * PUZZLE_VALUE);
        // check the order of operations here
    }
}