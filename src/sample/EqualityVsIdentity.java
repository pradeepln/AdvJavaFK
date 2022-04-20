package sample;

import java.util.LinkedList;
import java.util.List;

public class EqualityVsIdentity {
	
	public static void myAlgo(List l) {
		l.add(0, "abc");
		
		//lot of other stuff
		l.remove(0);
	}

	public static void main(String[] args) {
		Employee e1 = new Employee(1);

		Employee e2 = new Employee(1);
		
		System.out.println(e1 == e2);
		
		System.out.println(e1.equals(e2));

		List al = new LinkedList();
		myAlgo(al);
	}

}
