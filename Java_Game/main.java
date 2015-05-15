/**
 * game.java
 * Created By: Brandon Brien
 * 
 * A game where users can buy things in order to get more money
 * Basically a copy of adventure capitalist
 * 
 * Instead of money a user will use blocks to buy thigns
 * 
 * TODO: Just  finished learning how to update the current money for each internval
 * 		 (can change time later, at 1 second now). that number needs to be rounded 
 *  	 when it's calculated to the nearest hundreth. 
 *  
 *  	- Next thing should be getting the buttons to work like the sample game that i made.
 *  	  Pretty much want the sample game to be complete before i start working with the block
 *  	  side of things.
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

	//Graphic attributes
	private JFrame frame;
	private JPanel panel;
	private JButton b1, b2;
	private JLabel lab1, lab2, lab3;
	private Canvas canvas;
	private Surface2 test;
	
	//Non-Graphics Attributes
	private double current_money;
	private double money_rate;
	
	/*
	 * 0 = Lemons
	 * 1 = Apples
	 * 2 = Oranges
	 */
	private double[] prices = new double[3];
	private int[] fruit_count = new int[3];
	private double start_time;
	private double end_time;
	private double difference;

	
	final int WIDTH = 800;
	final int HEIGHT = 800;
	final int FIRST_BUTTON = 500;
	
	/**
	 * Initialize game state (non graphics)
	 * @return Boolean
	 */
	public Boolean initialize()
	 {
		current_money = 1;
		money_rate = 1;
		System.out.println("Welcome to some shitty incremental game!!\n");
		
		//Sets up prices
		prices[0] = 2; //Lemon price
		prices[1] = 10; //Apple price
		prices[2] = 20; //Orange price
			
		//Gets current time to so we know how much to add later when we want to calculate
		//how much the user has made
		start_time = System.currentTimeMillis();
		 
		return true;
	 }
	
	/**
	 * Shows all the users stats (problay don't relaly need it in this form for later)
	 */
	public void showStats()
	{

		//Going to calculate how much money the user has made since we last checked 
		end_time = System.currentTimeMillis();
		difference = end_time-start_time;
		start_time = System.currentTimeMillis();
		current_money += money_rate*(difference/1000);
		
		//Rounds to the nearest 2
		DecimalFormat df2 = new DecimalFormat("###.##");
        current_money = Double.valueOf(df2.format(current_money));
        
        
        //Updates our current amount of money
		String curr_str = Double.toString(current_money);
        lab1.setText("Current money: $" + curr_str);
		
		//System.out.printf("You currently have: $" + "%.2f" + " in your bank acccount\n", current_money );
		//System.out.println("And you get: $" + money_rate + " per second\n");
		
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
		test = new Surface2();
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
			b1.setText("Hello");
		}
		else if("orange".equals(e.getActionCommand()))
		{
			button = 2;
			b2.setText("Hello");
		}
		
		handlePress(button);
	}
	
	public void handlePress(int num_button)
	{
		//Do stuff to handle button
		
	}
	
	public void drawButtons()
	{
        //Button 1
        b1 = new JButton();
        test.add(b1);
        b1.setBounds(15,FIRST_BUTTON, 100, 25);
        b1.setVisible(true);
        b1.setText("Lemons");
        b1.addActionListener(this);
        b1.setActionCommand("lemon");
        
        //Button 2
        b2 = new JButton();
        test.add(b2);
        b2.setBounds(15, FIRST_BUTTON+35,100,25);
        b2.setVisible(true);
        b2.setText("Oranges");  
        b2.addActionListener(this);
        b2.setActionCommand("orange");

	}
	
	public void drawLabels()
	{
        //Adds a label for current money
		String curr_str = Double.toString(current_money);
        lab1 = new JLabel();
        lab1.setText("Current money: $" + curr_str);
        lab1.setForeground(Color.WHITE);
        lab1.setBounds(125, 410, 300, 100);
        //lab1.setBounds(new Rectangle(new Point(125, 450), lab1.getPreferredSize()));
        test.add(lab1);
        
        
        //Adds a label for lemon price 
		String lemon_price = Double.toString(prices[0]);
        lab2 = new JLabel("$" + lemon_price);
        lab2.setForeground(Color.WHITE);
        lab2.setBounds(new Rectangle(new Point(150, FIRST_BUTTON), lab2.getPreferredSize()));
        test.add(lab2);
        
        //Adds a label for orange price 
		String orange_price = Double.toString(prices[1]);
        lab3 = new JLabel("$" + orange_price);
        lab3.setForeground(Color.WHITE);
        lab3.setBounds(new Rectangle(new Point(150, FIRST_BUTTON + 35), lab3.getPreferredSize()));
        test.add(lab3);
        
        
	}
	
	/*
	 * Testing whether or not i can update the labels properly
	 */
	public void testUpdate()
	{
		lab3.setText("asdf");
		
	}
	
	
	//Maybe put this into a utility class that just has some random things to use
	public void waitSeconds(int seconds)
	{
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	public static void main(String[] args){
		
		main asdf = new main();
		asdf.initialize();
		//asdf.showStats();

		asdf.draw();	
		asdf.drawLabels();
		
		asdf.waitSeconds(1);	
		asdf.showStats();
		
		asdf.waitSeconds(1);
		asdf.showStats();
		

		
		
		

	

	}

}

