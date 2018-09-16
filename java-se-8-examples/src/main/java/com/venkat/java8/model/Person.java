package com.venkat.java8.model;

import com.venkat.java8.interfaces.PersonInterface;

public class Person implements PersonInterface {

	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return name + " (" + age + ")";
	}

	public static int compareAges(Person p1, Person p2) {
		Integer age = p1.getAge();
		return age.compareTo(p2.getAge());
	}
}
