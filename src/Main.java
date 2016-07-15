import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        // 0 means unassigned cells
        //The Following is considered as one of the most difficult sudoku puzzle to solve :D
        int[][] grid = new int[][] {
                          {0, 0, 0, 0, 0, 0, 0, 0, 0},
                          {0, 0, 0, 0, 0, 0, 0, 0, 0},
                          {0, 0, 0, 0, 0, 0, 0, 0, 0},
                          {0, 0, 0, 0, 0, 0, 0, 0, 0},
                          {0, 0, 0, 0, 0, 0, 0, 0, 0},
                          {0, 0, 0, 0, 0, 0, 0, 0, 0},
                          {0, 0, 0, 0, 0, 0, 0, 0, 0},
                          {0, 0, 0, 0, 0, 0, 0, 0, 0},
                          {0, 0, 0, 0, 0, 0, 0, 0, 0}};
                          
        Sudoku s = new Sudoku();
        s.createSudoku(grid);
        //s.printGrid(grid);
        /*
        if( s.solveSudoku(grid) )
        {
            System.out.println("The Solution to the given Sudoku puzzle is : ");
            s.printGrid(grid);
        }
        else
            System.out.println("The given Sudoku puzzle can't be solved.");
    	}
    	*/
        s.solveSudoku(grid);
        int[][] userGrid = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}};
            
        userGrid = s.initializeGrid(userGrid,grid);
        //s.printGrid(userGrid);
        System.out.println("Set the difficulty: ");
        System.out.println("1.Easy\n2.Medium\n3.Hard\n");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        /*
         * Generate Game for the user to solve. According to difficulty.
         */
        s.generateGame(choice, userGrid);
        /*
         *  If user gives up then show the solution. Implement this feature below.
         */
        System.out.println();
        s.printGrid(grid);
}}