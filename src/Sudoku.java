public class Sudoku {
	 final int N = 9;
	    
	    /*The following function checks if the specified number is present in any other column at the same row position*/ 
	    public boolean UsedInRow(int grid[][], int row, int num)    
	    {
	        for(int col = 0; col<N; col++)
	            if( grid[row][col]==num )
	                return true;
	        return false;
	    }
	    
	    /*The following function checks if the specified number is present in any other row at the same column position*/ 
	    public boolean UsedInCol(int grid[][], int col, int num)    
	    {
	        for(int row = 0; row<N; row++)
	            if( grid[row][col]==num )
	                return true;
	        return false;
	    }
	    
	    /*Checks the current box for the presence of num in it*/
	    public boolean UsedInBox(int grid[][], int RowStart, int ColStart, int num)
	    {
	        for(int row = 0; row<3; row++)
	            for(int col = 0; col<3; col++)
	                if( grid[row+RowStart][col+ColStart]==num )
	                    return true;
	        return false;
	    }
	    
	    /*Returns a boolean value which indicates whether it will be legal to assign num to the given (row, col) location*/
	    public boolean isSafe(int grid[][], int row, int col, int num)
	    {
	        int RowStart = row - row%3; //If row = 8, RowStart = 8 - 8%3 = 6(which is the starting row index of the 3rd box)
	        int ColStart = col - col%3;
	        
	        if( UsedInRow(grid, row, num) || UsedInCol(grid, col, num) || UsedInBox(grid, RowStart, ColStart, num))
	            return false;
	        
	        return true;
	    }
	    
	    public boolean solveSudoku(int grid[][])
	    {
	        int row = 0, col = 0, flag = 0;
	        
	        for(row=0; row<N; row++)  //The following nested loop checks for the first empty box in the puzzle.
	        {
	            for(col=0; col<N; col++)
	            {
	                if( grid[row][col] == 0 ) 
	                {
	                    flag = 1;
	                    break;
	                }
	            }
	            if( flag==1 )
	                break;
	        }
	        
	        if( flag==0 )   //Base case for recursion( Returns true when there is no empty position left )
	            return true;
	            
	        for(int num = 1; num<=9; num++)
	        {
	            if( isSafe(grid, row, col, num) )   //Checks if the current number can be placed at the present location.
	            {
	                grid[row][col] = num;   //Assigns the value num to the current position.
	                
	                if( solveSudoku(grid) ) //Recurively calling itself to fill in the rest of the boxes.
	                    return true;        //If the puzzle is solvable after putting "num" at the current postion then it returns true to sieze the function from going any further.
	                    
	                grid[row][col] = 0;     //It means that the puzzle was not solvable with current value at (row, col) as "num".
	            }
	        }
	        
	        return false;       //Returns false if the puzzle isn't solvable.
	    }
	    
	    /*The following function prints the solved Sudoku puzzle*/
	    void printGrid(int grid[][])
	    {
	    	int countV = 0;
	    	int countH = 0;
	    	
	        for(int i=0; i<N; i++)
	        {
	        	countV = 0;
	            for(int j=0; j<N; j++)
	            { 
	            	System.out.print(grid[i][j]+" ");
	            	countV++;
	            	if(countV%3 == 0)
	            		System.out.print("|");
	            }   
	            System.out.println();
	            countH++;
	            if(countH%3 == 0)
	            {	
	            	System.out.print("---------------------\n");
	            }
	        }
	    }
	    
	    void createSudoku(int grid[][])
	    {
	        for(int i=0; i<9; i++)
	        {
	            while( true )
	            {
	                int a, b;
	                a = (int)(Math.random() * 9);
	                b = (int)(Math.random() * 9);
	                if( isSafe(grid, i, a, b) )
	                {
	                    grid[i][a] = b;
	                    break;
	                }
	            }
	        }
	    }
	    public int[][] initializeGrid(int userGrid[][],int grid[][])
		{
			for(int i=0; i<9; i++)
			{
				for(int j=0; j<9; j++)
					userGrid[i][j] = grid[i][j];
			}
			return userGrid;
		}
	    
	    void generateGame(int difficulty, int userGrid [][])
	    {
	    	//When user has chosen Easy difficulty
	    	if(difficulty == 1)
	    	{
	    		for(int i=0; i<40; i++)
	    		{
	    			int a=0,b=0;
	    			do
	    			{
	    				a = (int)(Math.random() * 9);
	    				b = (int)(Math.random() * 9);
	    				if(userGrid[a][b] !=0)
	    				{
	    					userGrid[a][b] = 0;
	    					break;
	    				}
	    				else
	    					continue;
	    			}while(true);
	    		}
	    	}
	    	//When user has chosen medium difficulty
	    	else if(difficulty == 2)
	    	{
	    		for(int i=0; i<45; i++)
	    		{
	    			int a=0,b=0;
	    			do
	    			{
	    				a = (int)(Math.random() * 9);
	    				b = (int)(Math.random() * 9);
	    				if(userGrid[a][b] !=0)
	    				{
	    					userGrid[a][b] = 0;
	    					break;
	    				}
	    				else
	    					continue;
	    			}while(true);
	    		}
	    	}
	    	//When user has chosen Hard difficulty
	    	else if(difficulty == 3)
	    	{
	    		for(int i=0; i<50; i++)
	    		{
	    			int a=0,b=0;
	    			do
	    			{
	    				a = (int)(Math.random() * 9);
	    				b = (int)(Math.random() * 9);
	    				if(userGrid[a][b] !=0)
	    				{
	    					userGrid[a][b] = 0;
	    					break;
	    				}
	    				else
	    					continue;
	    			}while(true);
	    		}
	    	}
	    	printGrid(userGrid);
	    }
	    
}
