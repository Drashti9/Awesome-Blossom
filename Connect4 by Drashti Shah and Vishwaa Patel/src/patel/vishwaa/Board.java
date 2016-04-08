package patel.vishwaa;

/**
 * Acts as the model of the game
 * defines the grid of JButtons and JLabels
 * updates the 2d array of cell according to the moves of the user and the player that plays that move
 * Also updates the row location after a token is dropped in a column
 * gets the row and column of the token dropped and checks if there is a connect 4 horizontally, vertically, and diagonally
 * 
 * @author Vishwaa Patel & Drashti Shah
 *  @version 1.0
 * @since 2016-04-08
 *
 */
public class Board {
	//initializes the array and 2d array
private Cell[][] grid  = new Cell[7][7];
private int[] height = new int[7];
	
	/**
	 * initializes the 2d array of the cell and also assigns the values to height array 
	 */
	public Board(){
		
		for (int i=0; i<7; i++){
			for (int j=0; j<7; j++){
				grid[i][j] = new Cell();				
			}
		}
		for(int i=0;i<7;i++)
		{
			height[i] = 7;
		}
	}	
	
	/**
	 * updates the row location of the column when the user clicks a button
	 * @param column - gets the column according to the button clicked where the user puts a token
	 * @return the updated row location where it is empty
	 * 
	 */
	public void rowLocation(int column){
		height[column]--;
	}
	
	/**
	 * gets the column based on the button clicked by the user and returns the row location 
	 * @param column - gets the column according to the button clicked where the user puts a token
	 * @return the row location of the column where it is empty
	 */
	public int getRowLocation(int column)
	{
		return height[column];
	}
	
	/**
	 * checks if the column is full and returns true if it is full
	 * @param column - gets the column according to which button is clicked by the user where the user puts a token
	 * @return true if the column is full else false
	 */
	public boolean isColumnFull(int column){
		return grid[0][column].getState()!=CellState.EMPTY;
	}
	
	/**
	 * updates the cell 2d array where the user puts a token 
	 * @param row - gets the row of the location where the user puts a token
	 * @param column - gets the column of the location where the user puts a token
	 * @param player - gets the state of the player and updates it according to that player
	 */
	public void update(int row, int column, CellState player){		
		grid[row][column].setState(player);		
	}
	
	/**
	 * checks if the board is full
	 * @return true if the board is full or else returns false
	 */
	public boolean isFinish(){
		int a=0;
		for(int i=0; i<7; i++){
			if(grid[0][i].getState() != CellState.EMPTY){
				a++;
			}
		}
		
		if(a==7){
			return true;
		}
		else
		{
			return false;		
		}
	}
	
	/**
	 * checks if there are four tokens of the same player in a line vertically
	 * checks it by going up from the current dropped token and down the current dropped token for the same token
	 * @param column - the column of the location where the user dropped the token
	 * @param row - the row of the location where the user dropped the token
	 * @return the the number of matches it gets with the token dropped vertically
	 */
	public int verticalCheckWin(int column, int row){
		int temp =0;
		if(row < 4){
			for(int i=1; i<4; i++){
				if(grid[row+i][column].getState() == grid[row][column].getState()){
					temp++;
				}				
			}			 
		}
		return temp;
		}
	
	/**
	 * checks if there are four tokens of the same player in a line diagonally
	 * checks it by going diagonally up from the current dropped token and diagonally down from the current dropped token for the same token
	 * @param column - the column of the location where the user dropped the token
	 * @param row - the row of the location where the user dropped the token
	 * @return the the number of matches it gets with the token dropped diagonally
	 */
	
	public int diagonal1CheckWin(int column, int row){
		int matchUp=0;
		int matchDown=0;
		int tempRow1 =row;
		int tempColumn1 = column;
		int tempRow2 = row;
		int tempColumn2 = column;
		
		while (tempRow1>=0 && tempColumn1 >=0  && grid[tempRow1][tempColumn1].getState() == grid[row][column].getState()){
			tempRow1--;
			tempColumn1--;
			matchUp++;
		}
		
		if(matchUp>0){
			matchUp = matchUp -1;
		}
		
		while(tempRow2 <=6 && tempColumn2 <=6  && grid[tempRow2][tempColumn2].getState() == grid[row][column].getState()){
			tempRow2 ++;
			tempColumn2 ++;
			matchDown++;
		}
		
		if(matchDown>0){
			matchDown = matchDown -1;
		}
			return matchUp + matchDown;
		}	
	
	/**
	 * checks if there are four tokens of the same player in a line diagonally
	 * checks it by going diagonally up from the current dropped token and diagonally down from the current dropped token for the same token
	 * @param column -  the column of the location where the user dropped the token 
	 * @param row - the row of the location where the user dropped the token
	 * @return the the number of matches it gets with the token dropped diagonally
	 */
	
	public int diagonal2CheckWin(int column, int row){
		int matchDown=0;
		int matchUp=0;
		int tempRow1 = row;
		int tempColumn1 = column;
		int tempRow2 = row;
		int  tempColumn2 = column;
		
		while (tempRow1 <=6 && tempColumn1 >=0  && grid[tempRow1][tempColumn1].getState() == grid[row][column].getState()){
			tempRow1++;
			tempColumn1--;
			matchDown++;			
		}
		
		if(matchDown>0){
			matchDown=matchDown-1;
		}	
		
		while(tempRow2 >=0 &&  tempColumn2 <=6 && grid[tempRow2][tempColumn2].getState() == grid[row][column].getState()){
			tempRow2 --;
			 tempColumn2 ++;
			matchUp++;			
		}
		
		if(matchUp>0){
			matchUp=matchUp-1;
		}
		
		return (matchDown+matchUp);
	}
		
	/**
	 * checks if there are four tokens of the same player in a line horizontally
	 * checks it by going horizontally right from the current dropped token and horizontally left from the current dropped token for the same token
	 * @param column -  the column of the location where the user dropped the token 
	 * @param row - the row of the location where the user dropped the token
	 * @return the the number of matches it gets with the token dropped diagonally
	 */
	public int horizontalCheckWin(int column, int row){
		int moveLeft=0;
		int moveRight=0;
		int tempColumn1 = column;
		int tempColumn2 = column;
		
		while (tempColumn1 >=0  && grid[row][tempColumn1].getState() == grid[row][column].getState()){
			tempColumn1--;
			moveLeft++;
		}
		if(moveLeft>0){
			moveLeft = moveLeft-1;
		}
		
		while(tempColumn2 <=6  && grid[row][tempColumn2].getState() == grid[row][column].getState()){
			tempColumn2++;
			moveRight++;
		}
		
		if(moveRight>0){
			moveRight = moveRight-1;
		}
		
			return moveLeft + moveRight;
		}

}
