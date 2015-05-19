import java.awt.Point;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;

/*
 * TODO: add get methods
 */
public class Fruit {
	
	private String name;
	
	
	private double price;
	private double rate;
	
	private int count; //Fruit_num
		
	public Fruit(String desired_name, double desired_price, int desired_rate)
	{
		
		name = desired_name;		
		price = desired_price;
		rate = desired_rate;
		
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double new_price)
	{
		price = new_price;
	}
	
	public double getRate()
	{
		return rate;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void setCount(int new_count)
	{
		count = new_count;
	}
	
	public String getName()
	{
		return name;
	}
	
}