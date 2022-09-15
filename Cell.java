import java.awt.Graphics; 

public class Cell {
	private int row; 
	private int col; 
	private int value; 
	private boolean locked; 
	
	//initialize all values to 0 or false;
	public Cell() {
		this.row = 0; 
		this.col = 0; 
		this.value = 0; 
		this.locked = false; 
	}
	
	//initialize the row, column, and value fields to the given parameter values. Set the locked field to false;
	public Cell(int row, int col, int value) {
		this.row = row; 
		this.col = col; 
		this.value = value; 
		this.locked = false;
	}
	
	//initialize all of the Cell fields given the parameters.
	public Cell(int row, int col, int value, boolean locked) {
		this.row = row; 
		this.col = col; 
		this.value = value; 
		this.locked = locked;
	}
	
	//return the Cell's row index.
	public int getRow() {
		return this.row; 
	}
	
	//return the Cell's column index.
	public int getCol() {
		return this.col; 
	}
	
	//return the Cell's value.
	public int getValue() {
		return this.value;
	}
	
	//set the Cell's value.
	public void setValue(int newval) {
		this.value = newval; 
	}
	
	//return the value of the locked field
	public boolean isLocked() {
		return locked; 
	}
	
	//set the Cell's locked field to the new value.
	public void setLocked(boolean lock) {
		this.locked = lock;
	}
	
	//
	public Cell clone() {
		Cell holder = new Cell(this.row, this.col, this.value, this.locked); 
		return holder; 
		
	}
	
	public String toString() {
		return "" + this.value; 
	}
	
	
	public void draw(Graphics g, int x0, int y0, int scale){
		char[] temp = {(char)('0' + this.value),0}; 
		g.drawChars(temp,0,1,x0*scale - scale/2, y0*scale - scale/2); 
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
