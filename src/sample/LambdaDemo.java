package sample;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaDemo {
//arity           ret                             args
//sig accSp mod   ret  name                       args 
	public int compareBasedOnPresenseOfE(String s1,String s2){
		if(!s1.contains("e") && s2.contains("e")) {
			return 1;
		}else if(s1.contains("e") && !s2.contains("e")) {
			return -1;
		}else {
			return 0;
		}
	}

	public static void main(String[] args) {
		String[] strs = {"this","is","a","bunch","of","words","like","execution"};
		List<String> l = Arrays.asList(strs);
		
//		Collections.sort(l,(o1, o2) -> o1.length() - o2.length());

//		Collections.sort(l, (o1, o2) -> o2.length() - o1.length());
		
//		Collections.sort(l, (s1,s2) -> compareBasedOnPresenseOfE(s1, s2));
//		Collections.sort(l, LambdaDemo::compareBasedOnPresenseOfE);
		LambdaDemo obj = new LambdaDemo();
		Collections.sort(l, obj::compareBasedOnPresenseOfE);
		System.out.println(l);
	}

}
