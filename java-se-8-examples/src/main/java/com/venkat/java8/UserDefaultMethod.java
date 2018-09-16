package com.venkat.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.venkat.java8.interfaces.PersonInterface;
import com.venkat.java8.model.Person;

public class UserDefaultMethod {

	public static void main(String[] args) {
		List<Person> personsList = new ArrayList<Person>();
		//
		personsList.add(new Person("A", 43));
		personsList.add(new Person("B", 30));
		personsList.add(new Person("C", 73));
		//
		Predicate<Person> pred = p -> p.getAge() > 65;

		personsList.forEach(p -> {
			if (pred.test(p)) {
				System.out.println(p.personInfo());
			}
		});
		//
		personsList.forEach(p -> {
			if (pred.test(p)) {
				System.out.println(PersonInterface.personInfoStatic(p));
			}
		});
		//
	}
}
