package com.venkat.java8.interfaces;

public interface PersonInterface {
	String getName();

	void setName(String name);

	int getAge();

	void setAge(int age);

	default String personInfo() {
		return getName() + " (" + getAge() + ")";
	}

	static String personInfoStatic(PersonInterface p) {
		return p.getName() + " (" + p.getAge() + ")";
	}
}
