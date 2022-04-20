package concurrency.filesearch.poolbased;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;

public class SearchWorker implements Runnable {
	
	String fileName;
	Queue<File> fileQueue;
	List<File> results;
	CountDownLatch latch;
	
	public SearchWorker(String fileName, Queue<File> fileQueue, List<File> results, CountDownLatch latch) {
		super();
		this.fileName = fileName;
		this.fileQueue = fileQueue;
		this.results = results;
		this.latch = latch;
	}

	@Override
	public void run() {
		while(true) {
			File aFileOrFolder = fileQueue.poll();
			if(aFileOrFolder == null) {
				latch.countDown();
				break;
			}
			if(aFileOrFolder.isFile() && aFileOrFolder.getName().equalsIgnoreCase(fileName)) {
				results.add(aFileOrFolder);
			}else if(aFileOrFolder.isDirectory() && aFileOrFolder.canRead()) {
				File[] contents = aFileOrFolder.listFiles();
				if(contents != null) {
					fileQueue.addAll(Arrays.asList(contents));
				}
			}
		}
		
	}

}
