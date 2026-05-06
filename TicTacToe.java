// TicTacToe
// UC8 controls the continuous game loop and alternates
// turns until the game ends.

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    // UC1 variables
    static char[][] board = {
        {'-', '-', '-'},
        {'-', '-', '-'},
        {'-', '-', '-'}
    };

    // UC2 variables
    static boolean isHumanTurn = true;
    static char humanSymbol;
    static char computerSymbol;

    // UC8 variables
    static boolean gameOver = false;

    // UC3 - Scanner for user input
    static Scanner scanner = new Scanner(System.in);

    /**
     * Entry point of the program. Demonstrates the structure
     * of a continuous game loop.
     */
    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        tossAndAssignSymbols();
        displayTossResult();

        // UC8: while loop - keep playing until game is over
        while (!gameOver) {
            if (isHumanTurn) {
                // Human's turn
                int slot = getUserSlot();
                int row = getRowFromSlot(slot);
                int col = getColFromSlot(slot);

                if (isValidMove(row, col)) {
                    placeMove(row, col, humanSymbol);
                    printBoard();

                    if (checkWin(humanSymbol)) {
                        System.out.println("🎉 You win!");
                        gameOver = true;
                    } else if (checkDraw()) {
                        System.out.println("It's a draw!");
                        gameOver = true;
                    } else {
                        isHumanTurn = false; // switch to computer
                    }
                } else {
                    System.out.println("Invalid move! Try again.");
                }

            } else {
                // Computer's turn
                System.out.println("\nComputer is making a move...");
                computerMove();
                printBoard();

                if (checkWin(computerSymbol)) {
                    System.out.println("Computer wins!");
                    gameOver = true;
                } else if (checkDraw()) {
                    System.out.println("It's a draw!");
                    gameOver = true;
                } else {
                    isHumanTurn = true; // switch to human
                }
            }
        }
    }

    /**
     * Initializes the 3x3 board by filling each cell with '-'.
     */
    static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    /**
     * Prints the Tic-Tac-Toe board with separators.
     */
    static void printBoard() {
        System.out.println("----------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("----------");
        }
    }

    /**
     * Uses random logic to decide the first player and assigns symbols.
     */
    static void tossAndAssignSymbols() {
        Random random = new Random();
        int toss = random.nextInt(2);

        if (toss == 0) {
            isHumanTurn = true;
            humanSymbol = 'X';
            computerSymbol = 'O';
        } else {
            isHumanTurn = false;
            humanSymbol = 'O';
            computerSymbol = 'X';
        }
    }

    /**
     * Displays the toss result.
     */
    static void displayTossResult() {
        System.out.println("\n--- Toss Result ---");
        if (isHumanTurn) {
            System.out.println("You won the toss! You go first.");
        } else {
            System.out.println("Computer won the toss! Computer goes first.");
        }
        System.out.println("Your symbol   : " + humanSymbol);
        System.out.println("Computer symbol: " + computerSymbol);
        System.out.println();
    }

    /**
     * Reads slot number from user.
     */
    static int getUserSlot() {
        System.out.print("Enter slot number (1-9): ");
        int slot = scanner.nextInt();
        return slot;
    }

    /**
     * Converts slot to row index.
     */
    static int getRowFromSlot(int slot) {
        return (slot - 1) / 3;
    }

    /**
     * Converts slot to column index.
     */
    static int getColFromSlot(int slot) {
        return (slot - 1) % 3;
    }

    /**
     * Validates the move.
     */
    static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2) return false;
        if (col < 0 || col > 2) return false;
        if (board[row][col] != '-') return false;
        return true;
    }

    /**
     * Places symbol on board.
     */
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    /**
     * Computer picks a random valid slot and places its symbol.
     */
    static void computerMove() {
        Random random = new Random();
        int slot, row, col;
        do {
            slot = random.nextInt(9) + 1;
            row = getRowFromSlot(slot);
            col = getColFromSlot(slot);
        } while (!isValidMove(row, col));

        placeMove(row, col, computerSymbol);
        System.out.println("Computer chose slot: " + slot);
    }

    /**
     * Checks if the given symbol has won.
     */
    static boolean checkWin(char symbol) {
        // Check rows
        for (int r = 0; r < 3; r++) {
            if (board[r][0] == symbol && board[r][1] == symbol && board[r][2] == symbol)
                return true;
        }
        // Check columns
        for (int c = 0; c < 3; c++) {
            if (board[0][c] == symbol && board[1][c] == symbol && board[2][c] == symbol)
                return true;
        }
        // Check diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
            return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)
            return true;

        return false;
    }

    /**
     * Checks if the game is a draw (no empty cells left).
     */
    static boolean checkDraw() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == '-') return false;
            }
        }
        return true;
    }
}