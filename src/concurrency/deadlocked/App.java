package concurrency.deadlocked;

public class App {

	public static void main(String[] args) {
		Buffer b = new Buffer();
		Producer p = new Producer(b);
		Consumer c = new Consumer(b);
		
		p.start();
		c.start();
	}

}
