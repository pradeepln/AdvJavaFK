package concurrency.startstop;

public class PrimeCounterTask implements Runnable{
	
	private long start,stop,count;

	public PrimeCounterTask(long start, long stop) {
		super();
		this.start = start;
		this.stop = stop;
	}
	
	@Override
	public void run() {
		System.out.println("Started counting primes in the range "+start+" to "+stop+" in thread: "
							+Thread.currentThread().getName());
		
		for(long i = start; i <= stop; i++) {
			if(isPrime(i)) {
				count++;
			}
			
			if(Thread.interrupted()) {
				System.out.println(Thread.currentThread().getName()+" has been interrupted...terminating prematurely :(");
				return;
			}
		}
	}
	
	
	private boolean isPrime(long num) {
		if(num <= 2) {
			return true;
		}
		for(long i=2;i<num;i++) {
			if((num % i) == 0) {
				return false;
			}
		}
		return true;
	}
	
	
	public long getCount() {
		return count;
	}

}
