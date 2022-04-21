package concurrency.primecounting.withcompletablefuture;

public class PrimeCounter{
	
	private long start,stop;

	public PrimeCounter(long start, long stop) {
		super();
		this.start = start;
		this.stop = stop;
	}
	
	
	public Long calculate() {
		Long count = 0L;
		System.out.println("Started counting primes in the range "+start+" to "+stop+" in thread: "
							+Thread.currentThread().getName());
		
		for(long i = start; i <= stop; i++) {
			if(isPrime(i)) {
				count++;
			}
		}
		return count;
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
	
	

}
