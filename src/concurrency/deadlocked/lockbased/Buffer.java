package concurrency.deadlocked.lockbased;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
	// my unlocks should have been in finally
	private int data;
	private boolean available = false;
	Lock lock = new ReentrantLock();
	Condition emptyCondition = lock.newCondition();
	Condition fullCondition = lock.newCondition();
	
	public int readData() {
		lock.lock();
		while(!available) {
			try {
				fullCondition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//long running loop
		this.available = false;
		emptyCondition.signalAll();
		
		lock.unlock();
		return this.data;
	}

	
	public void writeData(int newData) {
		lock.lock();
		while(available) {
			try {
				emptyCondition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.data = newData;
		this.available = true;
		fullCondition.signalAll();
		lock.unlock();
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