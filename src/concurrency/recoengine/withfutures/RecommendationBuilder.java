package concurrency.recoengine.withfutures;

import java.util.HashMap;
import java.util.Map;
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
		
		Future<Product> crFut = pool.submit(() -> re.getRecoBasedOnCampaign(userId));
		Future<Product> srFut = pool.submit(() -> re.getRecoBasedOnSearches(userId));
		Future<Product> hFut = pool.submit(() -> re.getRecoBasedOnOrderHistory(userId));
		
		
		Product cProduct = crFut.get();
		Product hProduct = hFut.get();
		Product sProduct = srFut.get();
		
		long stop = System.currentTimeMillis();
		
		System.out.println("It took "+(stop - start)+" ms to compute the recos...");
		
		Map<String,Product> recos = new HashMap<String, Product>();
		recos.put("campaign", cProduct);
		recos.put("history", hProduct);
		recos.put("searches", sProduct);
		
		pool.shutdown();
		return recos;
		
	}

}

