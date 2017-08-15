package com.venkat.jaxbxsdexample.run;

import java.io.File;

import com.venkat.jaxbxsdexample.parsers.MyJAXBParser;
import com.venkat.jaxbxsdexample.xml.Employee;

public class MyJaxbExRun {
	
	public static void main(String[] args) {
		try {
			Employee empObj = MyJAXBParser.unMarshaller(new File("src/main/resources/employee.xml"), Employee.class);
			System.out.println(empObj);
			//
			Employee empObj1 = new Employee();
			empObj1.setId(9);
			empObj1.setSalary(6464646464646464d);
			StringOutputStream ps = new StringOutputStream(); 
			MyJAXBParser.marshaller(Employee.class, empObj1,ps );
			//
			System.out.println(ps.getString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
