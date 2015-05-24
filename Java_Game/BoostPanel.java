import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class BoostPanel extends JPanel {
	
	private int FIRST_BUTTON_X = 200;
	private int FIRST_BUTTON_Y = 100;
	
	private final int WIDTH = 325;
	private final int HEIGHT = 500;
	
	/*
	 * Colors needed/used
	 */
	private Color SOFT_YELLOW = new Color(253,253,75);
	private Color SOFT_ORANGE = new Color(255,126,53);
	private Color SOFT_RED = new Color(255,50,50);
	private Color DARK_ORANGE = new Color(255,140,0);
	private Color SOFT_PURPLE = new Color(204,102,204);
	private Color SOFT_BLUE = new Color(100,100,255);

    public BoostPanel() {
    	
    	setBackground(Color.BLACK);
    	setIgnoreRepaint(true);
    	setPreferredSize(new Dimension(300, 1000));

    }

    private void doDrawing(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.drawString("Boosts", 120, 18);
		
	   	g.setColor(SOFT_YELLOW); 	
    	g.fillRect(80, FIRST_BUTTON_Y-2, 10, 10); 
    	
	   	g.setColor(SOFT_ORANGE); 	
    	g.fillRect(80, FIRST_BUTTON_Y+86, 10, 10); 
    	
	   	g.setColor(SOFT_RED); 	
    	g.fillRect(80, FIRST_BUTTON_Y+174, 10, 10); 
    	
    	/*
    	 * White lines that separate the upgrades
    	 */
    	g.setColor(Color.WHITE);
    	g.drawLine(0, FIRST_BUTTON_Y-70, 300, FIRST_BUTTON_Y-70 );
    	
    	g.setColor(Color.WHITE);
    	g.drawLine(0, FIRST_BUTTON_Y+20, 300, FIRST_BUTTON_Y+20 );
    	     	
    	g.setColor(Color.WHITE);
    	g.drawLine(0, FIRST_BUTTON_Y+110, 300, FIRST_BUTTON_Y+110 );
    	
    	g.setColor(Color.WHITE);
    	g.drawLine(0, FIRST_BUTTON_Y+200, 300, FIRST_BUTTON_Y+200 );
    	
    	
	}
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 1000);
    }
	
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        
        doDrawing(g);
    }

}