package patel.vishwaa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

/** 
 * Start Screen Panel outputs the JButtons(different player mode) and the logo of Connect 4 to the user which is the menu
 * screen which gives the option for different player mode and instructions
 * upon clicking Instructions, Single Player or Two Player,a new JFrame is creates a new frame when the user 
 * 
 * @author Vishwaa Patel & Drashti Shah
 * @version 1.0
 * @since 2016-04-08
 *
 */
public class StartScreenPanel extends JPanel{
	
	// initializes all the GUI components
	private JLabel name;
	private JButton SinglePlayer;
	private JButton TwoPlayer;
	private JButton Instructions;	
	private BufferedImage logo = null;

	/**
	 * Initializes panel by setting background and layout of the panel's 
	 * GUI components like buttons and JLabel and sets the size of JButtons
	 * Also reads images and sets it as the icon of the JLabel for the logo of the game
	 */
	public StartScreenPanel(){
		
		// features of the panel
		setBackground(new Color (0,50,100));
		setPreferredSize(new Dimension(500, 500));		
		
		// creates all the JButtons and JLabels
        Instructions = new JButton("Instructions");		
		SinglePlayer = new JButton("Single Player");
		TwoPlayer = new JButton("Two Player");	
		name = new JLabel();
		
		// Reads images to be used as labels in program or pops an exception if the file is not found
		try{
			logo = ImageIO.read( new File ("connect-4-logo.png"));	
			name.setIcon(new ImageIcon(logo));
		}		
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "File not Found");
		}
		
		// sets the size of the buttons
		SinglePlayer.setPreferredSize(new Dimension(150, 50));		
		TwoPlayer.setPreferredSize(new Dimension(150, 50));
		Instructions.setPreferredSize(new Dimension(150,50));
		
		// adds all the buttons to the action Listener
		SinglePlayer.addActionListener(new ClassListener());
		TwoPlayer.addActionListener(new ClassListener());
		Instructions.addActionListener(new ClassListener());
		
		// Adds buttons to panel
		add(name);
		add(SinglePlayer);
		add(TwoPlayer);
		add(Instructions);
						
		// sets the visiblity of the JButtons and JLabel
		SinglePlayer.setVisible(true);
		TwoPlayer.setVisible(true);
		Instructions.setVisible(true);
			
	}		
	
	/**
	 *	Defines the response of the buttons created in the constructor
	 *  uses the method event.getSource() to differentiate between the different buttons clicked		  
	 *   
	 */
	private class ClassListener implements ActionListener {
		/**
		 *
		 * Handles the response of each button clicked
		 * @param event
		 * 			Action Event object tracks which button is clicked
		 */
		public void actionPerformed(ActionEvent event) {
			
			// if Single Player button is clicked, then opens a new frame with board panel
			if (event.getSource() == SinglePlayer) {
					JFrame frame = new JFrame("Board");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().add(new BoardPanel("SinglePlayer"));
					frame.pack();
					frame.setResizable(false);
					frame.setVisible(true);
					
				}
			//  if Two Player button is clicked, then opens a new frame with Instructions panel 
				else if (event.getSource() == Instructions) {
					JFrame frame = new JFrame("Connect Four");
					frame.getContentPane().add(new InstructionsPanel());
					frame.pack();
					frame.setResizable(false);
					frame.setVisible(true);
				}
				// if Two Player button is clicked, then opens a new frame with board panel
				else if(event.getSource() == TwoPlayer){
					
					JFrame frame = new JFrame("Board");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().add(new BoardPanel("TwoPlayer"));
					frame.pack();
					frame.setResizable(false);
					frame.setVisible(true);
										
				}
			}
		}		
	}