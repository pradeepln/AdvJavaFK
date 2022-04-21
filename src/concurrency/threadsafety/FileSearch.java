package concurrency.threadsafety;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

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
	
		Thread t1 = new Thread(new SearchWorker(fileName, fileQueue, results));
		Thread t2 = new Thread(new SearchWorker(fileName, fileQueue, results));
		Thread t3 = new Thread(new SearchWorker(fileName, fileQueue, results));
		Thread t4 = new Thread(new SearchWorker(fileName, fileQueue, results));
		
		t1.start();t2.start();t3.start();t4.start();
		System.out.println("Threads started.....searching in progress");
		t1.join();t2.join();t3.join();t4.join();
		System.out.println("There were "+results.size()+" matches found.");
		results.forEach(f->System.out.println(f.getAbsolutePath()));
	}

}
