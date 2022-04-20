package concurrency.threadsafety;

class Pusher extends Thread{
	MyStack s;

	public Pusher(MyStack s) {
		super();
		this.s = s;
	}
	
	@Override
	public void run() {
		for(int i=0;i<100000;i++) {
			s.push(i);
		}
	}
	
}

class Popper extends Thread{
	MyStack s;

	public Popper(MyStack s) {
		super();
		this.s = s;
	}
	
	@Override
	public void run() {
		for(int i=0;i<100000;i++) {
			s.pop();
		}
	}
	
}

public class TestStack {
	
	public static void main(String[] args) throws InterruptedException {
		MyStack s = new MyStack(100000);
		
		Pusher pusher = new Pusher(s);
		Popper popper = new Popper(s);
		pusher.start();
		//pusher.join();
		popper.start();
	}

}
