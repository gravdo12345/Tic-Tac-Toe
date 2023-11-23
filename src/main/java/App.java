import java.util.Scanner;

public class TicTacToeGame {

    private static final int SIZE = 3;
    private static final char EMPTY_CELL = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private char[][] board;

    public TicTacToeGame() {
        this.board = initializeBoard();
    }

    private char[][] initializeBoard() {
        return new char[][]{{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
    }

    private void displayBoard() {
        for (char[] row : board) {
            System.out.println(" " + row[0] + " | " + row[1] + " | " + row[2] + " ");
            System.out.println("-----------");
        }
        System.out.println();
    }

    private void resetBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != PLAYER_X && board[i][j] != PLAYER_O) {
                    board[i][j] = EMPTY_CELL;
                }
            }
        }
    }

    private void playerMove() {
        int input;
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your move (1-9): ");
            input = scan.nextInt();

            if (input > 0 && input <= SIZE * SIZE) {
                int row = (input - 1) / SIZE;
                int col = (input - 1) % SIZE;

                if (board[row][col] == EMPTY_CELL) {
                    board[row][col] = PLAYER_X;
                    break;
                } else {
                    System.out.println("That slot is already in use. Enter another.");
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    private void computerMove() {
        int row, col;

        while (true) {
            row = (int) (Math.random() * SIZE);
            col = (int) (Math.random() * SIZE);

            if (board[row][col] == EMPTY_CELL) {
                board[row][col] = PLAYER_O;
                break;
            }
        }
    }

    private boolean checkForWin(char player) {
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    private void announceResult(boolean gameWon) {
        displayBoard();
        if (gameWon) {
            System.out.println("You won the game!\nThanks for playing!");
        } else {
            System.out.println("It's a draw!\nThanks for playing!");
        }
    }

    public void playGame() {
        boolean gameWon = false;
        boolean boxEmpty = true;

        System.out.println("Welcome to Tic Tac Toe!\n");

        while (!gameWon && boxEmpty) {
            displayBoard();

            if (boxEmpty) {
                resetBoard();
                boxEmpty = false;
            }

            playerMove();
            gameWon = checkForWin(PLAYER_X);

            if (!gameWon) {
                computerMove();
                gameWon = checkForWin(PLAYER_O);
            }

            boxEmpty = !isBoardFull() && !gameWon;
        }

        announceResult(gameWon);
    }

    public static void main(String[] args) {
        TicTacToeGame ticTacToeGame = new TicTacToeGame();
        ticTacToeGame.playGame();
    }
}
