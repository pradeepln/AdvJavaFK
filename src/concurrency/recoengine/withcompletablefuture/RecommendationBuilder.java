package concurrency.recoengine.withcompletablefuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RecommendationBuilder {
	
	RecommendationEngine re;

	public RecommendationBuilder(RecommendationEngine re) {
		super();
		this.re = re;
	}
	
	public Map<String,Product> getRecommendations(int userId) throws InterruptedException, ExecutionException{
		
		ExecutorService pool = Executors.newFixedThreadPool(5);
		long start = System.currentTimeMillis();
		Map<String,Product> recos = new HashMap<String, Product>();
		
		CompletableFuture<Void> crFut = CompletableFuture
											.supplyAsync(() -> re.getRecoBasedOnCampaign(userId))
											.thenAccept(p -> recos.put("campaign", p));
		CompletableFuture<Void> srFut = CompletableFuture
				.supplyAsync(() -> re.getRecoBasedOnSearches(userId))
				.thenAccept(p -> recos.put("searches", p));
		
		CompletableFuture<Void> hFut = CompletableFuture
				.supplyAsync(() -> re.getRecoBasedOnOrderHistory(userId))
				.thenAccept(p -> recos.put("history", p));
		
		CompletableFuture.allOf(crFut,srFut,hFut).get();
		
		long stop = System.currentTimeMillis();
		
		System.out.println("It took "+(stop - start)+" ms to compute the recos...");
		
		
		
		
		pool.shutdown();
		return recos;
		
	}

}

