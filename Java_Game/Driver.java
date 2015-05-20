/*
package Main;

/*
 * Ideas: 
 * 		- some game with "pixels" that you generate
 * 		  and different colors mean more money and when
 * 		  the screen gets full the colors start to change to
 * 		  show that they are worth more 
 * 		  This will still have the incremental components like this game
 * 	
 */
public class Driver {


	 public static void main(String[] args) {
		 
			Game gameState = new Game(); //New game
		Utilities util = new Utilities(); //For non-game functions
		gameState.initialize();

		gameState.draw();	
		gameState.drawLabels();
		
		for(int i=0;i<100000;i++)
		{

			util.waitMili(123);	
			gameState.showStats();
				
		}
	 }
}


