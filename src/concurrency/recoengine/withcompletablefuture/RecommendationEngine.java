package concurrency.recoengine.withcompletablefuture;

public interface RecommendationEngine {
	
	public Product getRecoBasedOnOrderHistory(int userId);
	public Product getRecoBasedOnSearches(int userId);
	public Product getRecoBasedOnCampaign(int userId);

}
