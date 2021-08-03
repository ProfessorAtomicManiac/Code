package TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Set up the players
        char player = 'X';
        char computer = 'O';

        // Random generator setup
        Random random = new Random(System.currentTimeMillis());
        // Using the computer's time to set a seed to insure the results are more random

        // Set up Board Class via two-dimensional arr
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        int row, col;

        board.printBoard();
        while (true) {
            do {
                System.out.println("Enter which row you chose to play (1st row is '0', 2nd row is '1', etc): ");
                row = scanner.nextInt();
                System.out.println("Enter which column you chose to play (1st column is '0', 2nd column is '1', etc): ");
                col = scanner.nextInt();

                // The boolean argument is whether to display whether the move is legal. We don't want to display
                // such messages when the computer is moving
            } while (board.move(row, col, player, true));

            board.printBoard();
            if (board.status() == player) {
                System.out.println("Player Wins!");
                break;
            }

            System.out.println("Computer's Turn ");
            do {
                row = random.nextInt(3);
                col = random.nextInt(3);
            } while (board.move(row, col, computer, false));
            board.printBoard();
            if (board.status() == computer) {
                System.out.println("Computer Wins!");
                break;
            }

        }
    }

}

