// TicTacToe
// UC6 places a player's symbol on the board at the given position.
// This use case focuses on updating game state.

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
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    // UC3 - Scanner for user input
    static Scanner scanner = new Scanner(System.in);

    /**
     * Entry point of the program. Places a sample move
     * and prints the updated cell value.
     */
    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        tossAndAssignSymbols();
        displayTossResult();

        int slot = getUserSlot();
        int row = getRowFromSlot(slot);
        int col = getColFromSlot(slot);

        System.out.println("Slot entered: " + slot);
        System.out.println("Row: " + row);
        System.out.println("Column: " + col);

        if (isValidMove(row, col)) {
            placeMove(row, col, humanSymbol);
            System.out.println("\nBoard after your move:");
            printBoard();
        } else {
            System.out.println("Invalid move!");
        }
    }

    /**
     * Initializes the 3x3 board by filling each cell with '-' to indicate
     * an empty position.
     */
    static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    /**
     * Prints the Tic-Tac-Toe board using horizontal and vertical separators
     * so that the grid structure is clearly visible to the user.
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
     * Uses random logic to decide the first player and assigns symbols
     * based on the toss outcome.
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
     * Displays the toss result, indicating who plays first and which
     * symbol is assigned to each player.
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
    }

    /**
     * Reads an integer slot value from the user.
     * Input: Scanner object
     * Output: Slot number (1-9)
     */
    static int getUserSlot() {
        System.out.print("\nEnter slot number (1-9): ");
        int slot = scanner.nextInt();
        return slot;
    }

    /**
     * Converts slot number into row index using zero-based indexing.
     * Input: Slot number (1-9)
     * Output: Row index (0-2)
     */
    static int getRowFromSlot(int slot) {
        return (slot - 1) / 3;
    }

    /**
     * Converts slot number into column index using modulo operation.
     * Input: Slot number (1-9)
     * Output: Column index (0-2)
     */
    static int getColFromSlot(int slot) {
        return (slot - 1) % 3;
    }

    /**
     * Checks if the given row and column are within bounds
     * and if the target cell is empty.
     * Input: Row, Column
     * Output: true if valid, false otherwise.
     */
    static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2) {
            return false;
        }
        if (col < 0 || col > 2) {
            return false;
        }
        if (board[row][col] != '-') {
            return false;
        }
        return true;
    }

    /**
     * Updates the board by placing the given symbol at
     * the specified row and column.
     * Input: Row, Column, Symbol
     * Hint: Assume the move is already validated.
     */
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }
}