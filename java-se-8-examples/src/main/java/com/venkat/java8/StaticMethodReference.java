package com.venkat.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.venkat.java8.model.Person;

public class StaticMethodReference {

	public static void main(String[] args) {
		List<Person> personsList = new ArrayList<Person>();
		//
		personsList.add(new Person("A", 43));
		personsList.add(new Person("B", 30));
		personsList.add(new Person("C", 73));
		//
		Collections.sort(personsList, Person::compareAges);
		//
		personsList.forEach(p -> {
			System.out.println(p);
		});
	}
}
