import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class WelcomeScreen extends JPanel{
	
	
	private Font welcome_font = new Font("Calibri", Font.BOLD, 32);
	
	public WelcomeScreen()
	{
	
	}
	
	private void doDrawing(Graphics g) {
		
		g.setFont(welcome_font);
		g.drawString("Welcome to my Java Block Game" , 175, 50);
	}
	
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        
        doDrawing(g);
    }
	

}