package concurrency.recoengine;

import java.util.Map;

public class RecommendationApp {

	public static void main(String[] args) throws InterruptedException {
		RecommendationEngine re = new RecommendationEngineImpl();
		RecommendationBuilder rb = new RecommendationBuilder(re);
		Map<String,Product> recos = rb.getRecommendations(1234);
		System.out.println(recos);
	}

}
