package concurrency.primecounting.withcompletablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo2 {

	public static void main(String[] args) throws Exception {
		
		ExecutorService pool = Executors.newFixedThreadPool(3);
		PrimeCounter pc = new PrimeCounter(1, 100_000);
		
		CompletableFuture<Long> primeCalcFut = CompletableFuture
			.supplyAsync(() -> pc.calculate(),pool);
		
		primeCalcFut.thenAccept(l -> System.out.println("Number of primes is: "+l));

		Thread.sleep(1000);
		primeCalcFut.complete(7777L);
		
		pool.shutdown();
	}

}
