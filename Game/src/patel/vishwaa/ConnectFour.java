package patel.vishwaa;

import javax.swing.*;

import java.awt.*;

public class ConnectFour {
	

	public static void main(String[] args) {
		JFrame frame = new JFrame("Connect Four");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Board());
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

	}

}
