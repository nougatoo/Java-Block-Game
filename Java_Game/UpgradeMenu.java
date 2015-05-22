import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UpgradeMenu extends JPanel{
	
	private Utilities util = new Utilities();
	private MainListener main_listener = new MainListener();
	
	private int uMenu_choice = -1;
	private volatile boolean choice_made = false;
	
	private JFrame upgrade_menu_frame;
	
	private JPanel upgrade_menu_panel;
	
	private JButton upgrade0_button;
	private JLabel upgrade0_label;
	private JLabel upgrade0_cost_label;
	
	private JButton upgrade1_button;
	private JLabel upgrade1_label;
	private JLabel upgrade1_cost_label;
	
	private JButton upgrade2_button;
	private JLabel upgrade2_label;
	private JLabel upgrade2_cost_label;
	
	private Font description_font = new Font("Ariel", Font.PLAIN, 11);
	
	private int FIRST_BUTTON_X = 200;
	private int FIRST_BUTTON_Y = 100;
	
	private final int WIDTH = 300;
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
	
	
	
	
	public UpgradeMenu()
	{
		//createMenu();
	}
	
	/*
	 * @input: none
	 * Return: int, that indicates which upgrade the user wants
	 */
	public int getChoice()
	{		
		
		return uMenu_choice;
	}
	
	
	public boolean createMenu()
	{
        upgrade_menu_frame = new JFrame("Upgrade Menu");
		upgrade_menu_frame.setSize(WIDTH, HEIGHT);
	    upgrade_menu_frame.setResizable(false);
		//upgrade_menu_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		upgrade_menu_frame.setLocation(100, 60);
		
		upgrade_menu_frame.setVisible(true);
		

		
		upgrade_menu_panel = new UpgradeMenuDrawing();
		upgrade_menu_panel.setLayout(null);
		upgrade_menu_panel.setBackground(Color.BLACK);
		upgrade_menu_panel.setVisible(true);
		upgrade_menu_frame.add(upgrade_menu_panel);

		

		create_buttons();
		create_labels();
		
		
		//Set visible at the very end
		return true;

		
		
	}
	
	public void create_buttons()
	{
		upgrade0_button = new JButton();
		upgrade_menu_panel.add(upgrade0_button);
	    upgrade0_button.setBounds(FIRST_BUTTON_X, FIRST_BUTTON_Y-50,80,20);
	    upgrade0_button.setText("Buy it!");
	    upgrade0_button.addActionListener(main_listener);
	    upgrade0_button.setActionCommand("upgrade0_chosen");
	    
		upgrade1_button = new JButton();
		upgrade_menu_panel.add(upgrade1_button);
	    upgrade1_button.setBounds(FIRST_BUTTON_X, FIRST_BUTTON_Y+40,80,20);
	    upgrade1_button.setText("Buy it!");
	    upgrade1_button.addActionListener(main_listener);
	    upgrade1_button.setActionCommand("upgrade1_chosen");
	    
		upgrade2_button = new JButton();
		upgrade_menu_panel.add(upgrade2_button);
	    upgrade2_button.setBounds(FIRST_BUTTON_X, FIRST_BUTTON_Y+130,80,20);
	    upgrade2_button.setText("Buy it!");
	    upgrade2_button.addActionListener(main_listener);
	    upgrade2_button.setActionCommand("upgrade2_chosen");

	}
	
	public void create_labels()
	{
		/*
		 * Upgrade 0 (yellow blocks)
		 */
		upgrade0_label = new JLabel();
		upgrade_menu_panel.add(upgrade0_label);
		upgrade0_label.setText("<html><center>Increase all production by x2 for 3 seconds!</center></html>");
		upgrade0_label.setForeground(Color.WHITE);
		upgrade0_label.setFont(description_font);
        upgrade0_label.setSize(150,40);
        upgrade0_label.setLocation(25, FIRST_BUTTON_Y-60);
        
		upgrade0_cost_label = new JLabel();
		upgrade_menu_panel.add(upgrade0_cost_label);
		upgrade0_cost_label.setText("<html>Cost: x250</html>");
		upgrade0_cost_label.setForeground(Color.WHITE);
		upgrade0_cost_label.setFont(description_font);
        upgrade0_cost_label.setSize(60, 50);
        upgrade0_cost_label.setLocation(25, FIRST_BUTTON_Y-24);
        
        
        /*
         * Upgrade 1 (orange blocks)
         */
		upgrade1_label = new JLabel();
		upgrade_menu_panel.add(upgrade1_label);
		upgrade1_label.setText("<html><center>Increase all production by x4 for 5 seconds!</center></html>");
		upgrade1_label.setForeground(Color.WHITE);
		upgrade1_label.setFont(description_font);
        upgrade1_label.setSize(150,40);
        upgrade1_label.setLocation(25, FIRST_BUTTON_Y+30);
        
		upgrade1_cost_label = new JLabel();
		upgrade_menu_panel.add(upgrade1_cost_label);
		upgrade1_cost_label.setText("<html>Cost: x125</html>");
		upgrade1_cost_label.setForeground(Color.WHITE);
		upgrade1_cost_label.setFont(description_font);
        upgrade1_cost_label.setSize(60, 50);
        upgrade1_cost_label.setLocation(25, FIRST_BUTTON_Y+64);
        
        /*
         * Upgrade 2 (red blocks)
         */
		upgrade1_label = new JLabel();
		upgrade_menu_panel.add(upgrade1_label);
		upgrade1_label.setText("<html><center>Increase all production by x8 for 7 seconds!</center></html>");
		upgrade1_label.setForeground(Color.WHITE);
		upgrade1_label.setFont(description_font);
        upgrade1_label.setSize(150,40);
        upgrade1_label.setLocation(25, FIRST_BUTTON_Y+120);
        
		upgrade1_cost_label = new JLabel();
		upgrade_menu_panel.add(upgrade1_cost_label);
		upgrade1_cost_label.setText("<html>Cost: x60</html>");
		upgrade1_cost_label.setForeground(Color.WHITE);
		upgrade1_cost_label.setFont(description_font);
        upgrade1_cost_label.setSize(60, 50);
        upgrade1_cost_label.setLocation(25, FIRST_BUTTON_Y+152);
        
        
     
        
	}
	
	public void tearDown()
	{
		upgrade_menu_frame.remove(upgrade_menu_panel);
		upgrade_menu_frame.repaint();
		upgrade_menu_frame.setVisible(false);
	}


	


	/*
	 * Drawing nested class that draws the small blocks to show costs
	 */
    class UpgradeMenuDrawing extends JPanel{
    	
    
    	
    	private void doDrawing(Graphics g) {
    		
    		g.setColor(Color.WHITE);
    		g.drawString("Upgrades", 120, 18);
    		
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
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            
            doDrawing(g);
        }
    	

    }




}


