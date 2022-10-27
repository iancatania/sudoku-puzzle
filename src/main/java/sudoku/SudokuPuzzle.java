package sudoku;
/**
 * SudokuPuzzle.java - 
 * Initializes a puzzle object as a 2-Dimensional array of values,
 * with methods to check the game rules.
 *
 * @author Ian Catania
 * @version 2/2/20
 */
public class SudokuPuzzle {

    private int[][] board;
    private boolean[][] orig;

    final int ROW = 9;
    final int COLUMN = 9;

    /**
     * Constructor to instantiate both the int and Boolean 2D arrays.
     */
    public SudokuPuzzle() {
        board = new int[ROW][COLUMN];
        orig = new boolean[ROW][COLUMN];
    }

    /**
     * Prints the Sudoku board for the user to see.
     *
     * @return result - the Sudoku board as a string.
     */
    public String toString() {
        String result = "     1  2  3     4  5  6     7  8  9";
        for (int i = 0; i < ROW; i++) {
            result += "\n";
            if (i == 0 || i == 3 || i == 6) {
                result += "------------------------------------\n";
            }
            for (int j = 0; j < COLUMN; j++) {
                if (j == 0) {
                    result += (i + 1) + "  | " + board[i][j] + "  ";
                } else {
                    result += board[i][j] + "  ";
                }

                if (j == 2 || j == 5) {
                    result += "|  ";
                }
            }
        }
        return result;
    }

    /**
     * Initialize a box with value, sets orig at the location to true.
     *
     * @param row The row number for the box.
     * @param col The column number for the box.
     * @param value The initial value for the box.
     */
    public void addInitial(int row, int col, int value) {
        board[row][col] = value;
        orig[row][col] = true;
    }

    /**
     * Initialize a box with value, sets orig at the location to false.
     *
     * @param row The row of the box.
     * @param col The column of the box.
     * @param value The value of the box, user-inputted.
     */
    public void addGuess(int row, int col, int value) {
        if (board[row][col] == 0) {
            board[row][col] = value;
            orig[row][col] = false;
        } else {
            System.out.println("This box is full!\n");
        }
    }

    /**
     * Returns the value in the specified box.
     *
     * @param row The row of the box.
     * @param col The column of the box.
     * @return board[row][col] - the value of board at the specific index.
     */
    public int getValueIn(int row, int col) {
        return board[row][col];
    }

    /**
     * Returns to the user the list of allowed values for a specific box.
     *
     * @param row The row of the box.
     * @param col The column of the box.
     * @return values - A 1D array checking true or false for each possible value.
     */
    public boolean[] getAllowedValues(int row, int col) {
        int subRow = (row / 3) * 3;
        int subCol = (col / 3) * 3;
        boolean[] values = new boolean[10];
        for (int i = 1; i < 10; i++)
            values[i] = true;
        values[0] = false;        
        for (int i = 0; i < 9; i++) {
            values[board[i][col]] = false;
        }
        for (int j = 0; j < 9; j++) {
            values[board[row][j]] = false;
        }
        for (int i = subRow; i < subRow + 3; i++) {
            for (int j = subCol; j < subCol + 3; j++) {
                values[board[i][j]] = false;
            }
        }        
       return values;
    }

    /**
     * Returns true if all rules are satisfied for the entire board.
     *
     * @return True or false.
     */
    public boolean checkPuzzle() {
        for (int i = 0; i < board.length; i++) {
            if (checkRow(i) == false) {
                return false;
            }
        }

        for (int j = 0; j < board.length; j++) {
            if (checkColumn(j) == false) {
                return false;
            }
        }

        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board.length; j += 3) {
                if (checkSubArray(i, j) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks a row to determine if the puzzle requirements are met in the row.
     * A private helper method. Used in checkPuzzle.
     *
     * @param row The row of the box.
     * @return True or false.
     */
    private boolean checkRow(int row) {
        int check[] = new int[10];
        for (int i = 0; i < 9; i++) {
            check[board[row][i]]++;
        }
        
         for (int j = 1; j < 9; j++) {
              if (check[j] > 1) {
                  return false;
              }
         }
         return true;
    }

    /**
     * Checks a column to determine if the puzzle requirements are met in the
     * column. A private helper method. Used in checkPuzzle.
     *
     * @param col The column of the box.
     * @return True or false.
     */
    private boolean checkColumn(int col) {
        int check[] = new int[10];
        for (int i = 0; i < 9; i++) {
            check[board[i][col]]++;
        }
        
         for (int j = 1; j < 9; j++) {
              if (check[j] > 1) {
                  return false;
              }
         }
         return true;
    }

    /**
     * Runs a nested for loop to determine where each SubArray is, and another
     * to check the row and column of the SubArray. A private helper method.
     * Used in checkPuzzle.
     *
     * @param row The row of the top left box of the SubArray.
     * @param col The column of the top left box of the SubArray.
     * @return True or false.
     */
    private boolean checkSubArray(int row, int col) {
        int subRow = (row / 3) * 3;
        int subCol = (col / 3) * 3;
        int check[] = new int[10];
        for (int i = subRow; i < subRow + 3; i++) {
            for (int j = subCol; j < subCol + 3; j++) {
                check[board[i][j]]++;
            }
        }
        for (int k = 1; k < check.length; k++) {
            if (check[k] > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Assigns hard-coded values to the 2D int array "board", to set up the
     * game.
     */
    public void initializePuzzle() {
        int[][] initial = {{5, 3, 9, 0, 0, 0, 4, 0, 0},
                           {7, 2, 8, 3, 0, 4, 9, 0, 0},
                           {6, 4, 1, 0, 0, 0, 7, 3, 0},
                           {4, 6, 2, 5, 3, 9, 8, 7, 1},
                           {3, 8, 5, 7, 2, 1, 6, 4, 9},
                           {1, 9, 7, 4, 6, 8, 2, 5, 3},
                           {2, 5, 6, 1, 8, 7, 3, 9, 4},
                           {9, 1, 3, 0, 4, 0, 5, 8, 7},
                           {8, 7, 4, 9, 5, 3, 1, 2, 6},};

        for (int i = 0; i < initial.length; i++) {
            for (int j = 0; j < initial.length; j++) {
                addInitial(i, j, initial[i][j]);
            }
        }
    }

    /**
     * Checks to see if all boxes have been filled with an inputted number.
     *
     * @return True or false.
     */
    public boolean isFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * Re-initializes the puzzle, resetting all values to the hard-coded
     * beginning.
     */
    public void reset() {
        initializePuzzle();
    }
}
