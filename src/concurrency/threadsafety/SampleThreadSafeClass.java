package concurrency.threadsafety;

public class SampleThreadSafeClass {
	
	private int i = 10;
	
	
	public synchronized void incAndPrint() {
		i++;
		printI(i);
	}

	public void printI(int x) {
		x++;
		System.out.println(x);
	}
}
