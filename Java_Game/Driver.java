
public class Driver {


	 public static void main(String[] args) {

		int choice = -1;
		WelcomeLogic welcome_screen;
		Game game;
		Utilities util;
		
		util = new Utilities(); //For non-game functions
		
		
		 /* Creates a welcome screen and waits until the user has made a choice */
		 
		//welcome_screen = new WelcomeLogic();
		//welcome_screen.createWelcomeScreen();
		//choice = welcome_screen.waitForChoice();
		//welcome_screen.tearDown();
		
		
		
		game = new Game(choice); //New game
		game.initialize(); //Initialize all game variables and data
		game.create_frame(); //
		game.create_main_panel();
		
		for(int i=0;i<100000;i++)
		{

			util.waitMili(122);	
			game.showStats();
				
		}
	 }
}


