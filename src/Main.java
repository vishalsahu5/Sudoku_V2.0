import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        // 0 means unassigned cells
        Main g = new Main();
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
					
		int difficulty = 1;
		
		g.createSudoku(grid);                //Adds 9 random elements to the grid.
		g.solveSudoku(grid);                 //Solves the grid using backtracking created above.
		g.sudokuGenerator(grid, difficulty); //Removes some elements from the completely solved grid, such that the sudoku always results in an unique solution.
		
		/*Code for GUI starts from here.*/
		JFrame frame = new JFrame("Sudoku Prototype");  //Creates a new JFrame with the title "Sudoku Prototype".
		JPanel p = new JPanel();                        //Creates a JPanel, on which we will add all our components.
		JTextField[] box = new JTextField[81];          //81 JTextField's, one for each element. 
		
		p.setBackground(Color.WHITE);                   //Set's the background color to white.
		p.setLayout(null);                              //We are not using any predefined Layout.
		
		int row = 1;                                    //This variable help's us to add a horizontal gap after every 3 lines.
		for(int i=0; i<81; i++)                         //A loop to traverse through all the 81 boxes in the grid.
		{
			box[i] = new JTextField();
			box[i].addKeyListener(new KeyAdapter(){     //This keyListener helps us to restrict the JTextField to numerical values only.
				public void keyTyped(KeyEvent e)
				{
					//if( box[i].getText().length() >= 1 )
						//e.consume();
					
					char ch = e.getKeyChar();           //If the value inserted by the user is not an Integer than we will just delete it from the grid.
					if( !(ch>='0' && ch<='9') )
						e.consume();
				}
			});
			
			if( grid[i/9][i%9]!=0 )                     //If the value of Grid is not 0 at that position, than we will show that number on the screen.
			{
				box[i].setText(" "+grid[i/9][i%9]);
				box[i].setEditable(false);
				box[i].setForeground(Color.RED);
				box[i].setBackground(Color.WHITE);
				box[i].setFont(new Font("Tahoma", Font.BOLD, 14));
			}
			
			int w = 0, h = 0;                           //The next 2 if statements will helps us make 9, 3x3 boxes on the board.
			if( (i+1)%3==0 && i%9!=0 )                  //If we the value of the column is divisble by 3, assgin w by 2.
				w = 2;                                  //
			if( row%3==0 )                              //If we the value of the row is divisble by 3, assgin h by 2.
				h = 2;                                  //
			box[i].setBounds(50*(i%9)+50, 50*(i/9)+55, 50-w, 50-h);			
			
			box[i].setHorizontalAlignment(JTextField.CENTER);			                //This keeps the number added by the user to the centre.
			Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);   // Adds a border.
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
		
		JLabel l = new JLabel("Difficulty : ");
		JRadioButton d1 = new JRadioButton("Hard");
		JRadioButton d2 = new JRadioButton("Medium");
		JRadioButton d3 = new JRadioButton("Easy", true);
		
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int res = JOptionPane.showConfirmDialog(null, "Are you sure, you want to start a new game!!", "WARNING!!", JOptionPane.YES_NO_OPTION);
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
