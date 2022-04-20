package concurrency.recoengine.withfutures;

import java.util.Arrays;
import java.util.Random;

public class RecommendationEngineImpl implements RecommendationEngine {
	
	private Product[] campaignProducts;
	private Product[] allProducts;
	private Random r = new Random();
	
	public RecommendationEngineImpl() {
		allProducts = new Product[100];
		for(int i = 1; i <= 100 ; i++) {
			allProducts[i-1] = new Product(i, "Product"+i, i*10, i*100);
		}
		campaignProducts = new Product[10];
		
		for(int i = 0; i< 10;i++) {
			campaignProducts[i] = allProducts[r.ints(0, 100).findFirst().getAsInt()];
		}
		
	}

	@Override
	public Product getRecoBasedOnCampaign(int userId) {
		simulateWork(250);
		return campaignProducts[r.nextInt(10)];
	}
	
	@Override
	public Product getRecoBasedOnOrderHistory(int userId) {
		simulateWork(300);
		return allProducts[r.nextInt(100)];
	}
	
	@Override
	public Product getRecoBasedOnSearches(int userId) {
		simulateWork(200);
		return allProducts[r.nextInt(100)];
	}
	
	
	private void simulateWork(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
