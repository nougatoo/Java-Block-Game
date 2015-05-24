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


public class BoostMenu extends JPanel{
	
	private Utilities util = new Utilities();
	private MainListener main_listener = new MainListener();
	
	private int uMenu_choice = -1;
	private volatile boolean choice_made = false;
	
	
	private JScrollPane scroll;
	private BoostPanel boost_menu_panel;
	private JFrame boost_menu_frame;
	
	private JButton boost0_button;
	private JLabel boost0_label;
	private JLabel boost0_cost_label;
	
	private JButton boost1_button;
	private JLabel boost1_label;
	private JLabel boost1_cost_label;
	
	private JButton boost2_button;
	private JLabel boost2_label;
	private JLabel boost2_cost_label;
	
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
	
	
	
	
	public BoostMenu()
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
        boost_menu_frame = new JFrame("boost Menu");
		boost_menu_frame.setSize(WIDTH, HEIGHT);
	    boost_menu_frame.setResizable(false);
		boost_menu_frame.setLocation(100, 60);
		boost_menu_frame.setVisible(true);
		
		//Creates a panel
		boost_menu_panel = new BoostPanel();
		boost_menu_panel.setLayout(null);
		
		//Creates a scroll pane with the panel that was just created
        scroll = new JScrollPane(boost_menu_panel);
        scroll.setBackground(Color.BLACK);
        
        //Increase scroll speed
        scroll.getVerticalScrollBar().setUnitIncrement(16);
	

		create_buttons();
		create_labels();
		
		//Add scroll pane (thats made up of upgardePanel) to our upgrade frame
		boost_menu_frame.add(scroll);
		
		
		return true;

		
		
	}
	
	public void create_buttons()
	{
		boost0_button = new JButton();
		boost_menu_panel.add(boost0_button);
	    boost0_button.setBounds(FIRST_BUTTON_X, FIRST_BUTTON_Y-50,80,20);
	    boost0_button.setText("Buy it!");
	    boost0_button.addActionListener(main_listener);
	    boost0_button.setBackground(Color.GRAY.brighter());
	    boost0_button.setForeground(Color.BLACK);
	    boost0_button.setActionCommand("boost0_chosen");
	    
		boost1_button = new JButton();
		boost_menu_panel.add(boost1_button);
	    boost1_button.setBounds(FIRST_BUTTON_X, FIRST_BUTTON_Y+40,80,20);
	    boost1_button.setText("Buy it!");
	    boost1_button.addActionListener(main_listener);
	    boost1_button.setBackground(Color.GRAY.brighter());
	    boost1_button.setForeground(Color.BLACK);
	    boost1_button.setActionCommand("boost1_chosen");
	    
		boost2_button = new JButton();
		boost_menu_panel.add(boost2_button);
	    boost2_button.setBounds(FIRST_BUTTON_X, FIRST_BUTTON_Y+130,80,20);
	    boost2_button.setText("Buy it!");
	    boost2_button.addActionListener(main_listener);
	    boost2_button.setBackground(Color.GRAY.brighter());
	    boost2_button.setForeground(Color.BLACK);
	    boost2_button.setActionCommand("boost2_chosen");

	}
	
	public void create_labels()
	{
		/*
		 * Upgrade 0 (yellow blocks)
		 */
		boost0_label = new JLabel();
		boost_menu_panel.add(boost0_label);
		boost0_label.setText("<html><center>Increase all production by x2 for 3 seconds!</center></html>");
		boost0_label.setForeground(Color.WHITE);
		boost0_label.setFont(description_font);
        boost0_label.setSize(150,40);
        boost0_label.setLocation(25, FIRST_BUTTON_Y-60);
        
		boost0_cost_label = new JLabel();
		boost_menu_panel.add(boost0_cost_label);
		boost0_cost_label.setText("<html>Cost: x250</html>");
		boost0_cost_label.setForeground(Color.WHITE);
		boost0_cost_label.setFont(description_font);
        boost0_cost_label.setSize(60, 50);
        boost0_cost_label.setLocation(25, FIRST_BUTTON_Y-24);
        
        
        /*
         * Upgrade 1 (orange blocks)
         */
		boost1_label = new JLabel();
		boost_menu_panel.add(boost1_label);
		boost1_label.setText("<html><center>Increase all production by x4 for 5 seconds!</center></html>");
		boost1_label.setForeground(Color.WHITE);
		boost1_label.setFont(description_font);
        boost1_label.setSize(150,40);
        boost1_label.setLocation(25, FIRST_BUTTON_Y+30);
        
		boost1_cost_label = new JLabel();
		boost_menu_panel.add(boost1_cost_label);
		boost1_cost_label.setText("<html>Cost: x125</html>");
		boost1_cost_label.setForeground(Color.WHITE);
		boost1_cost_label.setFont(description_font);
        boost1_cost_label.setSize(60, 50);
        boost1_cost_label.setLocation(25, FIRST_BUTTON_Y+64);
        
        /*
         * boost 2 (red blocks)
         */
		boost1_label = new JLabel();
		boost_menu_panel.add(boost1_label);
		boost1_label.setText("<html><center>Increase all production by x8 for 7 seconds!</center></html>");
		boost1_label.setForeground(Color.WHITE);
		boost1_label.setFont(description_font);
        boost1_label.setSize(150,40);
        boost1_label.setLocation(25, FIRST_BUTTON_Y+120);
        
		boost1_cost_label = new JLabel();
		boost_menu_panel.add(boost1_cost_label);
		boost1_cost_label.setText("<html>Cost: x60</html>");
		boost1_cost_label.setForeground(Color.WHITE);
		boost1_cost_label.setFont(description_font);
        boost1_cost_label.setSize(60, 50);
        boost1_cost_label.setLocation(25, FIRST_BUTTON_Y+152);
        
        
     
        
	}
	
	public void tearDown()
	{
		boost_menu_frame.remove(scroll);
		boost_menu_frame.repaint();
		boost_menu_frame.setVisible(false);
	}





}


