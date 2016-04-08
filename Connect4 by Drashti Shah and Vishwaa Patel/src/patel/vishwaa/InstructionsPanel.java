package patel.vishwaa;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;

/**
 * @author Vishwaa Patel & Drashti Shah
 * @version 1.0
 * @since 2016-04-08
 * 
 *        Instructions panel shows the instructions screen of Connect 4 game
 *        It reads the instructions image and sets it as the icon of the JLabel instructions
 */
public class InstructionsPanel extends JPanel {
	
	// initializes variables
	private JLabel instructions;
	private BufferedImage instructionsImg;

	/**
	 * Initializes the panel by setting background and size of it 
	 * Also reads an instructions image and sets it as the icon of JLabel instructions
	 */
	public InstructionsPanel() {
		// features of the panel
		setBackground(new Color(0,0,0));
		setPreferredSize(new Dimension(500, 500));

		// creates the JLabel
		instructions = new JLabel();

		// Reads images to be used as labels in program or pops an exception if
		// the file is not found
		try {
			instructionsImg = ImageIO.read(new File("instructions.png"));
			instructions.setIcon(new ImageIcon(instructionsImg));
		}

		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "File not Found");
		}
		// adds the instructions label to the instructions panel
		add(instructions);

	}

}
