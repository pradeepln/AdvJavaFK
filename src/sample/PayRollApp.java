package sample;

import java.util.Optional;

public class PayRollApp {

	public static void main(String[] args) {
		Employee defaEmployee = new Employee(-1);
		
		Employee e = StreamDemo.findEmployeeById(10).orElse(defaEmployee);
		
		Optional<Employee> opt = StreamDemo.findEmployeeById(10);
	
		if(opt.isPresent()) {
		Employee anEmployee = opt.get();
		if(anEmployee.getId() == 1) {
			System.out.println("Something ....");
		}
		}else {
			
		}

	}

}
