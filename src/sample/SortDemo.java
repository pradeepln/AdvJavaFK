package sample;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortDemo {

	public static void main(String[] args) {
		String[] strs = {"this","is","a","bunch","of","words"};
		List<String> l = Arrays.asList(strs);
		
//		List<Employee> l = new ArrayList<Employee>();
//		Collections.sort(l,new StringLengthComparator());
		
		Collections.sort(l, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});
		System.out.println(l);
	}

}
