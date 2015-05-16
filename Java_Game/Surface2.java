import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

class Surface2 extends JPanel implements ActionListener {

	protected JButton b1, b2;
	/*
	 * 0 = White (for now)
	 * 1 = Yellow
	 * 2 = Orange
	 * etc...
	 */
	private int[][] squareGrid;
    public Surface2(int[][] grid) {
		
    	squareGrid= grid;
    	
	}

	private void doDrawing(Graphics g) {

    	g.setColor(Color.BLUE);
    	g.fillRect(0, 0, 10, 10);
    	int maxNum = 10;
    	
    	for(int x = 0;x<20;x++)
    	{
    		for(int y = 0;y<20;y++)
    		{
    			if(squareGrid[x][y] == 0)
    			{
    				g.setColor(Color.WHITE);
    			}
    			else if(squareGrid[x][y] == 1)
    			{
    				g.setColor(Color.YELLOW);
    			}
    	        
    	        g.fillRect(x*15, y*15, 15, 15);
    	    	
    			
    		}
    	}
    	
  	  	//g.setColor(Color.RED);
  	  	//g.fillRect(i*20, j*20, 20, 20);
    	
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Java 2D", 50, 50);
        
        //g.setColor(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        
        doDrawing(g);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void asdf(Graphics2D g2d) {
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0,0,20,20);
		
	}
}