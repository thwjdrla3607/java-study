package prob5;

public class MyStack {
	private int maxSize;
	private int top;
	private String[] string;
	
	public MyStack(int maxSize) {
		super();
		this.maxSize = maxSize * 2;
		this.top = 0;
		this.string = new String[this.maxSize];
	}
	
	public boolean isFull() {
		return top == maxSize;
	}
	
	public boolean isEmpty() {
		return top == 0;
	}
	
	public void push(String string) {
		if (this.isFull()) 
			return;
		
		this.string[top++] = string; 
	}
	
	public String pop() throws MyStackException {
		if(this.isEmpty())
			throw new MyStackException();
		
		String s = this.string[--this.top];
		return s;
	}
}