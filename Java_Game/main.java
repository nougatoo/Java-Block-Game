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
 * 
 * 
 * TODO next:
 * 	- Add a label to show how many yellow blocks that we we have
 *  - Decide how i want to handle when i no longer need yellow blocks
 *  		- Maybe use the same alg i did to color everything yellow
 *  		  but instead of going from 0 -> 1 in grid check for 1 and then
 *  		  go to two?
 * 
 *  Long term TODO: 
 *   - Be able to save the game state (into a file) and then have the user
 *     have the option to load the game back up when they are done.
 *     
 * Last edited: May 15th, 2015
 */

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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
	private JButton lemon_button, orange_button;
	private JLabel currMon_label, lemonP_label, orangeP_label, currRate_label, totalMon_label;
	private JLabel numLemon_label, numOrange_label;
	private Canvas canvas;
	private Surface2 test;
	private int numYellow;
	private int drawYellow_break;
	private boolean gridFull;
	private final int MAX_GRID_NUM = 400;
	
	//Non-Graphics Attributes
	private double current_money;
	private double money_rate;
	private double total_money_made;
	
	
	/*
	 * 0 = Lemons
	 * 1 = Oranges
	 */
	private double[] prices = new double[3];
	private double[] rates = new double[2]; //Lemons -> oranges ...etc
	private int[] fruit_count = new int[3];
	private int[][] grid = new int[20][20];
	private double start_time;
	private double end_time;
	private double difference;
	private DecimalFormat df2 = new DecimalFormat("###.##");

	
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
		
		//Sets up some graphics things
		drawYellow_break = 0;
		
		//Sets up prices
		prices[0] = 2; //Lemon price
		prices[1] = 10; //Apple price
		prices[2] = 20; //Orange price
		
		rates[0] = 1;
		rates[1] = 2;
		
		fruit_count[0] = 0;
		fruit_count[1] = 0;
		
		numYellow = 0;
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
	
	/**
	 * Does all the time interval calculations to add to current and total money
	 */
	public void showStats()
	{
		String temp;
		int yellowBreakpoint;
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
		yellowBreakpoint = (int)(total_money_made/100);
		if(yellowBreakpoint > drawYellow_break)
		{
			int i,j;
			
			numToColor = yellowBreakpoint - drawYellow_break;
			if((numToColor + numYellow) > MAX_GRID_NUM) //CHANGE WHEN ADDING MORE FRUIT
			{
				numToColor = MAX_GRID_NUM - numYellow;
			}
			
			if (isGridFull() == false)
			{
				
				do{
					i = utility.getRandInt(19, 0);
					j = utility.getRandInt(19, 0);
					
		        	if(grid[i][j] == 0)
		        	{
			        	grid[i][j] = 1;
			        	colored++;
			        	numYellow++;
		        	}
		        
				}while(grid[i][j] == 0 || (colored < numToColor));
				
		        test.repaint();
	
				drawYellow_break = yellowBreakpoint;
			}//End of if statement
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
	
	private boolean isGridFull(){
		
		int size;
		
		//Need to add up all the colors here 
		size = numYellow;
		
		return (size >= MAX_GRID_NUM);
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
		//Could have a method for each button that handles each case and this method will just send the program to that place
		if("lemon".equals(e.getActionCommand()))
		{
			button = 1;
		}
		else if("orange".equals(e.getActionCommand()))
		{
			button = 2;
		}
		
		handlePress(button);
	}
	
	public void handlePress(int num_button)
	{
		int choice;
		choice = num_button-1;
		if((current_money >= prices[choice]))
		{
			//Lemon choice
			money_rate += rates[choice];
			current_money -= prices[choice];
			prices[choice] = Math.pow(prices[choice], 1.1);
			fruit_count[choice] += 1;
			
			updateLabels();
			updatePrice(choice);
			
		}
		
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
		else //have to make this else if when i add more fruit
		{
			fruit = orangeP_label;
			fruit_num = numOrange_label;
		}
	    fruit.setText("$" + temp);
	    
	    temp = Integer.toString(fruit_count[choice]);
	    fruit_num.setText("[ " + temp + " ]");
	    
	}
	
	/*
	 * Updates current money, money rate
	 */
	public void updateLabels()
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
	


	public void drawButtons()
	{
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
	
	}

	/*
	 * Makes calls to the create_label function in the utilties class to create
	 * all the labels needed for the game
	 */
	public void drawLabels()
	{
		String temp; 

        //Creates a label for total money made
        temp = Double.toString(total_money_made);
        totalMon_label = utility.create_label(125, 350, "Total Money Made: $" + temp, 170, 25, Color.WHITE);
        test.add(totalMon_label);

        //Creates a label for the current money made
        temp = Double.toString(current_money);
        currMon_label = utility.create_label(125, 400, "Current money: $" + temp, 160, 25, Color.WHITE);
        test.add(currMon_label);              
    
        //Creates a label for the current money rate
        temp = Double.toString(money_rate);
        currRate_label = utility.create_label(125, 375, "Current money rate: $" + temp, 160, 25, Color.WHITE);
        test.add(currRate_label);              

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
        numOrange_label = utility.create_label(120, FIRST_BUTTON+30, "[ " + temp + " ]", 30, 30, Color.WHITE);
        test.add(numOrange_label);         
        
	}
	
	

	public static void main(String[] args){
		
		main gameState = new main(); //New game
		Utilities util = new Utilities(); //For non-game functions
		gameState.initialize();

		gameState.draw();	
		gameState.drawLabels();
		
		for(int i=0;i<1000;i++)
		{

			util.waitMili(123);	
			gameState.showStats();
				
		}

		
		
		

	

	}

}


