/**
 * main.java
 * Created By: Brandon Brien
 * 
 * A game where users can buy things in order to get more money
 * Basically a copy of adventure capitalist
 * 
 * Blocks are used to purchase special upgrades
 * hopefully they can be brought up in a menu (the choices)
 * 
 * As the user starts to generate more and more money, blocks become
 * harder and harder to generate, so be careful with your spending!
 * 
 * 
 * 
 * TODO next:
 * 	- Fixing bug that is some where in upgradeBlocks
 * 
 *  - Decide how i want to handle when i no longer need yellow blocks
 *  		- Maybe use the same alg i did to color everything yellow
 *  		  but instead of going from 0 -> 1 in grid check for 1 and then
 *  		  go to two?
 * 
 * 
 *  Long term TODO: 
 *   - Be able to save the game state (into a file) and then have the user
 *     have the option to load the game back up when they are done.
 *  
 *   - Have a welcome screen that ask the users if they wanted to continue their game or 
 *     start a new one
 *     
 * Last edited: May 15th, 2015
 */

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;





public class main extends JPanel implements ActionListener{
	
	private Utilities utility;

	//Graphic attributes
	private JFrame frame;
	private JPanel panel;
	
	private JButton lemon_button, orange_button, apple_button;
	private JButton upgradeYBlocks_button, upgradeOBlocks_button, upgradeRBlocks_button;
	
	private JLabel currMon_label, currRate_label, totalMon_label, lemonP_label, orangeP_label, appleP_label;
	private JLabel numLemon_label, numOranges_label, numApple_label; //Number of fruit purchased
	private JLabel numYellow_label, numOrange_label, numRed_label; //Number of blocks generated label
	
	private Canvas canvas;
	private Surface2 test;
	
	private int[] numBlock = new int[3]; //Integer rep of all blocks
	private int numYellow; //Integer representation of number of blocks (colors)
	private int drawYellow_break;
	private boolean gridFull;
	private final int MAX_GRID_NUM = 400;
	private final int NUMBER_OF_COLORS = 3;
	
	//Non-Graphics Attributes
	private double current_money;
	private double money_rate;
	private double total_money_made;
	
	
	/*
	 * 0 = Lemons
	 * 1 = Oranges
	 */
	private double[] prices = new double[3];
	private double[] rates = new double[3]; //Lemons -> oranges ...etc
	private int[] fruit_count = new int[3];
	private int[][] grid = new int[20][20];
	private int[] breakpoint = new int[3]; //Increase when i add more things than lemons are oranges
	private double start_time;
	private double end_time;
	private double difference;
	private DecimalFormat df2 = new DecimalFormat("###.##");
	private int yellow_per_dollar, orange_per_dollar, red_per_dollar;
	private int color_per_dollar_multiplier;
	private boolean multiplier_changed = false;

	
	final int WIDTH = 800;
	final int HEIGHT = 600;
	final int FIRST_BUTTON = 450;
	
	/**
	 * Initialize game state (non graphics)
	 * @return Boolean
	 */
	public Boolean initialize()
	 {
		utility = new Utilities();
		current_money = 10;
		money_rate = 1;
		total_money_made = current_money;
		System.out.println("Welcome to some shitty incremental game!!\n");
		
		//Initial breakpoint for all colors of blocks is 0
		breakpoint[0] = 0; //Lemons
		breakpoint[1] = 0; //Oranges
		breakpoint[2] = 0; //Apples
		
		//For every $ amount, they gain 1 block
		yellow_per_dollar = 100;
		orange_per_dollar = 1000;
		red_per_dollar = 1000000;
		color_per_dollar_multiplier = 1;
		

		//Sets up prices
		prices[0] = 2; //Lemon price
		prices[1] = 10; //Orange price
		prices[2] = 100; //Apple price
		
		rates[0] = 1000;
		rates[1] = 100;
		rates[2] = 150;
		
		//Put these into loops so i don't have to do it manually when i add a fruit
		fruit_count[0] = 0;
		fruit_count[1] = 0;
		fruit_count[2] = 0;
		
		numBlock[0] = 0;
		numBlock[1] = 0;
		numBlock[2] = 0;
		gridFull = false; //The grid is full if all the colored blocks add up to maxgrid num
		
		//Initializes our grid to zero
		for(int i = 0;i<20;i++)
		{
			for(int j = 0;j<20;j++)
			{
				grid[i][j]=0;
			}
		}
			
		//Gets current time to so we know how much to add later when we want to calculate
		//how much the user has made
		start_time = System.currentTimeMillis();
		 
		return true;
	 }
	
	/*
	 * - Does all the time interval calculations to add to current and total money
	 * - Takes our total money and checks whether we are at a break point for a certain colored
	 * 	 block. If we are, call handleBreakPoint to deal with it
	 */
	public void showStats()
	{
		String temp;
		int yellowBreakpoint, orangeBreakpoint, redBreakpoint;
		int numToColor;
		int colored = 0;

		//Going to calculate how much money the user has made since we last checked 
		end_time = System.currentTimeMillis();
		difference = end_time-start_time;
		start_time = System.currentTimeMillis();
		current_money += money_rate*(difference/1000);
		total_money_made += money_rate*(difference/1000);
		
		//Draw a yellow block every $100 earned, so if money made / 100 is
		// not equal to current break then draw a yellow cube at some random point
		
		//Can probably just have a one method for this later instead of having one of these blocks
		// per color
		yellowBreakpoint = (int)(total_money_made/(yellow_per_dollar*color_per_dollar_multiplier));
		orangeBreakpoint = (int)(total_money_made/(orange_per_dollar*color_per_dollar_multiplier));
		redBreakpoint = (int)(total_money_made/(red_per_dollar*color_per_dollar_multiplier));
		
		/*
		 * Yellow Breakpoint handling
		 */
		if(yellowBreakpoint > breakpoint[0] && (breakpoint[0]%100 != 9))
		{
			handleBreakPoint(yellowBreakpoint, 0, numBlock[0]);
		}
		else if(breakpoint[0]%100 == 9 && (yellowBreakpoint>breakpoint[0]))
		{

			breakpoint[0]++;
		}
		

		/*
		 * Orange Breakpoint handling
		 */
		if(orangeBreakpoint > breakpoint[1] && (breakpoint[1]%100 != 9))
		{
			handleBreakPoint(orangeBreakpoint, 1, numBlock[1]);
		}
		else if(breakpoint[1]%100 == 9 && (orangeBreakpoint>breakpoint[1]))
		{
			breakpoint[1]++;
		}
		

		/*
		 * Red Breakpoint handling
		 */
		if(redBreakpoint > breakpoint[2] && (breakpoint[2]%100 != 9))
		{
			handleBreakPoint(redBreakpoint, 2, numBlock[2]);
		}
		else if(breakpoint[2]%100 == 9 && (redBreakpoint>breakpoint[2]))
		{
			breakpoint[2]++;
		}
		
		
		
		//Rounds to the nearest 2
		current_money = Double.valueOf(df2.format(current_money));
        
        //Updates our current amount of money
		String curr_str = Double.toString(current_money);
        currMon_label.setText("Current money: $" + curr_str);
        
        //Updates the total amount of money made
		total_money_made = Double.valueOf(df2.format(total_money_made));
		temp = Double.toString(total_money_made);
	    totalMon_label.setText("Total money made: $" + temp);
	    
	}
	
	/*
	 * Each colored block has breakpoint, so every $x they earn a colored block of a certain
	 * type. This function gets called when a breakpoint is reached, it does not check
	 * whether we are at a break point
	 */
	public void handleBreakPoint(int currentColorBreak, int breakPointIndex, int numColor)
	{
		int i,j;
		int numToColor;
		int colored = 0;

		
		numToColor = currentColorBreak - breakpoint[breakPointIndex];	
		
		
		if((numToColor + getNumBlocks()) >= MAX_GRID_NUM) //CHANGE WHEN ADDING MORE FRUIT ///DO GETSIZE
		{
			handleFullGrid();
		}
		
		if (isGridFull() == false)
		{
			//Loop until we've created enough blocks for all the break points we passed
			// or look when we generated a random block that was already taken
			do{
				i = utility.getRandInt(20, 0);
				j = utility.getRandInt(20, 0);
				
	        	if(grid[i][j] == 0)
	        	{
	        		/*
	        		 * Since breakpoint index will always be 1 less than the number that's used to 
	        		 * determine what color to paint a square, we just use it to tell surface2 what
	        		 * to paint instead of sending a color as a parameter
	        		 */
		        	grid[i][j] = breakPointIndex+1; //Since break point index will
		        	colored++;
		        	numBlock[breakPointIndex]++;
	        	}
	        	
	        	/*
	        	System.out.println("bug");
	        	System.out.println(colored);
	        	System.out.println(breakPointIndex);
	        	System.out.println(numToColor);
	        	System.out.println(numBlock[0] + numBlock[1] + numBlock[2]);
	        	System.out.println(getNumBlocks());
	        	System.out.println(isGridFull() + "\n");
	        	*/
	        	
			}while(grid[i][j] == 0 || (colored < numToColor));
			
			
	        test.repaint(); //Redraw grid 
			breakpoint[breakPointIndex] = currentColorBreak; //Lets us know when we generate the next 100
			updateBlockLabels(); //Update block label to show the blocks we just generated
			
		}

	}
	
	public void handleFullGrid()
	{
		for(int i = NUMBER_OF_COLORS;i>0;i--)
		{
			if(numBlock[i-1] >= 100)
			{
				upgradeBlocks(i-1);	
			}
		}
		

	}
	
	private boolean isGridFull(){
		
		int size;
		
		//Need to add up all the colors here 
		size = numBlock[0] + numBlock[1] + numBlock[2];
		
		return (size >= MAX_GRID_NUM);
	}
	
	private int getNumBlocks()
	{
		int totalBlocks = 0;
		
		for(int i=0;i<NUMBER_OF_COLORS;i++)
		{
			totalBlocks += numBlock[i];
		}
		return totalBlocks;
	}
	
	
	public void draw(){
		
		
		System.out.println("Helo");
		//Sets up the frame
		frame = new JFrame("Test Box");
		frame.pack();

		frame.setSize(WIDTH, HEIGHT);
	    frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//Sets up the panel
		test = new Surface2(grid);
		test.setLayout(null);
		test.setBackground(Color.BLACK);
		
			
        frame.add(test);
                 
		drawButtons();
				
        //Shows everything we need		
		frame.setVisible(true);	
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int button = -1;
		int block = -1;
		//Could have a method for each button that handles each case and this method will just send the program to that place
		if("lemon".equals(e.getActionCommand()))
		{
			button = 1;
			handlePress(button);
		}
		else if("orange".equals(e.getActionCommand()))
		{
			button = 2;
			handlePress(button);
		}
		else if("apple".equals(e.getActionCommand()))
		{
			button = 3;
			handlePress(button);
		}
		else if("upgradeYellow".equals(e.getActionCommand()))
		{
			block = 0;
			upgradeBlocks(block);
			
		}
		else if("upgradeOrange".equals(e.getActionCommand()))
		{
			block = 1;
			upgradeBlocks(block);
			
		}
	}
	/*
	 * Converts as many possible blocks from one to the next
	 * 0 = yellow
	 * 1 = orange
	 * 2 = red
	 * ...etc
	 */
	public void upgradeBlocks(int block)
	{
		
		/*
		 * Maybe make a new upgrade blocks method that only upgrades 100 at a time and just have to call it multiple times 
		 * or just make a new grid every time a reset happens with the correnct num_blocks
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
		
		
		Point[] findColors = new Point[MAX_GRID_NUM];
		Point tempPoint;
		int raw_numBlocks_to_upgrade, numBlocks_to_upgrade;
		int nextEmpty = 0;
		int randomNum;
		int m = 0;
		
		raw_numBlocks_to_upgrade = numBlock[block];
		numBlocks_to_upgrade = raw_numBlocks_to_upgrade/100; //This will have to change if we want different conversion rates instead of 100 per and lower in the function
		
		System.out.println(block);
		System.out.println(numBlock[block]);
		System.out.println(getNumBlocks());
		System.out.println();
		
		
		numBlock[block] = numBlock[block] - (numBlocks_to_upgrade*100); //100 blocks = 1 block of the next kind
		numBlock[block+1] = numBlock[block+1] + numBlocks_to_upgrade; 
		
		
		
		updateBlockLabels();
		//NOw...how to update grid
		//I want to create an array that holds all the yellow points(after traversing through the
		// grid, then i can get a random integer from it changed x amount, then redraw the grid
		if(numBlocks_to_upgrade > 0)
		{
			for(int i=0;i<20;i++)
			{
				for(int j=0;j<20;j++)
				{
					if(grid[i][j] == (block+1)) //In grid 0 = black, 1 = yellow, orange=2, etc...
					{
						findColors[nextEmpty] = new Point(i,j);
						nextEmpty++;
					}
				}
			}
			
			

			
			//Pseudo shuffle of the locations of the array so that when we removed we don't always remove
			//the first 100
			int index;
		    for (int i = (nextEmpty -1 ) - 1; i > 0; i--)
		    {
		      index = utility.getRandInt(nextEmpty-1, 0);
		      // Simple swap
		      Point a = findColors[index];
		      findColors[index] = findColors[i];
		      findColors[i] = a;
		    }
		    
			
		    /*
		     * Takes the first 100*numBlocks upgraded entries of the shuffle array (locations of colors) and decreases
		     * their value by 1 and takes the last couple (number of colors above current colors) 
		     * and increases their value by 1
		     */
			
			
		    for(int i=0;i<(numBlocks_to_upgrade*100);i++)
			{

	
		    	
				tempPoint = findColors[i];
				if(i < ((numBlocks_to_upgrade*100)-numBlocks_to_upgrade))
				{
					if(block == 0)
						grid[findColors[i].x][findColors[i].y] += -1;
					else
						grid[findColors[i].x][findColors[i].y] = 0;;
				}
				else
				{
					grid[findColors[i].x][findColors[i].y] += 1;
				}
				
			}

		    
			test.repaint(); //Color the grid that we just changed
		}//End of if statement	
	}
	
	public void handlePress(int num_button)
	{
		int choice;
		choice = num_button-1;
		if((current_money >= prices[choice]))
		{
			//Fruit choice
			money_rate += rates[choice];
			current_money -= prices[choice];
			prices[choice] = Math.pow(prices[choice], 1.1);
			fruit_count[choice] += 1;
			
			/*
			 * Makes a call the change the block multiplier
			 * 
			 *  - Later the 1000 will have to be changed to some integer division function
			 * 
			 */
			if(money_rate > 1000 && multiplier_changed==false)
			{
				changeBlockGenMultiplier();
			}
			
			
			
			updateMoneyLabels();
			updatePrice(choice);
			
		}
		
	}
	
	/*
	 * Input: none
	 * 
	 * Method makes the program generate less blocks so they are more rare as they 
	 * earn more money per second
	 * 
	 * Future: 
	 *  - Can add an argument that tells the method how to change it
	 *  - Might only want to have specific fruit multiplier changes, but be so hard
	 *    to generate the later blocks
	 *  - Add a pop up message to the user that tells them because it's about to get 
	 *    harder to generate blocks but they get some extra ones for free!
	 */
	public void changeBlockGenMultiplier()
	{
		
		color_per_dollar_multiplier += 2;
		
		breakpoint[0] = 0; //Lemons
		breakpoint[1] = 0; //Oranges
		breakpoint[2] = 0; //Apples
		multiplier_changed = true;

	}
	
	/*
	 * Updates fruit price, and number of them lables
	 */
	public void updatePrice(int choice)
	{
		//A place holder string just to show price
		String temp;
		JLabel fruit, fruit_num;
		
		prices[choice] = Double.valueOf(df2.format(prices[choice]));
		temp = Double.toString(prices[choice]);
		
		if(choice == 0)
		{
			//Could just fill these into an array and access the array instead
			fruit = lemonP_label;
			fruit_num = numLemon_label;
		}
		else if (choice == 1) //have to make this else if when i add more fruit
		{
			fruit = orangeP_label;
			fruit_num = numOranges_label;
		}
		else
		{
			fruit = appleP_label;
			fruit_num = numApple_label;
		}
	    fruit.setText("$" + temp);
	    
	    temp = Integer.toString(fruit_count[choice]);
	    fruit_num.setText("[ " + temp + " ]");
	    
	}
	
	/*
	 * Updates current money, money rate
	 */
	public void updateMoneyLabels()
	{	
		//A place holder string used for display
		String temp;
		
		current_money = Double.valueOf(df2.format(current_money));
		temp = Double.toString(current_money);
	    currMon_label.setText("Current money: $" + temp);
	   
	    //change to rate label
		money_rate = Double.valueOf(df2.format(money_rate));
		temp = Double.toString(money_rate);
	    currRate_label.setText("Current money rate: $" + temp);
	}
	
	public void updateBlockLabels()
	{
		String temp;

	    //Change yellow block label
		temp = Integer.toString(numBlock[0]);
	    numYellow_label.setText("Number of Yellow Blocks: " + temp);
	    
	    //Change Orange block label
		temp = Integer.toString(numBlock[1]);
	    numOrange_label.setText("Number of Orange Blocks: " + temp);
	    
	    //Change Red block label
		temp = Integer.toString(numBlock[2]);
	    numRed_label.setText("Number of Red Blocks: " + temp);
		
	}
	
	


	public void drawButtons()
	{
		Font smallButtonFont = new Font("Arial", Font.PLAIN, 10);
	    /*
	     * Button to buy lemons
	     * On press: buys a lemon and updates all things to show that
	     */
	    lemon_button = new JButton(); 
	    test.add(lemon_button);
	    lemon_button.setBounds(15,FIRST_BUTTON, 100, 25);
	    lemon_button.setVisible(true);
	    lemon_button.setText("Lemons");
	    lemon_button.addActionListener(this);
	    lemon_button.setActionCommand("lemon");
	    

	    /*
	     * Button to buy oranges
	     * On press: buys a orange and updates all things to show that
	     */
	    orange_button = new JButton();
	    test.add(orange_button);
	    orange_button.setBounds(15, FIRST_BUTTON+35,100,25);
	    orange_button.setVisible(true);
	    orange_button.setText("Oranges");  
	    orange_button.addActionListener(this);
	    orange_button.setActionCommand("orange");
	    
	    /*
	     * Button to buy apples
	     * On press: buys a apples and updates all things to show that
	     */
	    apple_button = new JButton();
	    test.add(apple_button);
	    apple_button.setBounds(15, FIRST_BUTTON+70,100,25);
	    apple_button.setVisible(true);
	    apple_button.setText("Apple");  
	    apple_button.addActionListener(this);
	    apple_button.setActionCommand("apple");
	    
	    
	    
	    /*
	     * Button to upgrade yellow blocks to orange
	     * 
	     */
	    upgradeYBlocks_button = new JButton();
	    test.add(upgradeYBlocks_button);
	    upgradeYBlocks_button.setBounds(675, 105, 80,18); //y is 4 higher than label y
	    upgradeYBlocks_button.setFont(smallButtonFont);
	    upgradeYBlocks_button.setVisible(true);
	    upgradeYBlocks_button.setText("Upgrade!");
	    upgradeYBlocks_button.addActionListener(this);
	    upgradeYBlocks_button.setActionCommand("upgradeYellow");
	    
	    /*
	     * Button to upgrade orange blocks to red
	     * 
	     */
	    upgradeOBlocks_button = new JButton();
	    test.add(upgradeOBlocks_button);
	    upgradeOBlocks_button.setBounds(675, 145, 80,18); //y is 4 higher than lable y
	    upgradeOBlocks_button.setFont(smallButtonFont);
	    upgradeOBlocks_button.setVisible(true);
	    upgradeOBlocks_button.setText("Upgrade!");
	    upgradeOBlocks_button.addActionListener(this);
	    upgradeOBlocks_button.setActionCommand("upgradeOrange");
	    
	    /*
	     * Button to upgrade red blocks to purple
	     * 
	     */
	    upgradeRBlocks_button = new JButton();
	    test.add(upgradeRBlocks_button);
	    upgradeRBlocks_button.setBounds(675, 185, 80,18); //y is 4 higher than lable y
	    upgradeRBlocks_button.setFont(smallButtonFont);
	    upgradeRBlocks_button.setVisible(true);
	    upgradeRBlocks_button.setText("Upgrade!");
	    upgradeRBlocks_button.addActionListener(this);
	    upgradeRBlocks_button.setActionCommand("upgradeRed");
	
	}
	
	/*
	 * Makes calls to the create_label function in the utilties class to create
	 * fruit and money labels
	 */
	public void drawLabels()
	{
		String temp; 
		
		/* 
		 * Money Labels
		 */

        //Creates a label for total money made
        temp = Double.toString(total_money_made);
        totalMon_label = utility.create_label(125, 350, "Total Money Made: $" + temp, 250, 25, Color.WHITE);
        test.add(totalMon_label);

        //Creates a label for the current money 
        temp = Double.toString(current_money);
        currMon_label = utility.create_label(125, 400, "Current money: $" + temp, 250, 25, Color.WHITE);
        test.add(currMon_label);              
    
        //Creates a label for the current money rate
        temp = Double.toString(money_rate);
        currRate_label = utility.create_label(125, 375, "Current money rate: $" + temp, 250, 25, Color.WHITE);
        test.add(currRate_label);
        
        /*
         * Fruit Labels
         */

        //Creates a label for the price of lemons
        temp = Double.toString(prices[0]);
        lemonP_label = utility.create_label(160, FIRST_BUTTON, "$" + temp, 75, 25, Color.WHITE);
        test.add(lemonP_label);
        
        //Creates a label for the number of lemons
        temp = Integer.toString(fruit_count[0]);
        numLemon_label = utility.create_label(120, FIRST_BUTTON-5, "[ " + temp + " ]", 30, 30, Color.WHITE);
        test.add(numLemon_label);
        
        //Creates a label for the price of oranges
        temp = Double.toString(prices[1]);
        orangeP_label = utility.create_label(160, FIRST_BUTTON+35, "$" + temp, 75, 25, Color.WHITE);
        test.add(orangeP_label);
                         
        //Creates a label for the number of oranges
        temp = Integer.toString(fruit_count[1]);
        numOranges_label = utility.create_label(120, FIRST_BUTTON+30, "[ " + temp + " ]", 30, 30, Color.WHITE);
        test.add(numOranges_label);      
        
        //Creates a label for the price of apples
        temp = Double.toString(prices[2]);
        appleP_label = utility.create_label(160, FIRST_BUTTON+70, "$" + temp, 75, 25, Color.WHITE);
        test.add(appleP_label);
        
        //Creates a label for the number of apples
        temp = Integer.toString(fruit_count[2]);
        numApple_label = utility.create_label(120, FIRST_BUTTON+65, "[ " + temp + " ]", 30, 30, Color.WHITE);
        test.add(numApple_label);
        
        /*
         * Blocks Labels
         */
        
        //Number of yellow blocks label
        temp = Integer.toString(numBlock[0]);
        numYellow_label = utility.create_label(500, 101, "Number of Yellow Blocks: " + temp, 200, 25, Color.WHITE);
        test.add(numYellow_label);
        
        //Number of orange blocks label
        temp = Integer.toString(numBlock[1]);
        numOrange_label = utility.create_label(500, 141, "Number of Orange Blocks: " + temp, 200, 25, Color.WHITE);
        test.add(numOrange_label);
        
        //Number of red blocks label
        temp = Integer.toString(numBlock[1]);
        numRed_label = utility.create_label(500, 181, "Number of Red Blocks: " + temp, 200, 25, Color.WHITE);
        test.add(numRed_label);
        
        

	}
	
	

	public static void main(String[] args){
		
		main gameState = new main(); //New game
		Utilities util = new Utilities(); //For non-game functions
		gameState.initialize();

		gameState.draw();	
		gameState.drawLabels();
		
		for(int i=0;i<100000;i++)
		{

			util.waitMili(123);	
			gameState.showStats();
				
		}

		
		
		

	

	}

}


