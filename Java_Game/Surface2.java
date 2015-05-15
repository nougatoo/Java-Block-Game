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
	
    private void doDrawing(Graphics g) {

    	g.setColor(Color.BLUE);
    	g.fillRect(0, 0, 10, 10);
    	
        int i,j=0;    
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((19 - 0) + 1) + 0;    
        i = randomNum;
        randomNum = rand.nextInt((19 - 0) + 1) + 0;
        j = randomNum;
    	
  	  	g.setColor(Color.RED);
  	  	g.fillRect(i*20, j*20, 20, 20);
    	
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Java 2D", 50, 50);
        
        g.setColor(Color.WHITE);
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
}