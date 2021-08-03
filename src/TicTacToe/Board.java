package TicTacToe;

public class Board
{
    // The length is how long the side of the tic tac toe board is
    static final int length = 3;
    // How the board will be initialized
    static final char empty = '_';

    private char[][] grid;

    public Board()
    {
        grid = new char[length][length];
        for (int i = 0; i < length; i++) {
            for (int k = 0; k < length; k++) {

                // The "empty" space of the tic tac toe board will be the defaultChar and this Player is null to avoid conflicts with other Players
                this.grid[i][k] = empty;
            }
        }
    }

    public char getEmptyChar() {
        return empty;
    }

    // How the player will edit the tic tac toe board
    public boolean move(int row, int column, char player, boolean informError) {

        // If user enters an invalid spot on the grid
        if (row >= length || column >= length || row < 0 || column < 0) {
            if (informError) System.err.println("Invalid Position on the grid.");
            // Note: true means the operation was a failure, false means it worked
            // It was made this way for the while statement in Main.java
            return true;

            // Check if the spot on the grid is already taken by another player (!= works here since we want to see if the spot points to the same "Empty" Player)
        } else if (grid[row][column] != empty) {
            if (informError) System.err.println("This position is already chosen by a player.");
            return true;
        }
        grid[row][column] = player;
        return false;
    }

    public void printBoard() {
        for (int row = 0; row < length; row++) {
            for (int column = 0; column < length; column++) {
                System.out.print(this.grid[row][column] + " ");
            }
            System.out.println();
        }
    }

    public char status() {
        for (int i = 0; i < length; i++) {

            // Checking all rows
            if (grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2]) {
                if (grid[i][0] != empty)
                    return grid[i][0];
                // Checking all columns
            }
            if (grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i]) {
                if (grid[0][i] != empty)
                    return grid[0][i];
            }
            // Checking all diagonals
            if (grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]) {
                if (grid[0][0] != empty)
                    return grid[0][0];
            }
            if (grid[2][0] == grid[1][1] && grid[2][0] == grid[0][2]) {
                if (grid[2][0] != empty)
                    return grid[2][0];
            }

        }
        return empty;
    }
}