package concurrency.recoengine.withcompletablefuture;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RecommendationApp {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		RecommendationEngine re = new RecommendationEngineImpl();
		RecommendationBuilder rb = new RecommendationBuilder(re);
		Map<String,Product> recos = rb.getRecommendations(1234);
		System.out.println(recos);
	}

}
