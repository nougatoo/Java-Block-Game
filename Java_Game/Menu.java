import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public abstract class Menu {
	
	protected Utilities util = new Utilities();
	protected MainListener main_listener = new MainListener();
	
	protected int menu_choice = -1;
	protected volatile boolean choice_made = false;
	
	
	protected JFrame upgrade_menu_frame;
	protected JScrollPane scroll;
	protected BoostPanel upgrade_menu_panel;
	
	protected JButton upgrade0_button;
	protected JLabel upgrade0_label;
	protected JLabel upgrade0_cost_label;
	
	protected JButton upgrade1_button;
	protected JLabel upgrade1_label;
	protected JLabel upgrade1_cost_label;
	
	protected JButton upgrade2_button;
	protected JLabel upgrade2_label;
	protected JLabel upgrade2_cost_label;
	
	protected Font description_font = new Font("Ariel", Font.PLAIN, 11);
	
	protected int FIRST_BUTTON_X = 200;
	protected int FIRST_BUTTON_Y = 100;
	
	protected final int WIDTH = 325;
	protected final int HEIGHT = 500;
	
	public boolean createMenu()
	{
        upgrade_menu_frame = new JFrame("Upgrade Menu");
		upgrade_menu_frame.setSize(WIDTH, HEIGHT);
	    upgrade_menu_frame.setResizable(false);
		upgrade_menu_frame.setLocation(100, 60);
		upgrade_menu_frame.setVisible(true);
		
		//Creates a panel
		upgrade_menu_panel = new BoostPanel();
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
	abstract void create_buttons();
	abstract  void create_labels();
	
	public int getChoice()
	{		
		
		return menu_choice;
	}
	
}
