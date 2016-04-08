package patel.vishwaa;

/**
* @author Vishwaa Patel & Drashti Shah
* @version 1.1.0
* @since April 6th 2016
* 
*  Defines the spot for the token in the board. The state of the cell is changed when a token is dropped
*/
public class Cell {
	
private CellState state;
	
	/**
	 * initializes the state of the cell to be empty
	 */
	Cell(){
	   state = CellState.EMPTY;	
	}
	/**
	 * Returns the state of the cell
	 * @return the state of the cell
	 */
	public CellState getState(){
		return state;
	}
	
	/**
	 * sets the new state of the cell
	 * @param newState - gets the cellState and sets that state of the cell  
	 */
	public void setState(CellState newState){
		state = newState;
	}

}
