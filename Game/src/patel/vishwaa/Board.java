package patel.vishwaa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.File;

public class Board extends JPanel{
	JButton Instructions;
	JButton strategyToWin;
	JButton SinglePlayer;
	JButton TwoPlayer;
	JButton button;
	JLabel name;
	JLabel lblInstructions;
	JLabel label;
	BufferedImage empty = null;
	BufferedImage red = null;
	String img = "red";
	
	int check[] = new int [7];
	boolean turn = true;
	static JFrame frame;
	
		
	public Board(JFrame frame){
		
		final int FONT_SIZE = 72;
		setBackground(Color.cyan);
		this.frame = frame;
		setPreferredSize(new Dimension(500, 500));
		
		name = new JLabel();
				
		BufferedImage img = null;
		try 
		{
			img = ImageIO.read( new File ("connect-4-logo.png"));	
			name.setIcon(new ImageIcon(img));
		} 		
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "File not Found");
		}		
		
		/*try
		{
			img1 = ImageIO.read(new File("empty.png"));
			img2 = ImageIO.read(new File("red.png"));
			label.setIcon(new ImageIcon(img1)); 
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "File not Found");
		}*/
        
		
        Instructions = new JButton("Instructions");
		lblInstructions = new JLabel("Instructions here");
		SinglePlayer = new JButton("Single Player");
		TwoPlayer = new JButton("Two Player");
		label = new JLabel();
		SinglePlayer.setPreferredSize(new Dimension(150, 50));		
		TwoPlayer.setPreferredSize(new Dimension(150, 50));
		Instructions.setPreferredSize(new Dimension(150,50));
		
		add(name);
		add(SinglePlayer);
		add(TwoPlayer);
		add(Instructions);
						
		SinglePlayer.setVisible(true);
		TwoPlayer.setVisible(true);
		Instructions.setVisible(true);
			
		//label.setVisible(true);		
		
		SinglePlayer.addActionListener(new ClassListener());
		TwoPlayer.addActionListener(new ClassListener());
		Instructions.addActionListener(new ClassListener());
		
		
		
	}
		
		private class ClassListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				
				if (event.getSource() == SinglePlayer) {
			
					frame.getContentPane().remove(frame.getComponent(0));
					//frame.getComponent(0).setVisible(false);
					frame.getContentPane().add(new Board2());
					frame.pack();
					frame.setResizable(false);
					frame.setVisible(true);
					
					
					
					
				}
				
				else if (event.getSource() == Instructions) {
					setBackground(Color.YELLOW);
					setLayout(new GridLayout(1, 0));
					add(lblInstructions);
					remove(SinglePlayer);
					remove(TwoPlayer);
					remove(name);
					remove(Instructions);					
					lblInstructions.setText("<html> Instructions <br> <br> 1) Put your tokens <br> <br> 2) Try to connect 4 <html>");
				}
				/*else if(event.getSource() == TwoPlayer){
					for(int i=0; i<7; i++){
						for(int j=0; j<7; j++){
							grid[i][j] = new JLabel();
							grid[i][j].setIcon(new ImageIcon(empty));
							add(grid[i][j]);
						}
					}
					
				}*/
			}
		}		
		
	/*	private class ClassListener2 implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				
				for(int i=0; i<7; i++){
					if(event.getSource() == buttons[i]){
						if(check[i] <=7){
							grid[0][i].setIcon( new ImageIcon(img));
							check[0] = check[0] + 1;
						}
						else
						{
							JOptionPane.showMessageDialog(null, "This column is full");
						}
						
					}
				}
				
				if(turn){
					turn = false;
					img = "yellow";
				}
				else
				{
					turn = true;
					img = "red";
				}
				
			}
		}		*/
}



	

