package concurrency.threadsafety;

import java.util.Arrays;

public class MyStack {
	private Object[] contents;
	private int top = -1;
	
	public MyStack(int capacity) {
		contents = new Object[capacity];
	}
	
	public synchronized void push(Object element) {
		
		contents[++top] = element;
	}
	
	public synchronized Object pop() {
		return contents[top--];
	}
	
	public synchronized Object[] toArray() {
		return Arrays.copyOf(contents, contents.length);
	}
	
	public synchronized Object peek() {
		Object topMost = pop();
		push(topMost);
		return topMost;
	}

}
