import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.math.*;
import java.util.Random;


public class Game implements Runnable{
   
   final int WIDTH = 1000;
   final int HEIGHT = 700;
   
   JFrame frame;
   Canvas canvas;
   BufferStrategy bufferStrategy;
   private Rectangle rect;
   boolean[][] grid;
   int num_yellow;
   int num_green;
   int num_red;
   
   
   public Game(){
      frame = new JFrame("Basic Game");
      
      /*
      JButton b1 = new JButton();
      b1.setSize(200,50);
      b1.setVisible(true);
      b1.setText("HelloWorld");
      frame.add(b1);
      */
      
      JPanel panel = (JPanel) frame.getContentPane();
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      panel.setLayout(null);
      
      canvas = new Canvas();
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
      
      panel.add(canvas);
      
      canvas.addMouseListener(new MouseControl());
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setResizable(false);
      frame.setVisible(true);
      
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      
      canvas.requestFocus();
      
      //////////
      rect = new Rectangle(300,300,300,300);
      
      grid = new boolean[20][20];
      
      for(int i =0;i<20;i++)
      {
     	 for(int j = 0;j<20;j++)
     	 {
     		 grid[i][j] = false;
     	 }
     	 
     	 
      }
      
      num_yellow = 0;
      num_green = 0;
      num_red = 0;

   }
   
        
   private class MouseControl extends MouseAdapter{
      
   }
   
   long desiredFPS = 60;
    long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
    
   boolean running = true;
   
   public void run(){
      
      long beginLoopTime;
      long endLoopTime;
      long currentUpdateTime = System.nanoTime();
      long lastUpdateTime;
      long deltaLoop;
      
      while(running){
         beginLoopTime = System.nanoTime();
         
         render();
         
         lastUpdateTime = currentUpdateTime;
         currentUpdateTime = System.nanoTime();
         update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));
         
         endLoopTime = System.nanoTime();
         deltaLoop = endLoopTime - beginLoopTime;
           
           if(deltaLoop > desiredDeltaLoop){
               //Do nothing. We are already late.
           }else{
               try{
                   Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
               }catch(InterruptedException e){
                   //Do nothing
               }
           }
      }
   }
   
   private void render() {
      Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
     
      
      render(g);
      g.dispose();
      bufferStrategy.show();
   }
   
   //TESTING
   private double x = 0;
   
   /**
    * Rewrite this method for your game
    */
   protected void update(int deltaTime){
	   
	  /*
      x += deltaTime * 0.1;
      while(x > 500){
         x -= 500;
      }
      */
      
   }
   
   /**
    * Rewrite this method for your game
    */
   protected void render(Graphics2D g){
	   
	 
      //g.fillRect((int)x, 0, 200, 200);
	   
      Color red = new Color(255, 0, 0);
      Color green = new Color(0, 255, 0);
      Color blue = new Color(0, 0, 255);
      g.setColor(green);
      
      //g.fill(rect);
      Color temp;
      
      int i,j=0;    
      Random rand = new Random();
      // nextInt is normally exclusive of the top value,
      // so add 1 to make it inclusive
      int randomNum = rand.nextInt((19 - 0) + 1) + 0;    
      i = randomNum;
      randomNum = rand.nextInt((19 - 0) + 1) + 0;
      j = randomNum;
      
      //Gets a random place on the grid, fills in a red, and updates grid
      if(grid[i][j] == false)
      {
    	  g.setColor(red);
          g.fillRect(i*20, j*20, 20, 20);
          grid[i][j] = true;
    	  
      }
    		
      
      randomNum = rand.nextInt((19 - 0) + 1) + 0;    
      i = randomNum;
      randomNum = rand.nextInt((19 - 0) + 1) + 0;
      j = randomNum;
      
      System.out.println("hgell");
      if(grid[i][j] == false)
      {
    	  g.setColor(green);
          g.fillRect(i*20, j*20, 20, 20);
          grid[i][j] = true;
    	  
      }
      
      randomNum = rand.nextInt((19 - 0) + 1) + 0;    
      i = randomNum;
      randomNum = rand.nextInt((19 - 0) + 1) + 0;
      j = randomNum;
      
      if(grid[i][j] == false)
      {
    	  g.setColor(blue);
          g.fillRect(i*20, j*20, 20, 20);
          grid[i][j] = true;
    	  
      }
      
      
      
      
      //g.setColor(red);
      //g.fillRect(i*20, j, 20, 20);
    
      //Remove this to get the program to keep rendering
      //running = false;
      
      //Generate a grid
      /*
      for(i = 0;i<20;i+=1)
      {
    	  for(j=0;j<20;j+=2)
    	  {
    		  g.setColor(red);
              g.fillRect(i*20, j*20, 20, 20);
              
              g.setColor(green);
              g.fillRect(i*20, (j+1)*20, 20, 20);
                
    	  }
    	  
    	  temp = red;
    	  red = green;
    	  green = temp;
    	
           
      }
      */
      
   }
   
   public static void main(String [] args){
      Game ex = new Game();
      new Thread(ex).start();
   }

}