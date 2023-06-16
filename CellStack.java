
public class CellStack {
	private Cell[] cells;
	private int t; 
	private int limit; 
	
	
	//initialize the stack to a default size.
	public CellStack() {
		this.cells = new Cell[10]; 
		this.t = 0; 
		this.limit = 10; 
	}
	
	// initialize the stack to hold up to max elements.
	public CellStack(int max) {
		this.cells = new Cell[max]; 
		this.t = 0;
		this.limit = max;
	}
	
	//if there is space, push c onto the stack.
	public void push( Cell c ) {
		if(this.t == limit) {
			Cell[] holder = new Cell[limit*2]; 
			for(int i = 0; i < this.limit; i++) {
				holder[i] = cells[i];
			}
			this.cells = holder;
			this.limit*=2; 
			cells[t] = c;
			t++; 
		} else {
			cells[t] = c; 
			t++;
		}
	}
	
	//remove and return the top element from the stack; return null if the stack is empty.
	public Cell pop() {
		if(this.t == 0) {
			return null;
		}
		t--; 
		return cells[t];
		
	}
	
	//return the number of selements on the stack.
	public int size() {
		return this.t; 
	}
	
	//return true if the stack is empty.
	public boolean empty() {
		return(this.t == 0);
	}
	
}
