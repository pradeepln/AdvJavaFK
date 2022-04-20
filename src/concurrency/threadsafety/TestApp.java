package concurrency.threadsafety;

public class TestApp {

	public static void main(String[] args) {
		SampleThreadSafeClass t = new SampleThreadSafeClass();
		
		t.incAndPrint();

	}

}
