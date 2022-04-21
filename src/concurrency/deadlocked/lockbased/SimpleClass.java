package concurrency.deadlocked.lockbased;

public class SimpleClass {

	int i = 10;

	public synchronized void toBeCalledByThreadA() {

		i++;
		System.out.println("Thread a " + i);
		//something
	}

	public synchronized void toBeCalledByThreadB() {

		System.out.println("Thread b " + i);
	}

}

//Thread a 11
//Thread b 10
