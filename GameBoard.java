package connect4;

import java.util.Scanner;

/**
 *
 * @author David Rosas
 */
public class GameBoard {

    private int rows = 6;
    private int columns = 7;
    private boolean gameOver = false;
    private Piece[][] mainBoard = new Piece[rows][columns];

    //get and set methods
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public boolean getGameState() {
        return this.gameOver;
    }

    public void setGameState(boolean state) {
        this.gameOver = state;
    }

    //GameBoard constructor sets all values of the array to null
    public GameBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mainBoard[i][j] = null;
            }
        }
    }

    //check if the spot on the selected column is null
    //creates new piece and sets piece color
    public void placePiece(int column, String color) {
        int col = column - 1;

        //since we already check for a full column in playerTurn()
        //we might be able to remove the if statement
        if (mainBoard[0][col] == null) {
            for (int i = rows - 1; i >= 0; i--) {
                if (mainBoard[i][col] == null) {
                    mainBoard[i][col] = new Piece();
                    mainBoard[i][col].setColor(color);
                    break;
                }
            }
        }
    }

    //iterates through both dimensions of the array
    //if the value is null then we print _ , otherwise we read the color
    public void getBoardState() {
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < columns; j++) {
                if (mainBoard[i][j] == null) {
                    System.out.print("_");
                } else {
                    System.out.print(mainBoard[i][j].getColor());
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }

    //player enter's column for their piece here. we check if the column is full
    //if the column is full then we ask for a different column. repeat until
    //we find a suitable column to play to. 
    public void playerTurn(String playerName, String playerColor) {
        Scanner input = new Scanner(System.in);

        System.out.printf("%s's turn.\n", playerName);
        System.out.print("Enter a column: ");
        int col = input.nextInt();
        boolean tryAgain = isColumnFull(col);

        while (!tryAgain) {
            System.out.printf("Column full. Enter a new column; ");
            col = input.nextInt();
            tryAgain = isColumnFull(col);
        }

        placePiece(col, playerColor);

    }

    //check if a column is full
    public boolean isColumnFull(int inCol) {
        return mainBoard[0][(inCol - 1)] == null;
    }

    //return the current gamestate to see if the game is over
    public boolean isGameOver() {
        return getGameState();
    }

    public boolean checkForWin(String color) {
        // Check horizontally
        for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns - 3; j++) {
        if (mainBoard[i][j] != null &&
        mainBoard[i][j].getColor().equals(color) &&
        mainBoard[i][j + 1] != null &&
        mainBoard[i][j + 1].getColor().equals(color) &&
        mainBoard[i][j + 2] != null &&
        mainBoard[i][j + 2].getColor().equals(color) &&
        mainBoard[i][j + 3] != null &&
        mainBoard[i][j + 3].getColor().equals(color)) {
        return true;
        }
        }
        }

        // Check vertically
        for (int j = 0; j < columns; j++) {
        for (int i = 0; i < rows - 3; i++) {
        if (mainBoard[i][j] != null &&
        mainBoard[i][j].getColor().equals(color) &&
        mainBoard[i + 1][j] != null &&
        mainBoard[i + 1][j].getColor().equals(color) &&
        mainBoard[i + 2][j] != null &&
        mainBoard[i + 2][j].getColor().equals(color) &&
        mainBoard[i + 3][j] != null &&
        mainBoard[i + 3][j].getColor().equals(color)) {
        return true;
        }
        }
        }

        // Check diagonally (from bottom-left to top-right)
        for (int i = 3; i < rows; i++) {
        for (int j = 0; j < columns - 3; j++) {
        if (mainBoard[i][j] != null &&
        mainBoard[i][j].getColor().equals(color) &&
        mainBoard[i - 1][j + 1] != null &&
        mainBoard[i - 1][j + 1].getColor().equals(color) &&
        mainBoard[i - 2][j + 2] != null &&
        mainBoard[i - 2][j + 2].getColor().equals(color) &&
        mainBoard[i - 3][j + 3] != null &&
        mainBoard[i - 3][j + 3].getColor().equals(color)) {
        return true;
        }
        }
        }

        // Check diagonally (from top-left to bottom-right)
        for (int i = 0; i < rows - 3; i++) {
        for (int j = 0; j < columns - 3; j++) {
        if (mainBoard[i][j] != null &&
        mainBoard[i][j].getColor().equals(color) &&
        mainBoard[i + 1][j + 1] != null &&
        mainBoard[i + 1][j + 1].getColor().equals(color) &&
        mainBoard[i + 2][j + 2] != null &&
        mainBoard[i + 2][j + 2].getColor().equals(color) &&
        mainBoard[i + 3][j + 3] != null &&
        mainBoard[i + 3][j + 3].getColor().equals(color)) {
        return true;
        }
        }
        }

        return false; // No win found
        }

    public boolean isBoardFull() {
        // need logic to check for win
        return false;
    }

}
