import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

public class MainListener implements ActionListener, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7420046919359955467L;
	private WelcomeLogic welcome_screen;
	private static Game game; // game is static so that upgrade menu's listener and game's listener use the same game
	private Utilities util;
	
	public MainListener(Game g)
	{
		game = g;
	}
	
	public MainListener()
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int button = -1;
		int block = -1;
		//Could have a method for each button that handles each case and this method will just send the program to that place
		if("lemon".equals(e.getActionCommand()))
		{
			button = 1;
			game.handleFruitPress(button);
		}
		else if("orange".equals(e.getActionCommand())) //add else
		{
			button = 2;
			game.handleFruitPress(button);
		}
		else if("apple".equals(e.getActionCommand()))
		{
			button = 3;
			game.handleFruitPress(button);
		}
		else if("grape".equals(e.getActionCommand()))
		{
			button = 4;
			game.handleFruitPress(button);
		}
		else if("blueberry".equals(e.getActionCommand()))
		{
			button = 5;
			game.handleFruitPress(button);
		}
		else if("upgradeYellow".equals(e.getActionCommand()))
		{
			block = 0;
			game.upgradeBlocks(block);
			
		}
		else if("upgradeOrange".equals(e.getActionCommand()))
		{
			block = 1;
			game.upgradeBlocks(block);
			
		}
		else if("upgradeRed".equals(e.getActionCommand()))
		{
			block = 2;
			game.upgradeBlocks(block);
			
		}
		else if("upgradePurple".equals(e.getActionCommand()))
		{
			block = 3;
			game.upgradeBlocks(block);
			
		}
		else if("boost0_chosen".equals(e.getActionCommand()))
		{
			game.handleBoostMenuChoice(0);
			
		}
		else if("boost1_chosen".equals(e.getActionCommand()))
		{
			game.handleBoostMenuChoice(1);
			
		}
		else if("boost2_chosen".equals(e.getActionCommand()))
		{
			game.handleBoostMenuChoice(2);
			
		}
		else if("upgrade0_chosen".equals(e.getActionCommand()))
		{
			game.handleUpgradeMenuChoice(0);
			
		}
		else if("upgrade1_chosen".equals(e.getActionCommand()))
		{
			game.handleUpgradeMenuChoice(1);
			
		}
		else if("create_boost_menu".equals(e.getActionCommand()))
		{
        	game.createBoostMenu();
			
		}
		else if("create_upgrade_menu".equals(e.getActionCommand()))
		{
        	game.createUpgradeMenu();
			
		}
		else if("save_game".equals(e.getActionCommand()))
		{
        	try {
				game.saveGame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if("show_stats".equals(e.getActionCommand()))
		{
        	game.createStatsWindow();
			
		}
		
		

	}

}
