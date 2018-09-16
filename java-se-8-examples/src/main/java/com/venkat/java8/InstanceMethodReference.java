package com.venkat.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.venkat.java8.model.Person;

public class InstanceMethodReference {
	
	public static void main(String[] args) {
		InstanceMethodReference obj = new InstanceMethodReference();
		obj.sortData();
	}

	private void sortData() {
		List<Person> personsList = new ArrayList<Person>();
		//
		personsList.add(new Person("A", 43));
		personsList.add(new Person("B", 30));
		personsList.add(new Person("C", 73));
		//
		Collections.sort(personsList, this::compareAges);
		//
		personsList.forEach(p->System.out.println(p));
	}
	
	private int compareAges(Person p1, Person p2){
		Integer age = p1.getAge();
		return age.compareTo(p2.getAge());
	}
}
