package com.venkat.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.venkat.java8.model.Person;

public class PredicateInnerClass {
	public static void main(String[] args) {
		List<Person> personsList = new ArrayList<Person>();
		//
		personsList.add(new Person("A",43));
		personsList.add(new Person("B",30));
		personsList.add(new Person("C",73));
		//
		
		Predicate<Person> pred = new Predicate<Person>() {

			@Override
			public boolean test(Person t) {
				return t.getAge()>65;
			}
		}; 
		
		for(Person p : personsList){
			if(pred.test(p)){
				System.out.println(p);
			}
		}
	}
	
}
