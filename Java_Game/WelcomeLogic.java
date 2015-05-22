import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class WelcomeLogic implements ActionListener{
	
	JFrame frame;
	WelcomeScreen welcome_screen;
	
	private JButton new_game_button, load_game_button;
	private JLabel welcome_message;
	
	private int choice = 0;
	private volatile boolean choice_made = false;
	
	
	Utilities util = new Utilities();

	public WelcomeLogic()
	{
		/*
		 * Sets up the frame
		 */
		frame = new JFrame("Test Box");
		frame.pack();
		frame.setSize(800, 600);
	    frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void createWelcomeScreen()
	{
		/*
		 * New welcome screen panel 
		 */
		welcome_screen = new WelcomeScreen();
		welcome_screen.setLayout(null);
		welcome_screen.setBackground(Color.GRAY);	
		frame.add(welcome_screen);
		
		//Draw buttons
		drawWelcomeButtons();
		
		//Lets user see verything
		frame.setVisible(true);
		
		
	}
	
	public void drawWelcomeButtons()
	{
	    new_game_button = new JButton(); 
	    welcome_screen.add(new_game_button);
	    new_game_button.setBounds(250, 440, 100, 25);
	    new_game_button.setVisible(true);
	    new_game_button.setText("New Game");
	    new_game_button.addActionListener(this);
	    new_game_button.setActionCommand("new_game");
	    
	    load_game_button = new JButton(); 
	    welcome_screen.add(load_game_button);
	    load_game_button.setBounds(375, 440, 100, 25);
	    load_game_button.setVisible(true);
	    load_game_button.setText("Load Game");
	    load_game_button.addActionListener(this);
	    load_game_button.setActionCommand("load_game");
	}
	
	/*
	 * This method will wait for the user to pick what they want to do
	 * from the welcome screen (start a new game, load game...etc)
	 */
	public int waitForChoice()
	{
		//Infinite loop that waits for the user to make a choice
		while(choice_made == false){
		    try {
		       Thread.sleep(200);
		    } catch(InterruptedException e) {
		    }
		}

		return choice;
	}

	public void tearDown()
	{
		/*
		 * A choice has finally been made, so we tear down the welcome screen panel
		 * and set frame to not visible so we can set it up for the main game
		 */
		frame.remove(welcome_screen);
		frame.repaint();
		frame.setVisible(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if("new_game".equals(e.getActionCommand()))
		{
			choice = 0;
			choice_made = true;
		}
		if("load_game".equals(e.getActionCommand()))
		{
			choice = 1;
			choice_made = true;
		}
	}
}
