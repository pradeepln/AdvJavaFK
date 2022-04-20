package sample;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamDemo {
	
	
	

	public static void main(String[] args) {
		String[] strs = {"this","is","a","bunch","of","words"};
		List<String> l = Arrays.asList(strs);
		
		//l.stream().map( s -> s.toUpperCase()).forEach(s -> System.out.println(s));
		
//		l.stream().map(s -> s.length()).map(i -> i*2).forEach(num -> System.out.println(num));
		List<Integer> doubleLengths = l
				.stream()
				.filter(s -> s.length() > 2)
				.map(s -> s.length())
				.map(i -> i*2)
				.collect(Collectors.toList());
		
		System.out.println(doubleLengths);
	}

	
	public static Optional<Employee> findEmployeeById(int id) {
		// db query select * from employee where emp_id=id
		boolean found = true;
		if(found) {
			return Optional.of(new Employee(1));
		}else {
			return Optional.empty();
		}
	}
}
