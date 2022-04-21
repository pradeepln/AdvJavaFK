package concurrency.primecounting.withcompletablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {

	public static void main(String[] args) throws Exception {
		
		ExecutorService pool = Executors.newFixedThreadPool(3);
		PrimeCounter pc = new PrimeCounter(1, 100_000);
		
		CompletableFuture
			.supplyAsync(() -> pc.calculate(),pool)
			.thenAccept(l -> System.out.println("Number of primes is: "+l));

		//Thread.sleep(10000);
	}

}
