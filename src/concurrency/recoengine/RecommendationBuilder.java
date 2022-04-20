package concurrency.recoengine;

import java.util.HashMap;
import java.util.Map;

public class RecommendationBuilder {
	
	RecommendationEngine re;

	public RecommendationBuilder(RecommendationEngine re) {
		super();
		this.re = re;
	}
	
	public Map<String,Product> getRecommendations(int userId) throws InterruptedException{
		long start = System.currentTimeMillis();
		CampaignRecoInvoker cri = new CampaignRecoInvoker(re, userId);
		HistoryRecoInvoker hri = new HistoryRecoInvoker(re, userId);
		SearchRecoInvoker sri = new SearchRecoInvoker(re, userId);
		Thread t1 = new Thread(cri);
		Thread t2 = new Thread(hri);
		Thread t3 = new Thread(sri);
		t1.start();t2.start();t3.start();
		
		t1.join();t2.join();t3.join();
		
		Product cProduct = cri.getResult();
		Product hProduct = hri.getResult();
		Product sProduct = sri.getResult();
		
		long stop = System.currentTimeMillis();
		
		System.out.println("It took "+(stop - start)+" ms to compute the recos...");
		
		Map<String,Product> recos = new HashMap<String, Product>();
		recos.put("campaign", cProduct);
		recos.put("history", hProduct);
		recos.put("searches", sProduct);
		return recos;
	}

}

class CampaignRecoInvoker implements Runnable{
	RecommendationEngine re;
	int userId;
	Product result;
	
	public CampaignRecoInvoker(RecommendationEngine re,int userId) {
		super();
		this.re = re;
		this.userId = userId;
	}
	
	@Override
	public void run() {
		result = re.getRecoBasedOnCampaign(userId);
		
	}
	
	public Product getResult() {
		return result;
	}
	
}


class HistoryRecoInvoker implements Runnable{
	RecommendationEngine re;
	int userId;
	Product result;
	
	public HistoryRecoInvoker(RecommendationEngine re,int userId) {
		super();
		this.re = re;
		this.userId = userId;
	}
	
	@Override
	public void run() {
		result = re.getRecoBasedOnOrderHistory(userId);
		
	}
	
	public Product getResult() {
		return result;
	}
	
}

class SearchRecoInvoker implements Runnable{
	RecommendationEngine re;
	int userId;
	Product result;
	
	public SearchRecoInvoker(RecommendationEngine re,int userId) {
		super();
		this.re = re;
		this.userId = userId;
	}
	
	@Override
	public void run() {
		result = re.getRecoBasedOnSearches(userId);
		
	}
	
	public Product getResult() {
		return result;
	}
	
}