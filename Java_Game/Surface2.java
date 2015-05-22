import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

class Surface2 extends JPanel implements ActionListener {

	protected JButton b1, b2;
	private final int BLOCK_SIZE_X = 15;
	private final int BLOCK_SIZE_Y = 15;
	private final int BLOCK_SPACING = 2;
	
	private Color SOFT_YELLOW = new Color(253,253,75);
	private Color SOFT_ORANGE = new Color(255,126,53);
	private Color SOFT_RED = new Color(255,50,50);
	private Color DARK_ORANGE = new Color(255,140,0);
	private Color SOFT_PURPLE = new Color(204,102,204);
	private Color SOFT_BLUE = new Color(100,100,255);
	
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
    
    /*
     * Draws little indicator squares besides the label that 
     * shows how many blocks are of each color there are
     */
    private void drawBlockLabelColors(Graphics g)
    {
    	//x should stay the same, y is 8 higher than label y
    	//Add more colors later
    	g.setColor(SOFT_YELLOW); 	
    	g.fillRect(480, 109, 10, 10); 
    	
    	g.setColor(SOFT_ORANGE);  	
    	g.fillRect(480, 149, 10, 10);
    	
    	g.setColor(SOFT_RED); 	
    	g.fillRect(480, 189, 10, 10);
    	
    	g.setColor(SOFT_PURPLE);
    	g.fillRect(480, 229, 10, 10);

    	g.setColor(SOFT_BLUE);
    	g.fillRect(480, 269, 10, 10);
    }

	private void doDrawing(Graphics g) {
		
		drawBlockLabelColors(g);

    	g.setColor(Color.BLUE);
    	g.fillRect(0, 0, 10, 10);
    	int maxNum = 10;
    	
    	for(int x = 0;x<20;x++)//Change the 20 if grid size is larger or smaller than 400
    	{
    		for(int y = 0;y<20;y++)
    		{
    			if(squareGrid[x][y] == 0)
    			{
    				g.setColor(Color.BLACK);
    			}
    			else if(squareGrid[x][y] == 1)
    			{
    				g.setColor(SOFT_YELLOW);
    			}
    			else if(squareGrid[x][y] == 2)
    			{
    				g.setColor(SOFT_ORANGE);
    			}
    			else if(squareGrid[x][y] == 3)
    			{
    				g.setColor(SOFT_RED);
    			}
    			else if(squareGrid[x][y] == 4)
    			{
    				g.setColor(SOFT_PURPLE);
    			}
    			else if(squareGrid[x][y] == 5)
    			{
    				g.setColor(SOFT_BLUE);
    			}
    			else if(squareGrid[x][y] == -1)
    			{
    				g.setColor(Color.GREEN);
    			}
    	        
    			 //The (15+2) can be changed to anything above 15 (will just increase spacing 
    			 
    	        g.fillRect(x*(BLOCK_SIZE_X+BLOCK_SPACING), y*(BLOCK_SIZE_Y+BLOCK_SPACING), BLOCK_SIZE_X, BLOCK_SIZE_Y); 
    	        //g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    		}
    	}
    	
  	  	//g.setColor(Color.RED);
  	  	//g.fillRect(i*20, j*20, 20, 20);
    	
        
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