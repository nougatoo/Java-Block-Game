import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


public class Driver {


	 public static void main(String[] args) throws IOException {

		int choice = -1;
		WelcomeLogic welcome_screen;
		Game game = new Game(1);
		Utilities util;
		
		util = new Utilities(); //For non-game functions
		
		
		 /* Creates a welcome screen and waits until the user has made a choice */
		 
		welcome_screen = new WelcomeLogic();
		welcome_screen.createWelcomeScreen();
		choice = welcome_screen.waitForChoice();
		welcome_screen.tearDown();
		
		if(choice == 0) //Create new game
		{
			game = new Game(choice); //New game
			game.initialize(); //Initialize all game variables and data
			

		}
		else
		{
			FileInputStream fin = new FileInputStream("savedGame3.txt");
			ObjectInputStream ois = new ObjectInputStream(fin);
			try {
				game = (Game) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			fin.close();
			ois.close();
			
			/*
			 * Sets up some extra stuff because we are not starting a new game
			 */
			game.loadedGame();
		}
		
		
		
		game.create_frame(); //
		game.create_main_panel();
		
		for(int i=0;i<1000000;i++)
		{

			util.waitMili(80);	
			game.showStats();
				
		}

	 }
}


