package Old_Files;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class ButtonMethods implements ActionListener {
	
	
	JButton b1 = new JButton();
	
	//Takes the frame and add a button to it. returns button
	public JButton create_button(JFrame frame){
		
		
	      frame.add(b1);
	      b1.setSize(100,30);
	      b1.setVisible(true);
	      b1.setText("HelloWorld");
	      frame.add(b1);
	      
	      b1.addActionListener(this);
	      
	      return b1;
	}
	
	public void actionPerformed(ActionEvent e) {
	  b1.setText("asdf");

	}
	
}
