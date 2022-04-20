package concurrency.deadlocked;

public class Buffer {
	
	private int data;
	private boolean available = false;
	
	public synchronized int readData() {
		while(!available) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.available = false;
		this.notifyAll();
		return this.data;
	}

	
	public synchronized void writeData(int newData) {
		
		while(available) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.data = newData;
		this.available = true;
		this.notifyAll();
	}
	
}


class Producer extends Thread{
	private Buffer buffer;

	public Producer(Buffer buffer) {
		super();
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		for(int i = 0 ; i < 25 ; i++) {
			buffer.writeData(i);
			System.out.println("Producer produced ----------> "+i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class Consumer extends Thread{
	private Buffer buffer;

	public Consumer(Buffer buffer) {
		super();
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		for(int i = 0 ; i < 25 ; i++) {
			int data = buffer.readData();
			System.out.println("Consumer consumed ----------> "+data);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}