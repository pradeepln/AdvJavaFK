package concurrency.filesearch.poolbased;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileSearch {

	public static void main(String[] args) throws Exception {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter filename: ");
		String fileName = s.nextLine();
		System.out.println("Enter starting folder: ");
		String folderName = s.nextLine();
		File folder = new File(folderName);
		File[] contents = folder.listFiles();
		System.out.println(contents.length);
		
		Queue<File> fileQueue = new ConcurrentLinkedQueue<>(Arrays.asList(contents));
		List<File> results = Collections.synchronizedList(new ArrayList<File>());
	
		ExecutorService pool = Executors.newFixedThreadPool(4);
		CountDownLatch latch = new CountDownLatch(4);
		
		pool.execute(new SearchWorker(fileName, fileQueue, results, latch));
		pool.execute(new SearchWorker(fileName, fileQueue, results, latch));
		pool.execute(new SearchWorker(fileName, fileQueue, results, latch));
		pool.execute(new SearchWorker(fileName, fileQueue, results, latch));
		
		
		System.out.println("Threads started.....searching in progress");
		latch.await();
		
		System.out.println("There were "+results.size()+" matches found.");
		results.forEach(f->System.out.println(f.getAbsolutePath()));
		
		pool.shutdown();
	}

}
