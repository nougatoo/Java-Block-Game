/**
 * game.java
 * 
 * A game where users can buy things in order to get more money
 * Basically a copy of adventure capitalist
 * This file is going to contain all basic game logic...for now
 * 
 * Last edited: May 10th, 2015
 
package Main;
import java.util.Scanner;
import java.math.*;

public class Game {
	
	//Attributes
	private double current_money;
	private double money_rate;
	
	
	 * 0 = Lemons
	 * 1 = Apples
	 * 2 = Oranges
	 
	private double[] prices = new double[3];
	private int[] fruit_count = new int[3];
	private double start_time;
	private double end_time;
	private double difference;
	//Have an array that stores the multiplier based on current level of fruit

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
	
	public void showStats()
	{

		//Going to calculate how much money the user has made since we last checked 
		end_time = System.currentTimeMillis();
		difference = end_time-start_time;
		start_time = System.currentTimeMillis();
		current_money += money_rate*(difference/1000);
		
		
		System.out.printf("You currently have: $" + "%.2f" + " in your bank acccount\n", current_money );
		System.out.println("And you get: $" + money_rate + " per second\n");
		
	}
	
	public void showOptions()
	{
	
		System.out.println("Here you can purchase some things in order to increase your money per second");
		System.out.printf("(1) Lemon[" + fruit_count[0] +"]: $" + "%.2f" +  ", +$1/s\n", prices[0]);
		System.out.printf("(2) Apple[" + fruit_count[1] +"]: $" + "%.2f" +  ", +$2/s\n", prices[1]);
		System.out.printf("(3) Orange[" + fruit_count[2] +"]: $" + "%.2f" +  ", +$10/s\n", prices[2]);
		System.out.println("(9) Show Stats");
		System.out.println("(0) Tutorial");
		
	}
	
	public int selectOption()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter you option here: ");
		int user_choice = keyboard.nextInt();
		
		return user_choice;
	}
	
	//Probably have a blanket function that just changes one variable based on users choice
	public void handleChoice(int choice)
	{
		if(choice == 1 && (current_money >= prices[0]))
		{
			//Lemon choice
			money_rate += 1;
			current_money -= prices[0];
			prices[0] = Math.pow(prices[0], 1.1);
			fruit_count[0] += 1;
		}
		else if(choice == 2 && (current_money >= prices[1]))
		{
			//Apple choice
			money_rate += 2;
			prices[1] = Math.pow(prices[1], 1.1);
			fruit_count[1] += 1;
		}
		else if (choice == 3 && (current_money >= prices[2]))
		{
			//Orange choice
			money_rate += 10;
			prices[2] = Math.pow(prices[2], 1.1);
			fruit_count[2] += 1;
		}
		else if(choice == 9)
		{
			//If the user wants to see how much money they have
		}
		else if(choice == 0)
		{
			//Show tutorial information here
		}
		else
		{
			System.out.println("Something went wrong or your choice was invalid");
		}
	}

}

*/
