import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class UpgradeMenu extends JPanel{
	
	private Utilities util = new Utilities();
	private MainListener main_listener = new MainListener();
	
	private int uMenu_choice = -1;
	private volatile boolean choice_made = false;
	
	
	private JFrame upgrade_menu_frame;
	private JScrollPane scroll;
	private UpgradePanel upgrade_menu_panel;
	
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
		upgrade_menu_frame.setLocation(100, 60);
		upgrade_menu_frame.setVisible(true);
		
		//Creates a panel
		upgrade_menu_panel = new UpgradePanel();
		upgrade_menu_panel.setLayout(null);
		
		//Creates a scroll pane with the panel that was just created
        scroll = new JScrollPane(upgrade_menu_panel);
        scroll.setBackground(Color.BLACK);
        
        //Increase scroll speed
        scroll.getVerticalScrollBar().setUnitIncrement(16);
	

		create_buttons();
		create_labels();
		
		//Add scroll pane (thats made up of upgardePanel) to our upgrade frame
		upgrade_menu_frame.add(scroll);
		
		
		return true;

		
		
	}
	
	public void create_buttons()
	{
		upgrade0_button = new JButton();
		upgrade_menu_panel.add(upgrade0_button);
	    upgrade0_button.setBounds(FIRST_BUTTON_X, FIRST_BUTTON_Y-50,80,20);
	    upgrade0_button.setText("Buy it!");
	    upgrade0_button.addActionListener(main_listener);
	    upgrade0_button.setBackground(Color.GRAY.brighter());
	    upgrade0_button.setForeground(Color.BLACK);
	    upgrade0_button.setActionCommand("upgrade0_chosen");
	    
		upgrade1_button = new JButton();
		upgrade_menu_panel.add(upgrade1_button);
	    upgrade1_button.setBounds(FIRST_BUTTON_X, FIRST_BUTTON_Y+40,80,20);
	    upgrade1_button.setText("Buy it!");
	    upgrade1_button.addActionListener(main_listener);
	    upgrade1_button.setBackground(Color.GRAY.brighter());
	    upgrade1_button.setForeground(Color.BLACK);
	    upgrade1_button.setActionCommand("upgrade1_chosen");
	    
	    /*
		upgrade2_button = new JButton();
		upgrade_menu_panel.add(upgrade2_button);
	    upgrade2_button.setBounds(FIRST_BUTTON_X, FIRST_BUTTON_Y+130,80,20);
	    upgrade2_button.setText("Buy it!");
	    upgrade2_button.addActionListener(main_listener);
	    upgrade2_button.setBackground(Color.GRAY.brighter());
	    upgrade2_button.setForeground(Color.BLACK);
	    upgrade2_button.setActionCommand("upgrade2_chosen");
		*/
	}
	
	public void create_labels()
	{
		/*
		 * Upgrade 0 (Purple)
		 */
		upgrade0_label = new JLabel();
		upgrade_menu_panel.add(upgrade0_label);
		upgrade0_label.setText("<html><center>Permanently multiply production by x3!</center></html>");
		upgrade0_label.setForeground(Color.WHITE);
		upgrade0_label.setFont(description_font);
        upgrade0_label.setSize(150,40);
        upgrade0_label.setLocation(25, FIRST_BUTTON_Y-60);
        
		upgrade0_cost_label = new JLabel();
		upgrade_menu_panel.add(upgrade0_cost_label);
		upgrade0_cost_label.setText("<html>Cost: x80</html>");
		upgrade0_cost_label.setForeground(Color.WHITE);
		upgrade0_cost_label.setFont(description_font);
        upgrade0_cost_label.setSize(60, 50);
        upgrade0_cost_label.setLocation(25, FIRST_BUTTON_Y-24);
        
        
        /*
         * Upgrade 1 (blue)
         */
		upgrade1_label = new JLabel();
		upgrade_menu_panel.add(upgrade1_label);
		upgrade1_label.setText("<html><center>Permanently multiply production by x5!</center></html>");
		upgrade1_label.setForeground(Color.WHITE);
		upgrade1_label.setFont(description_font);
        upgrade1_label.setSize(150,40);
        upgrade1_label.setLocation(25, FIRST_BUTTON_Y+30);
        
		upgrade1_cost_label = new JLabel();
		upgrade_menu_panel.add(upgrade1_cost_label);
		upgrade1_cost_label.setText("<html>Cost: x50</html>");
		upgrade1_cost_label.setForeground(Color.WHITE);
		upgrade1_cost_label.setFont(description_font);
        upgrade1_cost_label.setSize(60, 50);
        upgrade1_cost_label.setLocation(25, FIRST_BUTTON_Y+64);
        
        /*
         * Upgrade 2 (red blocks)
         
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
        
        */
     
        
	}
	
	public void tearDown()
	{
		upgrade_menu_frame.remove(scroll);
		upgrade_menu_frame.repaint();
		upgrade_menu_frame.setVisible(false);
	}


    



}


