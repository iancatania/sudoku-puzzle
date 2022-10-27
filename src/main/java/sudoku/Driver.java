package sudoku;
import java.util.Scanner;
/**
 * Driver.java - 
 * Instantiates and runs an instance of SudokuPuzzle for the user using a while loop.
 * Interfaces with user input for the game.
 *
 * @author Ian Catania
 * @version 2/2/20
 */
public class Driver {

    /**
     * Runs the while loop that allows for multiple inputs on the board. Creates
     * the inputs for the user to use various methods.
     *
     * @param args
     */
    public static void main(String[] args) {
        SudokuPuzzle sp = new SudokuPuzzle(); //Instantiates puzzle object
        Scanner sc = new Scanner(System.in); //Scanner for the user input

        sp.initializePuzzle(); //Adds the hard-coded values to the board

        String ans = "";
        while (!ans.equalsIgnoreCase("Q")) { //While loop breaks when Q input to quit
            System.out.println(sp.toString()); //Initialized board printed
            System.out.println("\nWhat would you like to do?");
            System.out.println("\n| (C) Clear the Board | (S) Set a Boxes' Number |");
            System.out.println("\n| (G) Get Possible Values | (Q) Quit the Game |\n");
            ans = sc.next(); //Reads next user input
            if (ans.equalsIgnoreCase("C")) { //Clear resets the board to it's original values
                sp.reset();
            }
            if (ans.equalsIgnoreCase("S")) { //Set allows the user to put a number in a box.
                System.out.println("What row is the box?");
                int row = sc.nextInt() - 1;
                System.out.println("What column is the box?");
                int col = sc.nextInt() - 1;
                System.out.println("What value will you set?");
                int value = sc.nextInt();
                sp.addGuess(row, col, value);
            }
            if (ans.equalsIgnoreCase("G")) { //Get shows the possible values of the specific box.
                String allowValue = "\nAllowed Values: ";
                System.out.println("What row would you like to check?");
                int row = sc.nextInt() - 1;
                System.out.println("What column would you like to check?");
                int col = sc.nextInt() - 1;
                boolean[] allowed = sp.getAllowedValues(row, col);
                     for (int i = 1; i < 10; i++)
                         if (allowed[i] == true) {
                             allowValue += i + ", ";
                         }
                allowValue += "\n";
                System.out.println(allowValue);
            }
            if (ans.equalsIgnoreCase("Q")) { //Quits the game.
                System.out.println("Thanks for playing!");
            }
            if (sp.isFull() && sp.checkPuzzle()) { //Checks for win condition, and allows user to replay.
                System.out.println("Congratulations! You won! Would you like to play again? y/n");
                ans = sc.next();
                if (ans.equalsIgnoreCase("N")) {
                    ans = "Q";
                }
                if (ans.equalsIgnoreCase("Y")) {
                    sp.reset();
                }
            }
            
        }
    }
}
