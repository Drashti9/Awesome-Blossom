package patel.vishwaa;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

/** 
 * Board Panel outputs the board of the game
 * contains the JLabels which makes up the grid of connect 4 and also outputs buttons on top of the grid which are clicked to drop a token in that column
 * If the location is empty or filled, it is determined by different images (empty image for empty location 
 * and colored image for the player's token in the location)
 * As the buttons are clicked, if the column is full, then it shows a error message and if is not then it drops an token in the least empty row location in that column 
 * Switches the player after every move and updates the cell 2d array and also JLabels 2d array
 * If the player mode is single player, then after each move it picks an appropriate column randomly and places a computer's token 
 * at the least available row location of that column 
 * checks if the player gets a connect 4 in a line horizontally, vertically, and diagonally by calling the methods in the board class
 * pops up a win message and the time it took to complete the task upon wining the game
 * @author Vishwaa Patel & Drashti Shah
 * @version 1.0
 * @since 2016-04-08
 *
 */

public class BoardPanel extends JPanel {
	// initializes the GUI components and variables
	private JButton buttons [] = new JButton[7];
	private JLabel[][] labels = new JLabel[7][7];
	private CellState player = CellState.PLAYER1;
	private String playerMode;
	private Board board;
	private BufferedImage emptyToken = null;
	private BufferedImage redToken = null;
	private BufferedImage yellowToken = null;
	private boolean win = false;
	private int column;
	private double beginningTime;
	private double endingTime;
	
	/** 
	 * initializes the Board Panel by setting the background color, size and font size
	 * Also creates and add all the GUI components to the panel and sets their visibility 
	 * adds all the buttons to the action Listener
	 */
	
	public BoardPanel(String player){
		beginningTime = System.currentTimeMillis();
		playerMode = player;
		
		//features of the panel
		setBackground(new Color (50,80,158));
		setLayout(new GridLayout(8, 7));
		board = new Board();
		
		// reads the image of the tokens to be used as labels in program or pops an exception if the file is not found
		try 
		{
			emptyToken = ImageIO.read(new File("empty.png"));
			redToken = ImageIO.read(new File("red.png"));
			yellowToken = ImageIO.read(new File("yellow.png"));
		}
		
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "File not Found");
		}		
		
		// adds all the buttons to the action Listener
		for(int i=0; i<7; i++){
			buttons[i] = new JButton(String.valueOf(i+1));				
				add(buttons[i]);
				buttons[i].addActionListener(new ClassListener());
		}
		
		// sets the icon of the label to the empty token 
		for(int i=0;i<7;i++){
			for(int j=0;j<7;j++){
				labels[i][j] = new JLabel();
				labels[i][j].setIcon(new ImageIcon(emptyToken));
				add(labels[i][j]);				
			}
		}
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
				
				// checks for the button clicked by the user and updates the board(2d array of cell) and also the 2d array of JLabels
				// at that location with the least available row location
				for(int i=0; i<7; i++){
						if(event.getSource() == buttons[i]){
							if(!board.isColumnFull(i)){
								board.rowLocation(i);
								board.update(board.getRowLocation(i), i, player);
								labels[board.getRowLocation(i)][i].setIcon(new ImageIcon(image(player)));
												
								// Checks if the current player wins vertically, horizontally or one of the both diagonally.
								// If any of these methods return 3, then there a 3 tokens of the same color around the token dropped by the user.
								if(board.verticalCheckWin(i, board.getRowLocation(i)) >= 3){
									win = true;
									endingTime = System.currentTimeMillis();
									JOptionPane.showMessageDialog(null, getToken(player) + " wins and it took " + Math.round((endingTime/1000 - beginningTime/1000)) + " seconds");
								}
								else if(board.diagonal1CheckWin(i, board.getRowLocation(i)) >= 3){
									win = true;
									endingTime = System.currentTimeMillis();
									JOptionPane.showMessageDialog(null, getToken(player) + " wins and it took " + Math.round((endingTime/1000 - beginningTime/1000)) + " seconds");
									
								}
								else if( board.diagonal2CheckWin(i, board.getRowLocation(i)) >= 3){
									win = true;
									endingTime = System.currentTimeMillis();
									JOptionPane.showMessageDialog(null, getToken(player) + " wins and it took " + Math.round((endingTime/1000 - beginningTime/1000)) + " seconds");
									
								}
								else if(board.horizontalCheckWin(i, board.getRowLocation(i)) >= 3){
									win = true;
									endingTime = System.currentTimeMillis();
									JOptionPane.showMessageDialog(null, getToken(player) + " wins and it took " + Math.round((endingTime/1000 - beginningTime/1000)) + " seconds");
									
								}			
								
								// switches the player
								// if it is Player 1 , then changes to player 2 
								// if it is Player 2 , then changes to player 1 
								if(player == CellState.PLAYER1){
									player = CellState.PLAYER2;
								}
								else
								{
									player = CellState.PLAYER1;
								}
								
								// if the player mode is single player and if it is player 2,
								// then it picks a random column and get the row location and updates the board(2darray of cell) 
								// and also the 2d array of JLabel
								
								if(playerMode == "SinglePlayer" && player == CellState.PLAYER2 && !win){
									column = (int)((7) * Math.random());
									while(board.getRowLocation(column) == 0){
										column = (int)((7)*Math.random());										
									}
									
									// Checks if the current player wins vertically, horizontally or one of the both diagonally.
									// If any of these methods return 3, then there a 3 tokens of the same color around the token dropped by the computer.						
									board.rowLocation(column);
									board.update(board.getRowLocation(column), column, CellState.PLAYER2);
									labels[(board.getRowLocation(column))][column].setIcon(new ImageIcon(yellowToken));
																		
									if(board.verticalCheckWin(column, board.getRowLocation(column)) >= 3){
										endingTime = System.currentTimeMillis();
										JOptionPane.showMessageDialog(null, getToken(player) + " wins and it took " + Math.round((endingTime/1000 - beginningTime/1000)) + " seconds");
										win = true;

									}
									else if(board.diagonal1CheckWin(column, board.getRowLocation(column)) >= 3){
										endingTime = System.currentTimeMillis();
										JOptionPane.showMessageDialog(null, getToken(player) + " wins and it took " + Math.round((endingTime/1000 - beginningTime/1000)) + " seconds");
										win = true;

									}
									else if( board.diagonal2CheckWin(column, board.getRowLocation(column)) >= 3){
										endingTime = System.currentTimeMillis();
										JOptionPane.showMessageDialog(null, getToken(player) + " wins and it took " + Math.round((endingTime/1000 - beginningTime/1000)) + " seconds");
										win = true;

									}
									else if(board.horizontalCheckWin(column, board.getRowLocation(column)) >= 3){
										endingTime = System.currentTimeMillis();
										JOptionPane.showMessageDialog(null, getToken(player) + " wins and it took " + Math.round((endingTime/1000 - beginningTime/1000)) + " seconds" );
										win = true;

									}		
									// switches the player to player1
									player = CellState.PLAYER1;
								}
							}
							
							else
							{
								JOptionPane.showMessageDialog(null, "This column is full");
							}
						}	
				}
				
				// if an of the two player wins, then it sets all the buttons disabled
				if(win){
					for(int i=0; i<7; i++){
						buttons[i].setEnabled(false);
					}				
				}	
				
				// if the board is full, then it prints out a draw message and disables all the buttons
				else if(board.isFinish()){
					endingTime = System.currentTimeMillis();
					JOptionPane.showMessageDialog(null, "Its a draw and the time it took is " + Math.round((endingTime/1000 - beginningTime/1000)) + " seconds");
					for(int i=0; i<7; i++){
						buttons[i].setEnabled(false);
					}
					
				}			
			}			
		}
		
		/**
		 * returns the color of the token that is associated to the player
		 * @param player - the player which wins the game
		 * @return the string of the color of the token
		 */
		
		private String getToken(CellState player){
			if(player == CellState.PLAYER1){
				return "Red";
			}
			else
			{
				return "Yellow";
			}
		}
		/**
		 * gets the image of the token that is associated to the player 
		 * @param player - the player which makes a move
		 * @return the image of the token of the player
		 */
		private BufferedImage image (CellState player){
			if(player == CellState.PLAYER1){
				return redToken;
			}
			else
			{
				return yellowToken;
			}
		}
}
