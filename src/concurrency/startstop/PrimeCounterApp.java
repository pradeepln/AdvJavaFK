package concurrency.startstop;

public class PrimeCounterApp {

	public static void main(String[] args) throws InterruptedException {
		
		//primeCountingUsingThreadSubClass();
		
		primeCountingUsingRunnable();
		
		System.out.println("Main method exiting....");
	}

	private static void primeCountingUsingRunnable() throws InterruptedException {
		System.out.println("primeCountingUsingThreadSubClass is running in thread: "
				+Thread.currentThread().getName());
		
		PrimeCounterTask task1 = new PrimeCounterTask(1, 100_000);
		PrimeCounterTask task2 = new PrimeCounterTask(100_001, 500_000);
		
		Thread t1 = new Thread(task1);
		Thread t2 = new Thread(task2);
		
		t1.start();
		t2.start();
		
		
		System.out.println("Main Thread waiting for 2 seconds for the workers to finish.....");
		Thread.sleep(2000);
		if(!t1.isAlive() && !t2.isAlive()) {
			System.out.println("Primes found by t1 = "+task1.getCount());
			System.out.println("Primes found by t2 = "+task2.getCount());
		}else {
			System.out.println("Worker threads haven't finished...interrupting them to cancel...");
			t1.interrupt();
			t2.interrupt();
		}
//		t1.join();
//		t2.join();
		
		
		
	}

	private static void primeCountingUsingThreadSubClass() throws InterruptedException {
		System.out.println("primeCountingUsingThreadSubClass is running in thread: "
						+Thread.currentThread().getName());
		PrimeCounterThread t1 = new PrimeCounterThread(1, 100_000);
		PrimeCounterThread t2 = new PrimeCounterThread(100_001, 200_000);
		
		t1.start();
		t2.start();
		
		System.out.println("Main Thread waiting for 2 workers to finish.....");
		t1.join();
		t2.join();
		
		System.out.println("Primes found by t1 = "+t1.getCount());
		System.out.println("Primes found by t2 = "+t2.getCount());
	}

}
