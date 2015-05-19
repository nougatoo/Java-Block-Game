import java.awt.Color;
import java.util.Random;

import javax.swing.JLabel;


public class Utilities {
	
	/*
	 * Input: integer for number of miliseconds to wait
	 */
	public void waitMili(int mili)
	{
		try {
			Thread.sleep(mili);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	/*
	 * Creates a label and returns it to user. Does not add to a panel/frame
	 * Input: location (x,y), label text, size (x,y), color
	 */
	public JLabel create_label(int x, int y, String text, int sizeX, int sizeY, Color color )
	{
		JLabel ff;
		ff = new JLabel(text);
        ff.setForeground(color);
        ff.setSize(sizeX, sizeY);
        ff.setLocation(x, y);
        
		JLabel asdf= null;
		
		
		return ff;
	}
	
	public void create_label_test(JLabel test, int x, int y, String text, int sizeX, int sizeY, Color color )
	{
		
		test.setText(text);
        test.setForeground(color);
        test.setSize(sizeX, sizeY);
        test.setLocation(x, y);
        
		JLabel asdf= null;
		
		
		//return test;
	}

	//Returns a random integer between two numbers
	public int getRandInt(int max, int min)
	{
        Random rand = new Random();
        int randomNum = rand.nextInt(((max-1) - min) + 1) + 0;    
        
        return randomNum;
	}
	
}
