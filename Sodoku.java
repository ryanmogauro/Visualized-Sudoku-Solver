 import java.util.Random;
public class Sodoku {
	
	private LandscapeDisplay display;
	private Board board;
	
	public Sodoku() {
		this.board = new Board(); 
		this.display = new LandscapeDisplay(board, 30); 
	}
	
	public Sodoku(int n) {
		this.display = new LandscapeDisplay(board, 30); 
		Random rand = new Random(); 
		this.board = new Board(); 
		for(int i = 0; i < n; i++) {
			this.board.set(rand.nextInt(9), rand.nextInt(9), rand.nextInt(9), true);
		}
	}
	
	public boolean solve(int delay){
		
		//Given: a stack, and the number of locked cells
		CellStack stack = new CellStack(); 
		boolean stuck = false; 
		int unlockedCells = (81 - board.numLocked()); 

		//while the stack size is less than the number of unspecified cells
		while(stack.size() < unlockedCells) { 
			 if( delay > 0 ) {
			        try {
			            Thread.sleep(delay);
			        }
			        catch(InterruptedException ex) {
			            System.out.println("Interrupted");
			        }
			        display.repaint();
			    }
		
			Cell nextCell = this.board.findBestCell(); 
			if(nextCell != null) {
				stack.push(nextCell);
				System.out.println("pushing onto stack"); 
			}
			else {
				System.out.println("made it to else"); 
				stuck = true; 
				while(stack.size() > 0 && stuck) {
					Cell currentCell = stack.pop(); 
					System.out.print("popped " + currentCell + " from stack");
					for(int i = currentCell.getValue()+1; i < 10; i++) {
						if(this.board.validValue(currentCell.getRow(), currentCell.getCol(), i)) {
							System.out.println("brother we made it");
							stack.push(currentCell); 
							currentCell.setValue(i); 
							stuck = false; 
							System.out.println("new value is " + i); 
							break; 
						}
					}
					if(stuck) {
						currentCell.setValue(0); 
					}
				}
				if(stack.size() == 0) {
					return false; 
				}
			}
		}
		return true; 
	}
	
	public boolean newSolve() {
		CellStack stack = new CellStack(); 
		int unlockedCells = (81 - board.numLocked()); 
		int n = 0;
		while(stack.size() < unlockedCells) {
			Cell nextCell = this.board.findBestCell(); 
			
			if(nextCell != null) {
				stack.push(nextCell);
				n++;
				System.out.println("adding number " + n + " to stack");
				//update the board
			} else {
				while(!stack.empty() && !(board.validSolution())) {
					Cell currentCell = stack.pop(); 
					for(int i = 1; i < 10; i++) {
						if(this.board.validValue(currentCell.getRow(), currentCell.getCol(), i)) {
							currentCell.setValue(i); 
							stack.push(currentCell);
							//out of order
							break; 
						} else {
							currentCell.setValue(0);
						}
					}
				}
				if(stack.empty()) {
					return false; 
				}
			}
		}
		return true; 
		
	}

	public String toString(){
		return this.board + ""; 
	}
		
		   

	public static void main(String[] args) {
		Sodoku game = new Sodoku(9); 
		game.solve(10); 
		System.out.println(game); 
	}

}
