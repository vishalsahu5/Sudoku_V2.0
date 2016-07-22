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
	    
	    /*This function counts the number of solution a given puzzle can have.*/
	    int countSolution(int grid[][], int count)
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
	            return count+1;
	            
	        for(int num = 1; num<=9; num++)
	        {
	            if( isSafe(grid, row, col, num) )
	            {
	                grid[row][col] = num;
	                count = countSolution(grid, count);
	            }
	            grid[row][col] = 0;
	        }
	        
	        return count;
	    }
	    
	    /*The following function delete's some random numbers from the sudoku such that the solution of the sudoku remains unique*/
	    void sudokuGenerator(int grid[][], int difficulty)
	    {
	        int num;
	       /*
	        *  If the difficulty of the required puzzle is 1 then we will delete 4 elements from each row.
	        *  If the difficulty of the required puzzle is 2 then we will delete 5 elements from each row.
	        *  If the difficulty of the required puzzle is 3 then we will delete 6 elements from each row.
	        */
	        if( difficulty==1 )                 
	            num = 4;                        
	        else if( difficulty==2 )            
	            num = 5;                        
	        else                                
	            num = 6;                         
	        
	        
	        for(int i=0; i<9; i++)
	        {
	        	/*
	        	 * This loop helps us in removing random elements from each row
	        	 */
	            for(int j=0; j<num; j++)      
	            {
	                while( true )
	                {
	                    int a = (int)(Math.random() * 9);
	                    int b = grid[i][a];
	                    /*
	                     * If the number is not already deleted than we will proceed in this if statement
	                     */
	                    if( b!=0 )           
	                    {
	                        grid[i][a] = 0;
	                       /*
	                        * If the number of ways in which the Sudoku can be solved remains 1 than we break out of the loop.
	                        */
	                       if( countSolution(grid, 0)==1 )     
	                            break;
	                            
	                        grid[i][a] = b;
	                    }
	                }
	            }
	        }
	    }
	    
	    void GUI(int difficulty)
			{
				Hello1 g = new Hello1();
				
				int[][] grid = new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0},
											{0, 0, 0, 0, 0, 0, 0, 0, 0},
											{0, 0, 0, 0, 0, 0, 0, 0, 0},
											{0, 0, 0, 0, 0, 0, 0, 0, 0},
											{0, 0, 0, 0, 0, 0, 0, 0, 0},
											{0, 0, 0, 0, 0, 0, 0, 0, 0},
											{0, 0, 0, 0, 0, 0, 0, 0, 0},
											{0, 0, 0, 0, 0, 0, 0, 0, 0},
											{0, 0, 0, 0, 0, 0, 0, 0, 0}};
											
				int[][] boxCheck = new int[9][9];
				
				g.createSudoku(grid);					//Adds 9 random elements to the grid.
				g.solveSudoku(grid);					//Solves the grid using backtracking created above.
				g.sudokuGenerator(grid, difficulty);	//Removes some elements from the completely solved grid, such that the sudoku always results in an unique solution.
				
				JFrame frame = new JFrame("Sudoku Prototype");	//Creates a new JFrame with the title "Sudoku Prototype".
				JPanel p = new JPanel();						//Creates a JPanel, on which we will add all our components.
				JTextField[] box = new JTextField[81];			//81 JTextField's, one for each element. 
				
				p.setBackground(Color.WHITE);					//Set's the background color to white.
				p.setLayout(null);								//We are not using any predefined Layout.
				
				int row = 1;									//This variable help's us to add a horizontal gap after every 3 lines.
				for(int i=0; i<81; i++)							//This keyListener helps us to restrict the JTextField to numerical values only.
				{
					box[i] = new JTextField();
					box[i].addKeyListener(new KeyAdapter(){
						public void keyTyped(KeyEvent e)
						{
							//if( box[i].getText().length() >= 1 )
								//e.consume();
							
							char ch = e.getKeyChar();
							if( !(ch>='0' && ch<='9') )			//If the value inserted by the user is not an Integer than we will just delete it from the grid.
								e.consume();
						}
					});
					
					boxCheck[i/9][i%9] = 0;
					if( grid[i/9][i%9]!=0 )						//If the value of Grid is not 0 at that position, than we will show that number on the screen.
					{
						boxCheck[i/9][i%9] = 1;					//If we have placed the element in the grid than the value of boxCheck at that position is 1, else it is 0.
						
						box[i].setText(" "+grid[i/9][i%9]);
						box[i].setEditable(false);
						box[i].setForeground(Color.RED);
						box[i].setBackground(Color.WHITE);
						box[i].setFont(new Font("Tahoma", Font.BOLD, 14));
					}
					
					int w = 0, h = 0;											//The next 2 if statements will helps us make 9, 3x3 boxes on the board.
					if( (i+1)%3==0 && i%9!=0 )									//If we the value of the column is divisble by 3, assgin w by 2.		
						w = 2;
					if( row%3==0 )												//If we the value of the row is divisble by 3, assgin h by 2.
						h = 2;
					box[i].setBounds(50*(i%9)+50, 50*(i/9)+55, 50-w, 50-h);			
					
					box[i].setHorizontalAlignment(JTextField.CENTER);			//This keeps the number added by the user to the centre.	
					Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);	// Adds a border.
					box[i].setBorder(border);
					
					//box[i].setEditable(false);
					p.add(box[i]);
					
					if( i%9==8 )
						row++;
				}
				
				JButton b1 = new JButton("Give Up");
				b1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						g.solveSudoku(grid);
						for(int i=0; i<81; i++)
						{
							box[i].setText(""+grid[i/9][i%9]);
							box[i].setEditable(false);
							box[i].setBackground(Color.WHITE);
						}
						
					}
				});
				b1.setBounds(560, 350, 120, 50);
				b1.setFont(new Font("Tahoma", Font.BOLD, 12));
				p.add(b1);
				
				JButton b2 = new JButton("Hint");
				b2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						g.solveSudoku(grid);
						for(int i=0; i<81; i++)
						{
							if( boxCheck[i/9][i%9]==0 )
							{
								if( box[i].getText().equals("") )
									continue;
								
								int a = Integer.parseInt(box[i].getText());
								if( a==grid[i/9][i%9] )
									box[i].setBackground(Color.GREEN);
								else
									box[i].setBackground(Color.RED);
							}
						}
						
					}
				});
				b2.setBounds(560, 400, 120, 50);
				b2.setFont(new Font("Tahoma", Font.BOLD, 12));
				p.add(b2);
				
				JLabel l = new JLabel("Difficulty : ");
				JRadioButton d1 = new JRadioButton("Hard");
				JRadioButton d2 = new JRadioButton("Medium");
				JRadioButton d3 = new JRadioButton("Easy");
				
				switch( difficulty )
				{
					case 1 : d3.setSelected(true);
							 break;
					case 2 : d2.setSelected(true);
							 break;
					case 3 : d1.setSelected(true);
							 break;
				}
				
				ActionListener listener = new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JRadioButton button = (JRadioButton)e.getSource();
						
						int res = JOptionPane.showConfirmDialog(null, "Are you sure, you want to start a new game!!", "WARNING!!", JOptionPane.YES_NO_OPTION);
						
						if( res==0 )
						{
							if( button==d3 )
								GUI(1);
							else if( button==d2 )
								GUI(2);
							else
								GUI(3);
						}
					}
				};
				
				d1.addActionListener(listener);
				d2.addActionListener(listener);
				d3.addActionListener(listener);
				
				l.setBounds(560, 150, 120, 30);
				d1.setBounds(560, 240, 120, 30);
				d2.setBounds(560, 210, 120, 30);
				d3.setBounds(560, 180, 120, 30);
				
				l.setFont(new Font("Tahoma", Font.BOLD, 16));
				d1.setBackground(Color.WHITE);
				d2.setBackground(Color.WHITE);
				d3.setBackground(Color.WHITE);
				
				ButtonGroup group = new ButtonGroup();
				group.add(d1);
				group.add(d2);
				group.add(d3);
				
				p.add(l);
				p.add(d1);
				p.add(d2);
				p.add(d3);
				
				frame.add(p);
				frame.setSize(750, 600);
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
}
