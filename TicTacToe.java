import java.util.*;

public class TicTacToe {
    char[][] board = new char[3][3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char playAgain;

        System.out.println("WELCOME TO The TIC-TAC-TOE!");
        System.out.println("Player X and Player O take turns to play.\n");

        do {
            TicTacToe game = new TicTacToe();
            game.initializeBoard();
            char currentPlayer = 'X';
            boolean gameOver = false;

            System.out.println("Board positions guide:");
            game.printBoardPositions(); // Show the numbers at start

            while (!gameOver) {
                game.printBoard();
                System.out.println("Player " + currentPlayer + ", choose a position (1-9): ");
                int pos = sc.nextInt();

                if (pos < 1 || pos > 9) {
                    System.out.println("Invalid input! Enter a number between 1 and 9.");
                    continue;
                }

                int row = (pos - 1) / 3;
                int col = (pos - 1) % 3;

                if (game.board[row][col] == ' ') {
                    game.board[row][col] = currentPlayer;

                    if (game.checkWinner(currentPlayer)) {
                        game.printBoard();
                        System.out.println(" Player " + currentPlayer + " wins!");
                        gameOver = true;
                    } else if (game.isBoardFull()) {
                        game.printBoard();
                        System.out.println("Oops! It's a Draw!");
                        gameOver = true;
                    } else {
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    }
                } else {
                    System.out.println("That position is already occupied. Try again.");
                }
            }

            System.out.println("Do you want to play again? (Y/N): ");
            playAgain = sc.next().charAt(0);

        } while (playAgain == 'Y' || playAgain == 'y');

        System.out.println("Thanks for playing! Goodbye!");
    }

    public void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    // New method to print board positions 1-9
    public void printBoardPositions() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print((i * 3 + j + 1) + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
        System.out.println();
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
        System.out.println();
    }

    public boolean checkWinner(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player))
                return true;
        }
        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player))
            return true;

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }
}