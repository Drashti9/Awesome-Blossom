package patel.vishwaa;

import javax.swing.*;

/**
 * Connect 4 Driver which contains the main frame which contains the Start screen Panel
 * @author Vishwaa Patel & Drashti Shah
 * @version 1.0
 * @since 2016-04-08
 */

public class Connect4Driver {

	public static void main(String[] args) {
		
		//creates a new frame
		JFrame frame = new JFrame("Connect 4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// adds the start screen panel to the frame
		frame.getContentPane().add(new StartScreenPanel());
		//packs the frame
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

	}

}
   