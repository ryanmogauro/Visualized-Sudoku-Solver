import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Graphics;


public class Board {
	private Cell[][] board; 
	public static final int Size = 9;
	
	//constructor which builds a 9x9 board of cells
	public Board() {
		this.board = new Cell[Board.Size][Board.Size]; 
		for(int r = 0; r < Board.Size; r++) {
			for(int c = 0; c < Board.Size; c++) {
				this.board[r][c] = new Cell(r,c,0);
			}
		}
	}
	
	//returns the number of columns
	public int getCols() {
		return board[0].length; 
	}
	
	//returns the number of rows
	public int getRows() {
		return board.length; 
	}
	
	//returns the Cell at location r, c.
	public Cell get(int r, int c) {
		return board[r][c];
	}
	
	//returns whether the Cell at r, c, is locked.
	public boolean isLocked(int r, int c) {
		return board[r][c].isLocked(); 
	}
	
	// returns the number of locked Cells on the board.
	public int numLocked() {
		int totalLocked = 0; 
		for(int r = 0; r < Board.Size; r++) {
			for(int c = 0; c < Board.Size; c++) {
				if(board[r][c].isLocked()) {
					totalLocked++;
				}
			}
		}
		return totalLocked; 
	}
	
	//returns the value at Cell r, c.
	public int value(int r, int c) {
		return board[r][c].getValue();
	}
	
	//sets the value of the Cell at r, c.
	public void set(int r, int c, int value) {
		board[r][c] = new Cell(r, c, value);
	}
	
	// sets the value and locked fields of the Cell at r, c.
	public void set(int r, int c, int value, boolean locked) {
		board[r][c] =  new Cell(r, c, value, locked); 
	}
	
	//reads file based on filename parameter
	public boolean read(String filename){
		try{
			FileReader flReader = new FileReader(filename); 

			BufferedReader bfReader = new BufferedReader(flReader); 

			String line = bfReader.readLine(); 

			String digits = "0123456789"; 

			while(line != null){
				for(int r = 0; r < this.board.length; r++){
					String[] arr = line.split("[ ]+");

					for(int c = 0; c < this.board[r].length; c++){
						
						if(Integer.parseInt(arr[c]) > 0){
							this.set(r,c,Integer.parseInt(arr[c]), true);
						} else{
							this.set(r,c,Integer.parseInt(arr[c]));
						}
					}
					line = bfReader.readLine(); 
				}
			}
			bfReader.close(); 
			return true; 

		}
		catch(FileNotFoundException ex){
			System.out.println("Board.read():: unable to open file " + filename);
		}
		catch(IOException ex){
			System.out.println("Board.read():: error reading file " + filename);
		}

		return false;
	}


	public boolean validValue(int row, int col, int value){
		if(value < 1 || value > 9) {
			return false; 
		}
		
		for(int r = 0; r < this.board.length; r++) {
			if(row == r) {
				continue; 
			}
			if(this.board[r][col].getValue() == value) {
				return false; 
			}
		}
		
		for(int c = 0; c < this.board[row].length; c++) {
			if(col == c) {
				continue; 
			}
			if(this.board[row][c].getValue() == value) {
				return false; 
			}
		}
		
		int localRow = row/3; 
		int localCol = col/3; 
		
		for(int r = 0 + (3*localRow); r < (3 + (3*localRow)); r++) {
			for(int c = 0 + (3*localCol); c < (3 + (3*localCol)); c++) {
				if(row == r && col == r) {
					continue;
				}
				if(this.value(r, c) == value) {
					return false; 
				}
			}
		}
		return true;
	}

	public String toString() {
		String result = ""; 
		for(int r = 0; r < Board.Size; r++) {
			for(int c = 0; c < Board.Size; c++) {
				result+=this.board[r][c]; 
			}
			result+="\n";
		}
		return result; 
	}

	public boolean validSolution(){
		for(int r = 0; r < Board.Size; r++) {
			for(int c = 0; c < Board.Size; c++) {
				if(this.value(r,c) == 0 || !(this.validValue(r, c, this.value(r,c)))) {
					return false; 
				}
			}
		}
		return true; 
	}
	
	
	
	public Cell findBestCell() {
		for(int r = 0; r < Board.Size; r++) {
			for(int c = 0; c < Board.Size; c++) {
				if(this.value(r,c) == 0) {
					for(int i = 1; i < 10; i++) {
						if(this.validValue(r, c, i)) {
							this.set(r, c, i);
							return this.get(r, c); 
						}
					}
					return null;
				}
			}
		}
		return null; 
	}
	
	
	public void draw(Graphics g, int scale) {
		for(int r = 0; r < Board.Size; r++) {
			for(int c = 0; c < Board.Size; c++) {
				this.get(r, c).draw(g, r+1, c+1, scale);
			}
		}
	}
	
	
	//tests methods to ensure they're working as intended
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			Board board = new Board(); 
			board.read(args[0]); 
			System.out.println(board);
			System.out.println("should be false: " +  board.validValue(0,0,3)); 
			System.out.println("should be false: " +  board.validValue(0,0,1)); 
			System.out.println("should be true: " +  board.validValue(1,1,5));
			System.out.println("should be false: " + board.validSolution()); 
			System.out.println("should be true: " +  board.validValue(0,1,8));
			board.set(0,0,8); 
			System.out.println("should be false: " +  board.validValue(0,1,8));
			System.out.println("should be 8: " +  board.value(0,0));

		}
}
